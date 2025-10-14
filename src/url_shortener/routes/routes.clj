(ns url-shortener.routes.routes
  (:gen-class)
  (:require [compojure.core :refer [GET POST defroutes context]]
            [compojure.route :as route]
            [url-shortener.handlers.handler :refer [ short-handler redirect-by-short-id]]
            [front-end.index]))

(defroutes app-routes
  (GET "/" [] front-end.index/index)
  (GET "/:id" [] redirect-by-short-id)
  (POST "/api/short-url" [] short-handler)
  (route/not-found "Not found"))
