(ns url-shortener.handlers.handler
  (:gen-class)
  (:require [clojure.data.json :as json]
            [taoensso.carmine :as car :refer [wcar]]
            [url-shortener.services.short-url :refer [ short-url-service ]]
            [url-shortener.db.db :as db])
)
  
(defn short-handler [req] (let [notifier-json (:body req)]
                          
     (short-url-service (:url notifier-json))
     {:status 201
      :headers {"Content-Type" "application/json"}
      :body (json/write-str notifier-json)}))
                                           
