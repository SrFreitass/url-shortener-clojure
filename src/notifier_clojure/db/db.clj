(ns notifier-clojure.db.db
  (:gen-class)
  (:require [taoensso.carmine :as car]
            ))


(defonce redis-conn-pool (car/connection-pool {}))
;; (def redis-conn-spec { :uri (str "redis://"(env :redis-user)(env :redis-password)"@"(env :redis-host)":"(env :redis-port)) })
(def redis-conn-spec {:uri "redis://default:admin@127.0.0.1:6379"})
(def redis-wcar-opts { :pool redis-conn-pool :spec redis-conn-spec})

(defn create-url [short-id url] (car/wcar redis-wcar-opts (car/set short-id url car/set url short-id)))
(defn get-url [url] (car/wcar redis-wcar-opts (car/get url)))