(ns personal-website.components.utils.transforms
  (:require [personal-website.util :as util]))

(defn- category-content-type?
  "Helper function to check whether a resource is a blog category."
  [[_ component]]
  (= util/category-content-type (:content-type-id component)))

(defn- post-content-type?
  "Helper function to check whether a resource is a blog post."
  [[_ component]]
  (= util/post-content-type (:content-type-id component)))

(defn- find-categories
  "Finds all resources that are blog categories."
  [components]
  (filter category-content-type? components))

(defn- category-image-path
  "Determines how to pluck a category's image from the resource."
  [categories category]
  (categories (keyword (get-in category [:fields :icon :id]))))

(defn post-category-path
  "Determines how to pluck a post's category from the resource."
  [posts post]
  (get posts (keyword (get-in post [:fields :category :id]))))

(defn sorted-categories
  "Finds all resources that are blog categories, sorted by title."
  [components]
  (->> components
       (find-categories)
       (sort-by #(count (get-in (val %) [:fields :title])))
       (into {})))

(defn find-posts
  "Finds all resources that are blog posts."
  [components]
  (filter post-content-type? components))

(defn find-image
  "Plucks the category's image from the resource."
  [categories category]
  (get-in (category-image-path categories category) [:fields :file :url]))

(defn find-category-image
  "Plucks the post's category image from the resource."
  [posts post]
  (find-image posts (post-category-path posts post)))
