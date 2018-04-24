(defproject personal-website "1.0.0"
  :description "Personal Website"
  :url "http://ninablanson.com/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :min-lein-version "2.5.0"
  :uberjar-name "personal-website.jar"
  :main personal-website.server

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.238" :scope "provided"]
                 [reagent "0.8.0"]
                 [reagent-utils "0.3.1"]
                 [ring-server "0.5.0"]
                 [ring "1.6.3"]
                 [ring/ring-defaults "0.3.1"]
                 [compojure "1.6.0"]
                 [hiccup "1.0.5"]
                 [yogthos/config "1.1.1"]
                 [secretary "1.2.3"]
                 [venantius/accountant "0.2.4" :exclusions [org.clojure/tools.reader]]
                 [clj-http "3.8.0"]
                 [cljs-ajax "0.7.3"]
                 [markdown-clj "1.0.2"]]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-asset-minifier "0.2.7" :exclusions [org.clojure/clojure]]]
  :ring {:handler personal-website.handler/app
         :uberwar-name "personal-website.war"}

  :clean-targets ^{:protect false} [:target-path [:cljsbuild :builds :app :compiler :output-dir]
                                                 [:cljsbuild :builds :app :compiler :output-to]]

  :source-paths ["src/clj" "src/cljc"]
  :resource-paths ["resources" "target/cljsbuild"]
  :minify-assets {:assets {"resources/public/css/site.min.css" "resources/public/css/site.css"}}

  :cljsbuild {:builds {:app {:source-paths ["src/cljs" "src/cljc"]
                             :compiler {:output-to "target/cljsbuild/public/js/app.js"
                                        :output-dir "target/cljsbuild/public/js/out"
                                        :asset-path   "/js/out"
                                        :optimizations :none
                                        :pretty-print  true}}}}

  :profiles {:dev {:repl-options {:init-ns personal-website.repl}
                   :dependencies [[ring/ring-mock "0.3.2"]
                                  [ring/ring-devel "1.6.3"]
                                  [prone "1.5.1"]
                                  [lein-figwheel "0.5.11"
                                   :exclusions [org.clojure/core.memoize
                                                ring/ring-core
                                                org.clojure/clojure
                                                org.ow2.asm/asm-all
                                                org.clojure/data.priority-map
                                                org.clojure/tools.reader
                                                org.clojure/clojurescript
                                                org.clojure/core.async
                                                org.clojure/tools.analyzer.jvm]]
                                  [org.clojure/tools.nrepl "0.2.13"]
                                  [com.cemerick/piggieback "0.2.2"]
                                  [pjstadig/humane-test-output "0.8.3"]]
                   :source-paths ["env/dev/clj"]
                   :resource-paths ["config/prod"]
                   :plugins [[lein-figwheel "0.5.11"
                              :exclusions [org.clojure/core.memoize
                                           ring/ring-core
                                           org.clojure/clojure
                                           org.ow2.asm/asm-all
                                           org.clojure/data.priority-map
                                           org.clojure/tools.reader
                                           org.clojure/clojurescript
                                           org.clojure/core.async
                                           org.clojure/tools.analyzer.jvm]]]

                   :injections [(require 'pjstadig.humane-test-output)
                                (pjstadig.humane-test-output/activate!)]
                   :figwheel {:http-server-root "public"
                              :server-port 3449
                              :nrepl-port 7002
                              :nrepl-middleware ["cemerick.piggieback/wrap-cljs-repl"]
                              :css-dirs ["resources/public/css"]
                              :ring-handler personal-website.handler/app}
                   :cljsbuild {:builds {:app {:source-paths ["env/dev/cljs"]
                                              :compiler {:main "personal-website.dev"
                                                         :source-map true}}}}}
             :prod {:resource-paths ["config/prod"]}
             :uberjar {:hooks [minify-assets.plugin/hooks]
                       :source-paths ["env/prod/clj"]
                       :prep-tasks ["compile" ["cljsbuild" "once"]]
                       :aot :all
                       :omit-source true
                       :cljsbuild {:jar true
                                   :builds {:app {:source-paths ["env/prod/cljs"]
                                                  :compiler {:optimizations :advanced
                                                             :pretty-print false}}}}}})
