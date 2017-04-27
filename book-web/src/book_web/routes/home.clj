(ns book-web.routes.home
  (:require [compojure.core :refer :all]
            [book-web.views.layout :as layout]
            [hiccup.form :refer :all]))


(defn show-guests []
  [:ul.guests
   (for [{:keys [message name timestamp]}
         [{:message "Howdy" :name "Bob" :timestamp nil}
          {:message "Hello" :name "Bob" :timestamp nil}]
         ]
		   [:li
      [:blockquote message]
      [:p "-" [:cite name]]
      [:time timestamp]]
     )])


(defn home [& [name message error]]
  (layout/common
    [:h1 "Guestbook"]
    [:p "Welcome to my guestbook"]
    [:p error]
    ;here we call our show-guests function
    ;to generate the list of existing comments
  (show-guests)
  [:hr]
  ;here we create a form with text fields called "name" and "message"
  ;these will be sent when the form posts to the server as keywords of
  ;the same name
  (form-to [:post "/"]
           [:p "Name:"]
           (text-field "name" name)
           [:p "Message:"]
           (text-area {:rows 10 :cols 40} "message" message)
           [:br]
           (submit-button "comment"))))


(defn save-message [name message]
  (cond
    (empty? name)
    (home name message "name 是空")
    (empty? message)
    (home name message "message 是空")
    :else
    (do
      (println name message)
      (home))))

(defroutes home-routes
  (GET "/" [] (home))
  (POST "/" [name message] (save-message name message)))
