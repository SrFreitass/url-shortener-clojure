(ns url-shortener.handlers.handler
  (:gen-class)
  (:require
    [url-shortener.services.short-url :refer [ short-url-service redirect-url-service]]
    [clojure.data.json :as json]))


(defn redirect-by-short-id [req] (let [short-id (:id (:params req)) url (redirect-url-service short-id)]
                                 {:status (if (not= url nil) 302 404)
                                  :headers {"Location" url}}))

(defn short-handler [req] (let [notifier-json (:body req) short-url (short-url-service (:url notifier-json))]
                            {:status (if (not= short-url nil) 201 500)
                             :headers {"Content-Type" "application/json"}
                             :body (if (not= short-url nil) (json/write-str {"short-url" short-url}) nil)}))