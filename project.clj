(defproject personal-website "1.0.0"
  :description "Personal Website"
  :url "http://ninablanson.com/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.9.0"
  :uberjar-name "personal-website.jar"
  :main personal-website.server

  :dependencies [[cljs-ajax "0.8.1"]
                 [clj-http "3.10.2"]
                 [hiccup "1.0.5"]
                 [markdown-clj "1.10.5"]
                 [metosin/reitit "0.5.1"]
                 [org.clojure/clojure "1.10.0"]
                 [org.clojure/clojurescript "1.10.773" :scope "provided"]
                 [reagent "0.10.0"]
                 [reagent-utils "0.3.3"]
                 [ring "1.8.1"]
                 [ring/ring-defaults "0.3.2"]
                 [ring-server "0.5.0"]
                 [pez/clerk "1.0.0"]
                 [venantius/accountant "0.2.5" :exclusions [org.clojure/tools.reader]]
                 [yogthos/config "1.1.7"]]

  :plugins [[lein-environ "1.1.0"]
            [lein-cljsbuild "1.1.7"]
            [lein-asset-minifier "0.4.6" :exclusions [org.clojure/clojure]]]

  :ring {:handler personal-website.handler/app
         :uberwar-name "personal-website.war"}

  :clean-targets ^{:protect false} [:target-path [:cljsbuild :builds :app :compiler :output-dir]
                                                 [:cljsbuild :builds :app :compiler :output-to]]

  :source-paths ["src/clj" "src/cljc" "src/cljs"]
  :resource-paths ["resources" "target/cljsbuild"]
  :minify-assets [[:css {:source "resources/public/css/site.css" :target "resources/public/css/site.min.css"}]]

  :cljsbuild {:builds {:min {:source-paths ["src/cljs" "src/cljc" "env/prod/cljs"]
                             :compiler {:output-to "target/cljsbuild/public/js/app.js"
                                        :output-dir "target/cljsbuild/public/js"
                                        :source-map "target/cljsbuild/public/js/app.js.map"
                                        :optimizations :advanced
                                        :infer-externs true
                                        :pretty-print  false}}
                       :app {:source-paths ["src/cljs" "src/cljc" "env/dev/cljs"]
                             :figwheel {:on-jsload "personal-website.core/mount-root"}
                             :compiler {:main "personal-website.dev"
                                        :asset-path "/js/out"
                                        :output-to "target/cljsbuild/public/js/app.js"
                                        :output-dir "target/cljsbuild/public/js/out"
                                        :source-map true
                                        :optimizations :none
                                        :pretty-print  true}}}}

  :figwheel {:http-server-root "public"
             :server-port 3449
             :nrepl-port 7002
             :nrepl-middleware [cider.piggieback/wrap-cljs-repl]
             :css-dirs ["resources/public/css"]
             :ring-handler personal-website.handler/app}

  :profiles {:dev {:repl-options {:init-ns personal-website.repl}
                   :dependencies [[binaryage/devtools "1.0.2"]
                                  [cider/piggieback "0.5.1"]
                                  [figwheel-sidecar "0.5.20"]
                                  [nrepl "0.8.0"]
                                  [pjstadig/humane-test-output "0.10.0"]
                                  [prone "2020-01-17"]
                                  [ring/ring-devel "1.8.1"]
                                  [ring/ring-mock "0.4.0"]]
                   :source-paths ["env/dev/clj"]
                   :resource-paths ["config/dev"]
                   :plugins [[lein-figwheel "0.5.20"]]
                   :injections [(require 'pjstadig.humane-test-output)
                                (pjstadig.humane-test-output/activate!)]
                   :env {:dev true}}
             :uberjar {:hooks [minify-assets.plugin/hooks]
                       :source-paths ["env/prod/clj"]
                       :resource-paths ["config/prod"]
                       :prep-tasks ["compile" ["cljsbuild" "once" "min"]]
                       :env {:production true}
                       :aot :all
                       :omit-source true}})
