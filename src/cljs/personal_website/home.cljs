(ns personal-website.home)

(defn home-body []
  [:div {:class "row"}
    [:div {:class "col-xs-1 col-md-2"}]
    [:div {:class "col-xs-6 col-sm-6 col-md-4"}
      [:img {:class "home-image" :src "/assets/cat.png"}]]
    [:div {:class "col-xs-12 col-sm-6 col-md-6"}
      [:h1 "Hello! I'm Nina."
        [:br]
        [:small "안녕하세요! 니나 입니다."]]
      [:h3 "I'm a software developer in Austin, Texas."
        [:br]
        [:small "오스틴, 텍사스에 사는 소프터웨어 개발자입니다."]]
      [:h3 "Stay a while and listen."
        [:br]
        [:small "\"잠시 내 이야기 좀 들어보게나.\""]]
      [:h3
        [:a {:href "https://www.linkedin.com/pub/nina-blanson/93/565/692" :target "_blank"}
          [:img {:class "social-logo" :src "/assets/linked.png"}]]
        " "
        [:a {:href "https://github.com/ninanator" :target "_blank"}
          [:img {:class "social-logo" :src "/assets/github.png"}]]]]])
