(ns personal-website.components.blog-entries
  (:require [markdown.core :refer [md->html]]
            [personal-website.components.utils.transforms :as transforms]
            [personal-website.components.utils.urls :as urls]))

(defn blog-entries
  "Renders a component that shows a list of all blog entries."
  [posts]
  (if (empty? posts)
    [:div {:class "row"}
     [:img {:class "element-loading" :src urls/loading-uri}]]
    [:div {:class "row"}
     [:div {:class "col-xs-12 col-sm-12 col-md-12"}
      [:hr {:class "spacer"}]
      (for [[id post] (transforms/find-posts posts)]
        (let [post-title     (get-in post [:fields :title])
              category-image (transforms/find-category-image posts post)
              category-slug  (get-in (transforms/post-category-path posts post) [:fields :slug])
              category-title (get-in (transforms/post-category-path posts post) [:fields :title])
              post-date      (get-in post [:fields :createDate])
              post-summary   (get-in post [:fields :summary])
              post-slug      (get-in post [:fields :slug])]
          [:sections {:key (str "section-" id)}
           [:h2 {:key (str "title-" id)} post-title]
           [:div {:class "grey" :key (str "div-" id)}
            [:img {:class "height"
                   :src category-image
                   :key (str "category-img-" id)}]
            [:a {:class "category grey"
                 :key (str "category-" id)
                 :href (urls/blog-category-uri category-slug)} category-title]
            [:img {:class "padding-left height margin-right"
                   :src "/assets/date.png"
                   :key (str "date-img-" id)}]
            [:span {:key (str "date-" id)} post-date]
            [:div {:dangerouslySetInnerHTML {:__html (md->html post-summary)}
                   :class "padding-top"
                   :key (str "summary-" id)}]
            [:a {:class "bold green"
                 :href (str urls/blog-uri "/" post-slug)
                 :key (str "link-" id)} "READ MORE..."]]
           [:hr {:class "spacer" :key (str "hr-" id)}]]))]]))