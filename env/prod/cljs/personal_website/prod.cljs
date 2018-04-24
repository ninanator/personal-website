(ns personal-website.prod
  (:require [personal-website.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
