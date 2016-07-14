(ns lambdaisland.01-some
  (:require [compojure.core]))

(pos? 3)

(pos? -2)

;; function must be true for "some" of the items
(some pos? [-5 42 43]) ;=> true

(some pos? [-5 -6 -7]) ;=> nil

;; it's similar to applying the function to each item, and then combining the results with a logical or.

(or
 (pos? -5)
 (pos? 42)
 (pos? 43))
;;=>> true

(or
 (pos? -5)
 (pos? -6)
 (pos? -7))
;;=> false


;; what if we use function that returns other value than boolean (true or false)?
;; => both or and some will return the first truthy value it encounters.
(or
 nil
 false
 "greetings!")
;;=> "greetings!"

(some (fn [x] x)
      [nil false "greetings!"])
;;=> "greetings!"


(def shelf
  [{:cake "Pineapple Cake"}
   {:tea "Jinxuan Wulong Tea"}
   {:tofu "Stinky Tofu"}])

;; Clojure keywords can be invoked just like functions.
(:tea {:cake "Jinxuan Wulong Tea"})
(:tea {:tea "Jinxuan Wulong Tea"})

(some :tea shelf)

;;; 3 specific use cases for some

;; checking if a collection contains a certain value
;;
;; In clojure there's no generic function for checking if a collection contains a certain value (because it's not always efficient to implement -> would require linear scan).
;; Sets do support this operation .
(#{:a :b} :a) ;=> :a
(#{:a :b} :c) ;=> nil
;; sometimes the linear scan is exactly what you want
(some #{:a} [:b :c :d])
(some #{:a} [:b :a :d])


;; as an alternative for "apply or"
(def v [nil false 5 nil 7])
;; suppose you want to find the first value that is truthy
(filter identity v) ;=> (5 7)
(first (filter identity v)) ;=> 5
;; since we have items in vector we would need to expand them
(apply or v) ;=> Can't take value of macro
;; => whenever you find yourself wanting to type "apply or",
;; type "some identity" instead
(some identity v)


;; as a way of “stacking” request handlers
(defn home-page-handler [req]
  {:status 200
   :body "Hello, home"})

(home-page-handler {:method :get, :uri "/"})

;; let's modify our handler to respond only to root uri
(defn home-page-handler [{:keys [uri method] :as req}]
  (if (and (= method :get) (= uri "/"))
    {:status 200
     :body "Hello, home"}))

(home-page-handler {:method :get, :uri "/"})
(home-page-handler {:method :get, :uri "/foo"})

;; let's add about page handler
(defn about-page-handler [{:keys [uri method] :as req}]
  (if (and (= :get method) (= "/about" uri))
    {:status 200
     :body "All about us!"}))

(about-page-handler {:method :get :uri "/"})
(about-page-handler {:method :get :uri "/about"})

;; handler are selective, thus we can combine them
(defn application-routes [request]
  (or
   (home-page-handler request)
   (about-page-handler request)))

(application-routes {:method :get :uri "/"})
(application-routes {:method :get :uri "/about"})

;; Instead of calling same function several times, each time with a different argument,
;; we now call a different function each time, always with the same argument
;; Luckily, functions are just data, so we can iterate over them as well
;; Now, it's trivial to add new handlers to the list.
(defn application-routes [request]
  (some #(% request)
        [home-page-handler
         about-page-handler]))

(application-routes {:method :get :uri "/"})
(application-routes {:method :get :uri "/about"})

;; All this stuff is already implemented in compojure library
(defn application-routes [request]
  ((compojure.core/routes
    home-page-handler
    about-page-handler) request))
(application-routes {:method :get :uri "/"})
(application-routes {:method :get :uri "/about"})

;; or even more concise
(compojure.core/defroutes application-routes
  home-page-handler
  about-page-handler)
(application-routes {:method :get :uri "/"})
(application-routes {:method :get :uri "/about"})
