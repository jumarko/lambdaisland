(ns e02-introduction-to-luminus-lambwiki.routes.home
  (:require [e02-introduction-to-luminus-lambwiki.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [e02-introduction-to-luminus-lambwiki.db.core :refer [find-page-by-uri-slug]]
            [ring.util.http-response :refer [found]]))

(defn wiki-page [page]
  (layout/render
    "wiki-page.html" page))

(defroutes home-routes
  (GET "/" []
    (found "/home"))
  (GET "/:uri_slug" [uri_slug]
    (if-let [page (find-page-by-uri-slug {:uri_slug uri_slug})]
      (wiki-page page))))