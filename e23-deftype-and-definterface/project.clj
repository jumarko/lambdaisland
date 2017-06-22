
(defproject e23-deftype-and-definterface "0.1.0-SNAPSHOT"
  :description "Examples for lambdaisland episode 23; deftype and definterface"
  :url "https://lambdaisland.com/episodes/deftype_definterface"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot e23-deftype-and-definterface.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
