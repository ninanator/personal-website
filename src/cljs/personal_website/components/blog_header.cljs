(ns personal-website.components.blog-header)

(defn blog-header
  "Renders a component that represents the blog header."
  []
  [:div {:class "col-xs-12 col-sm-12 col-md-12 page-header"}
   [:div {:class "row"}
    [:div {:class "col-sm-6 col-md-2"}
     [:img {:class "margin-top blog-image margin-bottom" :src "/assets/sheep.png"}]]
    [:div {:class "col-sm-6 col-md-10 padding-left"}
     [:h1 {:class "margin-bottom"} "Dreaming of Electric Sheep"
      [:br]
      [:small ": a software engineering blog"]]]]])
