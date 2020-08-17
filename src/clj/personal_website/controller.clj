(ns personal-website.controller
  (:require [clojure.walk :refer [prewalk]]
            [personal-website.contentful :as contentful]))

(defn- clean-entries [posts]
  (update posts :body dissoc :sys
                      dissoc :skip
                      dissoc :total
                      dissoc :limit))

(defn- transform-sys [component]
  (if (contains? (:sys component) :id)
   (let [sys-id (get-in component [:sys :id])]
    (-> (assoc component :id sys-id)
        (dissoc component :sys)))
   component))

(defn- pull-content-type [component]
  (if (contains? (:sys component) :contentType)
    (let [content-type-id (get-in component [:sys :contentType :sys :id])]
      (assoc component :content-type-id content-type-id))
    component))

(defn- remove-space [component]
  (if (contains? (:sys component) :space)
    (update-in component [:sys] dissoc :space)
    component))

(defn- transform-component [component]
  (->> component
       (remove-space)
       (pull-content-type)
       (transform-sys)))

(defn- transform-components [components]
  (prewalk transform-component (components :body)))

(defn- flatten-included [components]
  (reduce conj (components :items) (flatten (vals (components :includes)))))

(defn get-blog-post
  "Retrieves, cleans, and formats the raw blog post data for post `post` coming from Contentful CMS."
  [post]
  (-> (contentful/get-blog-post post) clean-entries transform-components flatten-included))

(defn get-blog-categories
  "Retrieves, cleans, and formats the raw blog category data coming from Contentful CMS."
  []
  (-> (contentful/get-blog-categories) clean-entries transform-components flatten-included))

(defn get-blog-posts-by-category
  "Retrieves, cleans, and formats the raw blog post data coming from Contentful CMS.
   Providing a `category` will only return blog posts that match that category."
  ([]
   (-> (contentful/get-blog-posts) clean-entries transform-components flatten-included))
  ([category]
   (-> (contentful/get-blog-posts-by-category category) clean-entries transform-components flatten-included)))
