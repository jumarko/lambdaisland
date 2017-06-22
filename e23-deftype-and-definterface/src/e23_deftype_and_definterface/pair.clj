(ns e23-deftype-and-definterface.pair)

(deftype Pair [x y]
  java.lang.Object
  (equals [this other]
    (and (instance? Pair other)
         (= x (.x other))
         (= y (.y other)))))

(= (Pair. 1 2) (Pair. 1 2))
(= (Pair. 1 2) (Pair. 1 3))
