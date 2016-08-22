(ns e12-clojure-keyword-arguments.core
  (:require [medley.core :as m])
  )

;;; Motivation
(defn tea-info-sheet [& {:keys [name type description]}]
  [:div.tea
   [:h2.name name (if type
                    [:em "(" [:a {:href (str "/type/" type)} type] ")"])]
   [:p.description description]])

(tea-info-sheet :name "Alishan"
                :type "Oolong"
                :description "Hand picked high-mountain tea grown close to the Yushan national park.")

[:div.tea
 [:h2.name "Alishan" [:em "(" [:a {:href "/type/Oolong"} "Oolong"] ")"]]
 [:p.description
  "Hand picked high-mountain tea grown close to the Yushan national park."]]


;;; Demo - show how arguments are destructured
(defn demo [first-arg & rest-of-the-args]
  {:first first-arg
   :rest rest-of-the-args})

(demo 1 2 3 4)

(defn separate [separator & rest]
  (apply str (interpose separator rest)))

(separate "," "foo" "bar" "baz")


;;; If you treat sth like a map, Clojure will convert it into the map for you

(defn demo [coll]
  (let [{name :name
         type :type} coll]
    {"its name" name
     "its type" type}))

(demo {:name "Alishan" :type "Oolong"})
;; following doesn't work!!!
(demo [:name "Alishan" :type "Oolong"])
(demo '(:name "Alishan" :type "Oolong"))



;;; using :or for fallback values
(defn tea-info-sheet [& {:keys [name type description]
                         :or {name "Generic Tea"
                              description "No description given"}}]
  [:div.tea
   [:h2.name name (if type
                    [:em "(" [:a {:href (str "/type/" type)} type] ")"])]
   [:p.description description]])

(tea-info-sheet :name "Alishan")


;;; product-title function

(defn product-title [& {:keys [name type]}]
  [:h2.name name (if type
                   [:em "(" [:a {:href (str "/type/" type)} type] ")"])])

(defn tea-info-sheet [& {:keys [name type description]
                         :or {name "Generic Tea"
                              description "No description given"}}]
  [:div.tea
   (product-title :name name
                  :type type)
   [:p.description description]])

(tea-info-sheet :name "Alishan")


;;; using :as to capture all arguments as map and pass it to product-title function
(defn tea-info-sheet [& {:keys [name type description]
                         :or {name "Generic Tea"
                              description "No description given"}
                         :as tea-props}]
  [:div.tea
   ;; notice we can't just apply the arguments as they are but we need to use awkward construct
   (apply product-title (apply concat tea-props))
   [:p.description description]])

(tea-info-sheet :name "Alishan")

;; the better solution is to use "mapply" - not bundled with Clojure by default but already there in medly and similar libraries
(defn tea-info-sheet [& {:keys [name type description]
                         :or {name "Generic Tea"
                              description "No description given"}
                         :as tea-props}]
  [:div.tea
   ;; notice we can't just apply the arguments as they are but we need to use awkward construct
   (m/mapply product-title tea-props)
   [:p.description description]])

(tea-info-sheet :name "Alishan")



;;; Finally, we can realize that we need to use map instead of keyword arguments
;;; e.g. because the :as doesn't work properly with default values

;; notice the name is nil
(tea-info-sheet :type "My type")

;; let's fix this by using maps

(defn product-title [{:keys [name type]
                      :or {name "Generic Tea"
                           description "No description given"}}]
  [:h2.name name (if type
                   [:em "(" [:a {:href (str "/type/" type)} type] ")"])])

(defn tea-info-sheet [{:keys [name type description]
                       :or {name "Generic Tea"
                            description "No description given"}
                       :as tea-props}]
  [:div.tea
   (product-title tea-props)
   [:p.description description]])

(tea-info-sheet {:name "Alishan"
                 :type "Oolong"
                 :description "Hand picked high-mountain tea grown close to the Yushan national park."})

(tea-info-sheet {:type "Oolong"})


;; we can also provide handy constructor function
;; this function can be used for validation, etc.

(defn tea-props [& {:as props}]
  (merge {:name "Generic Tea"
          :description "No description given"} props))

(defn product-title [{:keys [name type]}]
  [:h2.name name (if type
                   [:em "(" [:a {:href (str "/type/" type)} type] ")"])])

(defn tea-info-sheet [{:keys [name type description]
                       :as tea-props}]
  [:div.tea
   (product-title tea-props)
   [:p.description description]])

(tea-info-sheet (tea-props :name "Alishan"
                           :type "Oolong"
                           :description "Hand picked high-mountain tea grown close to the Yushan national park."))

(tea-info-sheet (tea-props  :type "Oolong"))
