(ns personal-website.components.blog-entry
  (:require [markdown.core :refer [md->html]]
            [personal-website.components.utils.transforms :as transforms]
            [personal-website.components.utils.urls :as urls]))

(defn blog-entry
  "Renders a component that represents a single blog entry."
  [post]
  (if (empty? post)
    [:div {:class "row"}
     [:img {:class "element-loading" :src urls/loading-uri}]]
    [:div {:class "row"}
     [:div {:class "col-xs-12 col-sm-12 col-md-12"}
      [:hr {:class "spacer"}]
      (for [[id data] (transforms/find-posts post)]
        (let [post-title     (get-in data [:fields :title])
              category-image (transforms/find-category-image post data)
              category-slug  (get-in (transforms/post-category-path post data) [:fields :slug])
              category-title (get-in (transforms/post-category-path post data) [:fields :title])
              post-date      (get-in data [:fields :createDate])
              post-body      (get-in data [:fields :body])
              references     (get-in data [:fields :references])]
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
            [:div {:dangerouslySetInnerHTML {:__html (md->html post-body)}
                   :class "padding-top"
                   :key (str "summary-" id)}]
            [:ol {:class "padding-bottom" :key (str "ordered-list-" id)}
             (for [reference references]
               [:li {:key (str "reference-" reference)} reference])]]
           [:a {:class "bold green padding-bottom"
                :href urls/blog-uri} "BACK TO ENTRIES..."]]))]]))