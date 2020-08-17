(ns personal-website.middleware
  (:require [prone.middleware :refer [wrap-exceptions]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]))

(def middleware
  [#(wrap-defaults % site-defaults)
   wrap-exceptions
   wrap-reload])
