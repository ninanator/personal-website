(ns personal-website.contentful
  (:require [clj-http.client :as client]
            [personal-website.util :as util]
            [config.core :refer [env]]))

(def ^:private cdn-domain-url (:cdn-domain-url env))
(def ^:private access-token   (:cdn-access-token env))
(def ^:private space          (:cdn-space env))

(def ^:private categories {:opinions                 (:cdn-opinions-token env)
                           :algorithms               (:cdn-algorithms-token env)
                           :data-structures          (:cdn-data-structures-token env)
                           :fundamental-concepts     (:cdn-findamental-concepts-token env)
                           :languages-and-frameworks (:cdn-languages-and-frameworks-token env)})

(def ^:private contentful-entries-api (format "%s/spaces/%s/entries", cdn-domain-url, space))

(defn get-contentful-categories []
  (client/get contentful-entries-api {:as :json
                                      :query-params {"access_token" access-token
                                                     "content_type" util/category-content-type}}))

(defn get-contentful-posts []
  (client/get contentful-entries-api {:as :json
                                      :query-params {"access_token" access-token,
                                                     "content_type" util/post-content-type
                                                     "order"        "-fields.createDate"}}))

(defn get-contentful-post [post-slug]
  (client/get contentful-entries-api {:as :json
                                      :query-params {"access_token" access-token
                                                     "content_type" util/post-content-type
                                                     "fields.slug"  post-slug}}))

(defn get-contentful-posts-by-category [category]
  (client/get contentful-entries-api {:as :json
                                      :query-params {"access_token"           access-token
                                                     "content_type"           util/post-content-type
                                                     "fields.category.sys.id" (get categories (keyword category))
                                                     "order"                  "-fields.createDate"}}))
