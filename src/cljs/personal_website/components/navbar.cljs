(ns personal-website.components.navbar
  (:require [personal-website.components.utils.urls :as urls]))

(defn navbar
  "Renders a component that represents the navigation top-bar."
  []
  [:nav {:class "navbar navbar-color"}
   [:div {:class "container-fluid"}
    [:div {:class "navbar-header"}
     [:button {:type "button"
               :class "navbar-toggle collapsed"
               :data-toggle "collapse"
               :data-target "#navbar-data"
               :aria-expanded "false"}
      [:span {:class "sr-only"} "Toggle Navigation"]
      [:span {:class "icon-bar background-black"}]
      [:span {:class "icon-bar background-black"}]
      [:span {:class "icon-bar background-black"}]]
     [:a {:class "navbar-brand bold" :href "/"} "Nina Blanson"]]
    [:div {:class "collapse navbar-collapse" :id "navbar-data"}
     [:ul {:id "main-nav" :class "nav navbar-nav navbar-right"}
      [:li
       [:a {:href urls/blog-uri} "Electric Sheep"]]]]]])