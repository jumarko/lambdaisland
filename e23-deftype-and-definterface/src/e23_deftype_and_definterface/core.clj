(ns e23-deftype-and-definterface.core
  (:require [e23-deftype-and-definterface.binary-tree :as bt])
  (:gen-class))

(def EMPTY_TREE (bt/->Node nil nil nil))

(conj EMPTY_TREE 1 2 3)

;;; notice that output is sorted
(def tree (into EMPTY_TREE (take 10 (repeatedly #(rand-int 100)))))
(seq tree)
(rest tree)
(conj tree)
;; try =
(= (into EMPTY_TREE [5 4 6 9]) (into EMPTY_TREE [5 4 6 9]))
(= (into EMPTY_TREE [5 4 6 9 10]) (into EMPTY_TREE [5 4 6 9]))

;; Notice that Node also need to implement clojure.lang.Counted to work properly
(count EMPTY_TREE)
(count (into EMPTY_TREE [5 4 6 9 10]))

;; in CLJS you have to use .-value
#_(.-value (bt/->Node 17 nil nil))
(defn- play-with-nodes []
  (let [node (bt/->Node 17 nil nil)]
    (println
     (.value (bt/->Node 17 nil nil)))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (play-with-nodes))
