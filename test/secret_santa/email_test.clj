(ns secret-santa.email-test
  (:require [clojure.test :refer :all]
            [secret-santa.email :refer :all]))

(deftest loads-config
  (testing "config gets loaded"
    (let [expected-config {:host "smtp.blah.com"
                           :user "santa@blah.com"
                           :pass "Ho Ho Ho"}]
      (is (= expected-config (load-config "conf.example.clj"))))))

(deftest create-email-message
  (testing "Can create an email payload"
    (let [giver-and-receiver {:giver "cj@gmail.com" :receiver "rb@gmail.com"}]
      (is (=
            {:to "cj@gmail.com" :from from :subject subject :body "Please get a lovely gift for rb@gmail.com - the budget is £20"}
            (create-email giver-and-receiver))))))