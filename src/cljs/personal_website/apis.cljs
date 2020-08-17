(ns personal-website.apis
  (:require [ajax.core :refer [GET]]
            [reagent.core :refer [atom]]))

;; -------------------------
;; Atoms

(def category-data
  "Atom to save results of fetching category data."
  (atom []))
(def post-data
  "Atom to save the results of posts and post data."
  (atom []))

;; -------------------------
;; Data APIs

(defn- components->map [response-body]
  (-> (comp keyword :id) (map response-body) (zipmap response-body)))

(defn- response-handler [response-atom]
  (fn [response] (reset! response-atom (components->map response))))

(defn- error-handler [{:keys [status status-text]}]
  (.log js/console (str "Something bad happened: " status " " status-text)))

(defn fetch-blog-categories
  "API Endpoint to retrieve all blog categories.
   See [[get-contentful-categories]] for more info."
  []
  (GET "/api/v1/blog/categories" {:response-format :json
                             :keywords?       :true
                             :handler         (response-handler category-data)
                             :error-handler   error-handler}))

(defn fetch-blog-posts
  "API endpoint to retrieve blog posts.
   See [[get-blog-posts-by-category]] in controller.clj for more info."
  ([]
   (GET "/api/v1/blog/posts" {:response-format :json
                         :keywords?       :true
                         :handler         (response-handler post-data)
                         :error-handler   error-handler}))
  ([category]
   (GET (str "/api/v1/blog/posts?category=" (str category)) {:response-format :json
                                                        :keywords?       :true
                                                        :handler         (response-handler post-data)
                                                        :error-handler   error-handler})))

(defn fetch-blog-post
  "API endpoint to retrieve a single blog post by name.
   See [[get-blog-post]] in controller.js for more info."
  [post]
  (GET (str "/api/v1/blog/posts/" post) {:response-format :json
                                    :keywords?       :true
                                    :handler         (response-handler post-data)
                                    :error-handler   error-handler}))
