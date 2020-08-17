(ns personal-website.core
    (:require [clerk.core :as clerk]
              [reagent.core :as reagent]
              [reagent.dom :as rdom]
              [reagent.session :as session]
              [reitit.frontend :as reitit]
              [accountant.core :as accountant]
              [personal-website.components.navbar :refer [navbar]]
              [personal-website.pages.blog :as blog]
              [personal-website.pages.blog-entry :as blog-entry]
              [personal-website.pages.home :as home]))

;; -------------------------
;; Routes
;; If updating, make sure to look at [[page-for]]!

(def router
  (reitit/router
    [["/" :index]
     ["/blog"
      ["/entries" :blog-entries]
      ["/entries/:entry" :blog-entry]]]))

;; -------------------------
;; Translate routes -> page components

(defn page-for [route]
  (case route
    :index #'home/page
    :blog-entries #'blog/page
    :blog-entry #'blog-entry/page))

;; -------------------------
;; Page mounting component
;; Add any cross-page elements here (navbar, footer, ect).

(defn current-page []
  (fn []
    (let [page (:current-page (session/get :route))]
      [:div {:class "container"}
       [navbar]
       [:div {:class "col-md-12" :role "main"}
        [page]]])))

;; -------------------------
;; Initialize app

(defn mount-root []
  (rdom/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (clerk/initialize!)
  (accountant/configure-navigation! {:nav-handler (fn [path] (let [match (reitit/match-by-path router path)
                                                                   current-page (:name (:data  match))
                                                                   route-params (:path-params match)
                                                                   query-params (:query (:parameters match))]
                                                               (reagent/after-render clerk/after-render!)
                                                               (session/put! :route {:current-page (page-for current-page)
                                                                                     :route-params route-params
                                                                                     :query-params query-params})
                                                               (clerk/navigate-page! path)))
                                     :path-exists? (fn [path] (boolean (reitit/match-by-path router path)))})
  (accountant/dispatch-current!)
  (mount-root))
