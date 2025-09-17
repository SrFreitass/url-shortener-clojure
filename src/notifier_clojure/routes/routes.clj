(ns notifier-clojure.routes.routes
  (:gen-class)
  (:require [compojure.core :refer [GET defroutes]]
            [compojure.route :as route]))

(defroutes app-routes
  (GET "/health" [] "It's works")
  (route/not-found "Not found")
  )