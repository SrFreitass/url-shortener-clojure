(ns notifier-clojure.core
  (:gen-class)
  (:require [org.httpkit.server :as server]
            [clojure.data.json :as json])) 

(defn health-app [req] {
   :status 200
   :headers { "Content-Type" "application/json" }
   :body (json/write-str { :status "online" :success true })
  })

(defn -main
  "Ponto de entrada da aplicação"
  [& args]
  (let [port 8081]
    (server/run-server #'health-app {:port port})
    (println (str "Rodando o servidor em http://127.0.0.1:" port "/"))))
