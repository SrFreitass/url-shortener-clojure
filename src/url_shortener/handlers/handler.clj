(ns url-shortener.handlers.handler
  (:gen-class)
  (:require
   [url-shortener.services.short-url :refer [short-url-service redirect-url-service]]
   [clojure.string :as string]
   [url-shortener.utils.core :as utils]))


(defn redirect-by-short-id [req] (let [short-id (:id (:params req)) url (redirect-url-service short-id)]
                                   (try
                                     (println url "url")
                                     (if (not= url nil) 
                                       (utils/redirect (java.net.URLDecoder/decode url))
                                       (utils/response 404 "Not found" nil))
                                     (catch Exception e (println e) (utils/internal-server-error-exception)))))

(defn short-handler [req] (let [body (:body req) url (string/replace-first (clojure.core/slurp body) #"url=" "")]
                            (try
                              (if (utils/url-valid? url)
                                (let [short-id (short-url-service url)]
                                  (println short-id)
                                  (utils/redirect (str "/?short-id=" short-id)))
                                (utils/response 422 "URL invalid" nil))
                              (catch Exception e (println e) (utils/internal-server-error-exception)))))
