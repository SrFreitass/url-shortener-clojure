(ns url-shortener.handlers.handler
  (:gen-class)
  (:require
   [url-shortener.services.short-url :refer [short-url-service redirect-url-service]]
   [clojure.data.json :as json]))

(defn redirect-by-short-id [req] (let [short-id (:id (:params req)) url (redirect-url-service short-id)]
                                   (try
                                     {:status (if (not= url nil) 302 404)
                                      :headers {"Location" url}}
                                     (catch Exception e (println e) {:status 500}
                                            :headers {"Content-Type" "application/json"}
                                            :body (json/write-str {"error" "Internal Server Error"})))))

(defn short-handler [req] (let [notifier-json (:body req) url (:url notifier-json)]
                            (try
                              (if (= (re-matches #"https?:\/\/(www\.)?[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_\+.~#?&//=]*)" (if (= url nil) "" url)) nil)
                                {:status 422
                                 :headers {"Content-Type" "application/json"}
                                 :body (json/write-str {"error" "invalid URL"})}
                                (let [short-url (short-url-service url)]
                                  {:status (if (not= short-url nil) 201 500)
                                   :headers {"Content-Type" "application/json"}
                                   :body (if (not= short-url nil) (json/write-str {"short-url" short-url}) nil)}))
                              (catch Exception e (println e) {:status 500
                                                              :headers {"Content-Type" "application/json"}
                                                              :body (json/write-str {"error" "Internal Server Error"})}))))
