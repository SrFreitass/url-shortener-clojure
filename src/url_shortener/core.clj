(ns url-shortener.core
  (:gen-class)
  (:require [org.httpkit.server :as server]
            [url-shortener.routes.routes :refer [app-routes]]))

(defn -main
  "Ponto de entrada da aplicação"
  []
  (let [port 3000]
    (server/run-server #'app-routes {:port port})
    (println (str "Rodando o servidor em http://127.0.0.1:" port "/"))))
