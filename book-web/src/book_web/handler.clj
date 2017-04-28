(ns book-web.handler
  (:use compojure.core
        ring.middleware.resource
        ring.middleware.file-info
        hiccup.middleware
        book-web.routes.home)
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [book-web.models.db :as db]))

(defn init []
  (println "book-web is starting")
  (if-not (.exists (java.io.File. "./db.sq3"))
    (db/create-guestbook-table)))

(defn destroy []
  (println "book-web is shutting down"))

(defroutes app-routes
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> (routes home-routes app-routes)
      (handler/site)
      (wrap-base-url)))
