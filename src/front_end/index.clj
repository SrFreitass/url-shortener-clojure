(ns front-end.index
  (:require [hiccup2.core :refer [ html ]])
  (:gen-class))

(def index
  (str 
   (html
    [:html
     [:head
      [:title "Encurtador de URL"]
      [:script { :src "https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4" }]]
     [:body { :class "min-h-screen flex justify-center items-center bg-[#09090B] text-white bg-cover bg-center bg-[url('https://i.imgur.com/6peE9Vb.png')]" }
      [:form { :method "POST" :action "/api/short-url" :class "flex flex-col items-center w-full max-w-sm" }
       [:img { :class "mb-4" :src "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Clojure_logo.svg/1200px-Clojure_logo.svg.png" :width 100 }]
       [:h2 { :class "text-3xl text-center font-bold mb-4 text-[#92DD45]" } "Encurtador de URL"]
       [:input { :type "URL" :class "w-full mb-4 border border-white/20 p-2 rounded-md text-white/80" :name "url" }]
       [:button {:type "submit" :class "w-full py-2 px-8 bg-[#5782D9] rounded-md font-semibold"} "Encurtar URL"]]]])))