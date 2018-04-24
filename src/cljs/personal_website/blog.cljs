(ns personal-website.blog
  (:require [markdown.core :refer [md->html]]
            [personal-website.transforms :as t]))

(def blog-url "/blog/entries")
(def loading-path "/assets/loading.svg")

(defn- blog-cat-url [category]
  (str "/blog/entries?category=" category))

(defn blog-header-element []
  [:div {:class "col-xs-12 col-sm-12 col-md-12 page-header"}
    [:div {:class "row"}
      [:div {:class "col-sm-6 col-md-2"}
        [:img {:class "margin-top blog-image margin-bottom" :src "/assets/sheep.png"}]]
      [:div {:class "col-sm-6 col-md-10 padding-left"}
        [:h1 {:class "margin-bottom"} "Dreaming of Electric Sheep"
          [:br]
          [:small ": a software engineering blog"]]]]])

(defn blog-entries-element [posts]
  (if (empty? posts)
    [:div {:class "row"}
      [:img {:class "element-loading" :src loading-path}]]
    [:div {:class "row"}
      [:div {:class "col-xs-12 col-sm-12 col-md-12"}
        [:hr {:class "spacer"}]
        (for [[id post] (t/find-posts posts)]
          (let [post-title     (get-in post [:fields :title])
                category-image (t/find-category-image posts post)
                category-slug  (get-in (t/post-category-path posts post) [:fields :slug])
                category-title (get-in (t/post-category-path posts post) [:fields :title])
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
                     :href (blog-cat-url category-slug)} category-title]
                [:img {:class "padding-left height margin-right"
                       :src "/assets/date.png"
                       :key (str "date-img-" id)}]
                [:span {:key (str "date-" id)} post-date]
                [:div {:dangerouslySetInnerHTML {:__html (md->html post-summary)}
                       :class "padding-top"
                       :key (str "summary-" id)}]
                [:a {:class "bold green"
                     :href (str blog-url "/" post-slug)
                     :key (str "link-" id)} "READ MORE..."]]
             [:hr {:class "spacer" :key (str "hr-" id)}]]))]]))

(defn blog-entry-element [post]
  (if (empty? post)
    [:div {:class "row"}
      [:img {:class "element-loading" :src loading-path}]]
    [:div {:class "row"}
      [:div {:class "col-xs-12 col-sm-12 col-md-12"}
       [:hr {:class "spacer"}]
       (for [[id data] (t/find-posts post)]
          (let [post-title     (get-in data [:fields :title])
                category-image (t/find-category-image post data)
                category-slug  (get-in (t/post-category-path post data) [:fields :slug])
                category-title (get-in (t/post-category-path post data) [:fields :title])
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
                    :href (blog-cat-url category-slug)} category-title]
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
                 :href blog-url} "BACK TO ENTRIES..."]]))]]))

(defn blog-nav-element [categories]
  (if (empty? categories)
    [:div {:class "row"}
      [:img {:class "element-loading" :src loading-path}]]
    [:div {:class "blog-menu darker"}
      [:hr {:class "spacer"}]
      [:img {:class "height" :src "/assets/date.png"}]
      [:a {:class "category black" :href blog-url} "all"]
      [:hr {:class "spacer"}]
      (for [[id category] (t/sorted-categories categories)]
        (let [category-image (t/find-image categories category)
              category-slug  (get-in category [:fields :slug])
              category-title (get-in category [:fields :title])]
          [:span {:key (str "category-" id)}
            [:img {:class "height"
                   :src category-image
                   :key (str "blog-nav-image-" id)}]
            [:a {:class "category black"
                 :href (blog-cat-url category-slug)
                 :key (str "blog-nav-link-" id)} category-title]
            [:hr {:class "spacer" :key (str "spacer-" id)}]]))]))
