(ns e02-introduction-to-luminus-lambwiki.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [e02-introduction-to-luminus-lambwiki.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[e02-introduction-to-luminus_lambwiki started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[e02-introduction-to-luminus_lambwiki has shut down successfully]=-"))
   :middleware wrap-dev})
