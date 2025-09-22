(ns url-shortener.services.short-url
  (:gen-class)
  (:require [url-shortener.db.db :as db])
  )

(defn short-url-service [url]
  (let [url (db/get-url url)]
    (if not-empty url
        url)

    (let [chars (map char (range 48 127))]
      (db/create-url (apply str (repeatedly 10 #(rand-nth chars))) url))))