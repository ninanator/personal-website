(ns personal-website.components.utils.urls)

(def blog-uri
  "Returns the URI for the blog."
  "/blog/entries")

(def loading-uri
  "Returns the URI for the loading image."
  "/assets/loading.svg")

(defn blog-category-uri
  "Returns the URI for the blog, filtered by category."
  [category] (str "/blog/entries?category=" category))
