(ns personal-website.handler
  (:require [clojure.data.json :as json]
            [config.core :refer [env]]
            [hiccup.page :refer [include-js include-css html5]]
            [personal-website.middleware :refer [middleware]]
            [personal-website.controller :as controller]
            [reitit.ring :as reitit-ring]))

(def ^:private mount-target
  [:div#app
    [:img {:class "page-loading" :src "/assets/reload.gif"}]])

(defn- loading-page []
  (html5
    [:head {:lang "en"}
      [:meta {:charset "utf-8"}]
      [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
      [:title "Nina Blanson"]
      [:link {:rel "shortcut icon" :type "image/png" :href "/assets/favicon.ico"}]
      [:link {:href "https://maxcdn.bootstrapcdn.com/bootswatch/3.3.6/flatly/bootstrap.min.css" :rel "stylesheet"}]
      (include-css (if (env :dev) "/css/site.css" "/css/site.min.css"))]
    [:body
      mount-target
      (include-js "/js/app.js"
                  "https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"
                  "https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js")]))

(defn- index-handler [_]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (loading-page)})

(defn- api-handler [fn]
   {:status 200
    :headers {"Content-Type" "application/json"}
    :body (json/write-str (fn))})

(def ^:private public-routes
  [["/" {:get {:handler index-handler}}]
   ["/blog"
    ["/entries" {:get {:handler index-handler}}]
    ["/entries/:entry" {:get {:handler index-handler}}]]])

(def ^:private api-routes
  [["/api/v1"
    ["/blog/categories" {:get {:handler (fn [_]
                                          (api-handler controller/get-blog-categories))}}]
    ["/blog/posts/:post" {:get {:handler (fn [{:keys [path-params]}]
                                           (api-handler
                                             #(controller/get-blog-post (get path-params :post))))}}]
    ["/blog/posts" {:get {:handler (fn [{:keys [query-params]}]
                                     (api-handler
                                       #(controller/get-blog-posts-by-category (get query-params "category"))))}}]]])

(def app
  (reitit-ring/ring-handler
    (reitit-ring/router [public-routes api-routes])
    (reitit-ring/routes
      (reitit-ring/create-resource-handler {:path "/" :root "/public"})
      (reitit-ring/create-default-handler))
    {:middleware middleware}))
