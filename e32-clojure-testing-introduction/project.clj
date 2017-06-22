(defproject e32-clojure-testing-introduction "0.1.0-SNAPSHOT"
  :description "Examples for episode 32: Introduction to Clojure Testing."
  :url "https://lambdaisland.com/episodes/introduction-clojure-testing"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot e32-clojure-testing-introduction.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
