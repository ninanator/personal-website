(ns personal-website.contentful
  (:require [clj-http.client :as client]
            [personal-website.util :as util]
            [config.core :refer [env]]))

(def ^:private cms-domain-url (:cms-domain-url env))
(def ^:private access-token   (:cms-access-token env))
(def ^:private space          (:cms-space env))
(def ^:private get-posts-order  "-fields.createDate")
(def ^:private get-posts-select "sys,fields.category,fields.createDate,fields.slug,fields.summary,fields.title")
(def ^:private categories {:opinions                 (:cms-opinions-token env)
                           :algorithms               (:cms-algorithms-token env)
                           :data-structures          (:cms-data-structures-token env)
                           :fundamental-concepts     (:cms-findamental-concepts-token env)
                           :languages-and-frameworks (:cms-languages-and-frameworks-token env)})

(def ^:private contentful-entries-api (format "%s/spaces/%s/entries", cms-domain-url, space))

(defn get-contentful-categories []
  (client/get contentful-entries-api {:as :json
                                      :oauth-token access-token
                                      :query-params {"content_type" util/category-content-type}}))
(defn get-contentful-posts []
  (client/get contentful-entries-api {:as :json
                                      :oauth-token access-token
                                      :query-params {"content_type" util/post-content-type
                                                     "order"        get-posts-order
                                                     "select"       get-posts-select}}))
(defn get-contentful-post [post-slug]
  (client/get contentful-entries-api {:as :json
                                      :oauth-token access-token
                                      :query-params {"content_type" util/post-content-type
                                                     "fields.slug"  post-slug}}))
(defn get-contentful-posts-by-category [category]
  (client/get contentful-entries-api {:as :json
                                      :oauth-token access-token
                                      :query-params {"content_type"           util/post-content-type
                                                     "fields.category.sys.id" (get categories (keyword category))
                                                     "order"                  get-posts-order
                                                     "select"                 get-posts-select}}))
