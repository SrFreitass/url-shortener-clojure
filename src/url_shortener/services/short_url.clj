(ns url-shortener.services.short-url
  (:gen-class)
  (:require [url-shortener.db.db :as db]
            [url-shortener.utils.core :as utils]))

(defn gen-short-id [chars]
  (apply str (repeatedly 10 #(rand-nth chars))))

(defn short-url-service [url]
  (let [url (db/get-url url)]
    (if (not= url nil) url
        (loop [id utils/gen-id]
          (if (= (db/get-url id) nil)
            (do (db/create-url id url) (db/create-url url id) id)
            (recur (gen-short-id chars)))))))

(defn redirect-url-service [short-id]
  (let [url (db/get-url short-id)]
    (println url short-id)
    (if (not= url nil) url nil)))
