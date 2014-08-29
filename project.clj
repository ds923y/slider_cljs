(defproject hello-world "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2173"]
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [om "0.5.0"]]

  :plugins [[lein-cljsbuild "1.0.2"]]

  :source-paths ["src"]

  :cljsbuild {
    :builds [{:id "hello-world"
              :source-paths ["src"]
              :compiler {
                :libs ["MyOmSlide.js" "slider.js"]
                :output-to "hello_world.js"
                :output-dir "out"
                :optimizations :none
                :source-map true}}
             {:id "production"
            :source-paths ["src"]
            :compiler {
              :libs ["MyOmSlide.js" "slider.js"]
              :output-to "st.js"
              :optimizations :advanced
              :pretty-print false
              :preamble ["react/react.min.js"]
              :externs ["react/react.min.js"]}}]})
