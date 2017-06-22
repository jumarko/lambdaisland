(ns e32-clojure-testing-introduction.core)

(defn assert= [x y]
  (when (not= x y)
    (throw (AssertionError. (str "Expected " (pr-str y) " but got " (pr-str x))))))

(defn double-fn [x] (* x 2))

(defn ^{:test #'test-double-fn} test-double-fn []
  (assert= (double-fn 5) 10))

#_(->> (all-ns)
       (mapcat ns-interns)
       (filter #(->> % first str (re-find #"^test-"))))


(defn run-all-tests []
  (let [])
  (doseq [test (->> (all-ns)
                    (mapcat ns-interns)
                    (keep #(-> % second meta :test)))]
    (test)))

#_(run-all-tests)

;; let's write macro 
(defmacro defcheck [name & body]
  `(defn ~(with-meta name {:test `(var ~name)})
     []
     ~@body))

(defcheck test-double-fn-2
  (assert= (double-fn 20) 40))

(meta #'test-double-fn-2)

#_(run-all-tests)


;;; Test reporting

(declare ^:dynamic *test-results* )

(defn update-report! [& args]
  (apply swap! *test-results* update args))

(defn assert= [x y]
  (if (= x y)
    (update-report! :pass inc)
    (do
      (update-report! :fail inc)
      (update-report! :messages conj
                      (str "Expected " (pr-str y) " but got " (pr-str x))))))

(defn- print-report! [{:keys [assertions tests pass fail messages]}]
  (run! #(println % "\n\n") messages)
  (println "Ran" tests "tests containing" assertions "assertions.")
  (println fail "failures."))


(defn run-all-tests []
  (binding [*test-results* (atom {:assertions 0 :tests 0 :pass 0 :fail 0 :messages []})]
    (doseq [test (->> (all-ns)
                      (mapcat ns-interns)
                      (keep #(-> % second meta :test)))]
      (update-report! :tests inc)
      (test)
      (print-report! @*test-results*))))

#_(run-all-tests)
