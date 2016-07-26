(ns e02-introduction-to-luminus-lambwiki.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [e02-introduction-to-luminus-lambwiki.layout :refer [error-page]]
            [e02-introduction-to-luminus-lambwiki.routes.home :refer [home-routes]]
            [compojure.route :as route]
            [e02-introduction-to-luminus-lambwiki.env :refer [defaults]]
            [mount.core :as mount]
            [e02-introduction-to-luminus-lambwiki.middleware :as middleware]))

(mount/defstate init-app
                :start ((or (:init defaults) identity))
                :stop  ((or (:stop defaults) identity)))

(def app-routes
  (routes
    (-> #'home-routes
        (wrap-routes middleware/wrap-csrf)
        (wrap-routes middleware/wrap-formats))
    (route/not-found
      (:body
        (error-page {:status 404
                     :title "page not found"})))))


(defn app [] (middleware/wrap-base #'app-routes))
