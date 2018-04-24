(ns personal-website.transforms
  (:require [clojure.walk :refer [prewalk]]
            [personal-website.util :as util]))

(defn- category-content-type? [[id component]]
  (= util/category-content-type (:content-type-id component)))

(defn- post-content-type? [[id component]]
  (= util/post-content-type (:content-type-id component)))

(defn components->map [response-body]
  (-> (comp keyword :id) (map response-body) (zipmap response-body)))

(defn category-image-path [categories category]
  (categories (keyword (get-in category [:fields :icon :id]))))

(defn post-category-path [posts post]
  (get posts (keyword (get-in post [:fields :category :id]))))

(defn find-categories [components]
  (filter category-content-type? components))

(defn find-posts [components]
  (filter post-content-type? components))

(defn find-image [categories category]
  (get-in (category-image-path categories category) [:fields :file :url]))

(defn find-category-image [posts post]
  (find-image posts (post-category-path posts post)))

(defn sorted-categories [components]
  (->> components
     (find-categories)
     (sort-by #(count (get-in (val %) [:fields :title])))
     (into {})))
