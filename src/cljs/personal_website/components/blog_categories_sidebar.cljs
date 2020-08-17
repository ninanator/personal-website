(ns personal-website.components.blog-categories-sidebar
  (:require [personal-website.components.utils.transforms :as transforms]
            [personal-website.components.utils.urls :as urls]))

(defn blog-categories-sidebar
  "Renders a component that represents the blog category selector.
   This component is one method of filtering down the entries on a page."
  [categories]
  (if (empty? categories)
    [:div {:class "row"}
     [:img {:class "element-loading" :src urls/loading-uri}]]
    [:div {:class "blog-menu darker"}
     [:hr {:class "spacer"}]
     [:img {:class "height" :src "/assets/date.png"}]
     [:a {:class "category black" :href urls/blog-uri} "all"]
     [:hr {:class "spacer"}]
     (for [[id category] (transforms/sorted-categories categories)]
       (let [category-image (transforms/find-image categories category)
             category-slug  (get-in category [:fields :slug])
             category-title (get-in category [:fields :title])]
         [:span {:key (str "category-" id)}
          [:img {:class "height"
                 :src category-image
                 :key (str "blog-nav-image-" id)}]
          [:a {:class "category black"
               :href (urls/blog-category-uri category-slug)
               :key (str "blog-nav-link-" id)} category-title]
          [:hr {:class "spacer" :key (str "spacer-" id)}]]))]))
