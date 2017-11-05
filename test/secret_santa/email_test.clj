(ns secret-santa.email-test
  (:require [clojure.test :refer :all]
            [secret-santa.email :refer :all]
            ))

(deftest loads-config
  (testing "config gets loaded"
    (let [expected-config {:host "smtp.blah.com"
                           :user "santa@blah.com"
                           :pass "Ho Ho Ho"}]
      (is (= expected-config (load-config "conf.example.clj"))))))