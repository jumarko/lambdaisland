(ns user
  (:require [mount.core :as mount]
            e02-introduction-to-luminus-lambwiki.core))

(defn start []
  (mount/start-without #'e02-introduction-to-luminus-lambwiki.core/repl-server))

(defn stop []
  (mount/stop-except #'e02-introduction-to-luminus-lambwiki.core/repl-server))

(defn restart []
  (stop)
  (start))


