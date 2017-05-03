(require '[cljs.build.api :as cljs])

(def compiler-options {:output-to "compiled.js"
                       #_(main 'demo.main)
                       #_(:optimizations :none) ;; default -> don't invoke Google Closure compiler
                       :source-map "compiled.js.map"
                       :output-dir "out"
                       :optimizations :advanced
                       })

(cljs/build "cljs" compiler-options)
