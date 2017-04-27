(ns book-web.views.layout
  (:require [hiccup.page :refer [html5 include-css]]))

(defn common [& body]
  (html5
    [:head
     [:title "Welcome to book-web"]
     (include-css "/css/screen.css")]
    [:body body]))
