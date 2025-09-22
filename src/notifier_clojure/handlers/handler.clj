(ns notifier-clojure.handlers.handler
  (:gen-class)
  (:require [clojure.data.json :as json]
            [taoensso.carmine :as car :refer [wcar]]
            [notifier-clojure.db.db :as db])
)
  
(defn short-handler [req] (let [notifier-json (:body req)]
    (println (wcar db/redis-wcar-opts (car/ping)))
    (db/get-url "https://github.com/srfreitass")
     {:status 201
      :headers {"Content-Type" "application/json"}
      :body (json/write-str notifier-json)}))
                                           
