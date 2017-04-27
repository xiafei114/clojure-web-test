(ns book-web.routes.home
  (:require [compojure.core :refer :all]
            [book-web.views.layout :as layout]))

(defn home []
  (layout/common [:h1 "Hello World!"]))

(defroutes home-routes
  (GET "/" [] (home)))
