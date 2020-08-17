(ns personal-website.pages.blog-entry
  (:require [personal-website.apis :as apis]
            [personal-website.components.blog-categories-sidebar :refer [blog-categories-sidebar]]
            [personal-website.components.blog-entry :refer [blog-entry]]
            [personal-website.components.blog-header :refer [blog-header]]
            [reagent.session :as session]))

(defn page
  "Renders the page that represents a single blog entry."
  []
  (apis/fetch-blog-categories)
  (let [routing-data (session/get :route)
        entry (get-in routing-data [:route-params :entry])]
    (apis/fetch-blog-post entry))
  (fn []
    [:div {:class "col-xs-12 col-sm-12 col-md-12 col-lg-12" :role "main"}
     [:div {:class "col-xs-12 col-sm-12 col-md-12"} [blog-header]]
     [:div {:class "col-xs-12 col-sm-9 col-md-9"} [blog-entry @apis/post-data]]
     [:div {:class "col-sm-3 col-md-3"} [blog-categories-sidebar @apis/category-data]]]))
