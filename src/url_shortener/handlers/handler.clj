(ns url-shortener.handlers.handler
  (:gen-class)
  (:require
   [url-shortener.services.short-url :refer [short-url-service redirect-url-service]]
   [clojure.data.json :as json]
   [clojure.string :as string]
   [url-shortener.utils.core :as utils]))


(defn redirect-by-short-id [req] (let [short-id (:id (:params req)) url (redirect-url-service short-id)]
                                   (try
                                     (if (not= url nil) 
                                       (utils/redirect url)
                                       (utils/response 404 "Not found" nil))
                                     (catch Exception e (println e) utils/internal-server-error-exception))))

(defn short-handler [req] (let [body (:body req) url (string/replace-first (clojure.core/slurp body) #"url=" "")]
                            (try
                              (if (utils/url-valid? url)
                                (let [short-url (short-url-service url)]
                                  (utils/response (if (not= short-url nil) 201 500) "URL shorted" url))
                                (utils/response 422 "URL invalid" nil))
                              (catch Exception e (println e) utils/internal-server-error-exception))))
