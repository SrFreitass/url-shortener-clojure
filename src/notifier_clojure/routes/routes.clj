(ns notifier-clojure.routes.routes
  (:gen-class)
  (:require [compojure.core :refer [GET POST defroutes]]
            [compojure.route :as route]
            [ring.middleware.json :as mj]
            [notifier-clojure.handlers.handler :refer [ short-handler ]]))

(defroutes app-routes
  (GET "/" [] "It's works")
  (POST "/short" [] (mj/wrap-json-body short-handler))
  (route/not-found "Not found")
  )
