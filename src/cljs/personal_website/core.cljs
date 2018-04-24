(ns personal-website.core
    (:require [reagent.core :as reagent :refer [atom]]
              [reagent.session :as session]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]
              [ajax.core :refer [GET]]
              [markdown.core :refer [md->html]]
              [personal-website.transforms :as t]
              [personal-website.home :as h]
              [personal-website.blog :as b]))

;; -------------------------
;; Atoms

(def category-data (atom {}))
(def post-data (atom {}))

;; -------------------------
;; Data

(defn response-handler [response-atom]
  (fn [response] (reset! response-atom (t/components->map response))))

(defn error-handler [{:keys [status status-text]}]
  (.log js/console (str "Something bad happened: " status " " status-text)))

(defn fetch-categories []
  (GET "/api/v1/categories" {:response-format :json
                             :keywords?       :true
                             :handler         (response-handler category-data)
                             :error-handler   error-handler}))

(defn fetch-posts
  ([]
   (GET "/api/v1/posts" {:response-format :json
                          :keywords?       :true
                          :handler         (response-handler post-data)
                          :error-handler   error-handler}))
 ([category]
  (GET (str "/api/v1/posts?category=" (str category)) {:response-format :json
                                                        :keywords?       :true
                                                        :handler         (response-handler post-data)
                                                        :error-handler   error-handler})))

(defn fetch-post [post]
  (GET (str "/api/v1/posts/" post) {:response-format :json
                                    :keywords?       :true
                                    :handler         (response-handler post-data)
                                    :error-handler   error-handler}))

;; -------------------------
;; Views

(defn navbar []
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
            [:a {:href b/blog-url} "Electric Sheep"]]]]]])

;; Home Page
(defn home-page []
  [:div {:class "container"}
    [navbar]
    [:div {:class "col-md-12" :role "main"} [h/home-body]]])

;; Blog Pages
(defn blog-entries-page []
  [:div {:class "container"}
    [navbar]
    [:div {:class "col-xs-12 col-sm-12 col-md-12 col-lg-12" :role "main"}
      [:div {:class "col-xs-12 col-sm-12 col-md-12"} [b/blog-header-element]]
      [:div {:class "col-xs-12 col-sm-9 col-md-9"} [b/blog-entries-element @post-data]]
      [:div {:class "col-sm-3 col-md-3"} [b/blog-nav-element @category-data]]]])

(defn blog-entry-page []
  [:div {:class "container"}
    [navbar]
    [:div {:class "col-xs-12 col-sm-12 col-md-12 col-lg-12" :role "main"}
      [:div {:class "col-xs-12 col-sm-12 col-md-12"} [b/blog-header-element]]
      [:div {:class "col-xs-12 col-sm-9 col-md-9"} [b/blog-entry-element @post-data]]
      [:div {:class "col-sm-3 col-md-3"} [b/blog-nav-element @category-data]]]])

;; -------------------------
;; Routes

(defonce page (atom #'home-page))

(defn current-page []
  [:div [@page]])

(secretary/defroute "/" []
  (reset! page #'home-page))

(secretary/defroute "/blog/entries" [query-params]
  (fetch-categories)
  (fetch-posts (get query-params :category))
  (reset! page #'blog-entries-page))

(secretary/defroute "/blog/entries/:entry" [entry]
  (fetch-categories)
  (fetch-post entry)
  (reset! page #'blog-entry-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
