(ns url-shortener.routes.routes
  (:gen-class)
  (:require [compojure.core :refer [GET POST defroutes]]
            [compojure.route :as route]
            [ring.middleware.json :as mj]
            [url-shortener.handlers.handler :refer [ short-handler ]]))

(defroutes app-routes
  (GET "/" [] "It's works")
  (POST "/short" [] (mj/wrap-json-body short-handler { :keywords? true }))
  (route/not-found "Not found")
  )
