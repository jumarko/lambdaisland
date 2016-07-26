(ns e02-introduction-to-luminus-lambwiki.test.handler
  (:require [clojure.test :refer :all]
            [ring.mock.request :refer :all]
            [e02-introduction-to-luminus-lambwiki.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response ((app) (request :get "/"))]
      (is (= 200 (:status response)))))

  (testing "not-found route"
    (let [response ((app) (request :get "/invalid"))]
      (is (= 404 (:status response))))))
