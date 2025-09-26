(ns url-shortener.routes.routes
  (:gen-class)
  (:require [compojure.core :refer [GET POST defroutes context]]
            [compojure.route :as route]
            [ring.middleware.json :as mj]
            [url-shortener.handlers.handler :refer [ short-handler redirect-by-short-id]]))

(defroutes app-routes
  (GET "/" [] "It's works")
  (GET "/:id" [] redirect-by-short-id)
  (POST "/short" [] (mj/wrap-json-body short-handler {:keywords? true}))
  (route/not-found "Not found"))
