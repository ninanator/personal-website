(ns personal-website.handler
  (:require [clojure.data.json :as json]
            [compojure.core :refer [GET defroutes context]]
            [compojure.route :refer [not-found resources]]
            [hiccup.page :refer [include-js include-css html5]]
            [config.core :refer [env]]
            [personal-website.middleware :refer [wrap-middleware]]
            [personal-website.controller :as controller]))

(def mount-target
  [:div#app
    [:img {:class "page-loading" :src "/assets/reload.gif"}]])

(def loading-page
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

(defroutes public-routes
  (GET "/" [] loading-page)
  (GET "/blog/entries" [] loading-page)
  (GET "/blog/entries/:entry" [entry] loading-page)
  (GET "/blog/entries*" {params :query-params} loading-page))

(defroutes api-routes
  (context "/api/v1" []
    (GET "/posts/:post" [post]
      (json/write-str (controller/get-blog-post post)))
    (GET "/posts*" {params :query-params}
      (json/write-str (controller/get-blog-posts-by-category (get params "category"))))
    (GET "/categories" []
      (json/write-str (controller/get-blog-categories)))))

(defroutes app-routes
    public-routes
    api-routes
    (resources "/")
    (not-found "Not Found"))

(def app (wrap-middleware #'app-routes))
