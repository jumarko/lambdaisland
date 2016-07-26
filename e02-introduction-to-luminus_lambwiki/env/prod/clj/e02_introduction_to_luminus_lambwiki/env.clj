(ns e02-introduction-to-luminus-lambwiki.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[e02-introduction-to-luminus_lambwiki started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[e02-introduction-to-luminus_lambwiki has shut down successfully]=-"))
   :middleware identity})
