(ns secret-santa.core-test
  (:require [clojure.test :refer :all]
            [secret-santa.core :refer :all]
            [secret-santa.email :refer :all]
            ))

(deftest assign-secret-santas

  (testing "Secret santas get assigned"
    (let [santas `(:a :b :c :d)
          expected {:a :b, :b :c, :c :d, :d :a}]
      (is (= expected (assign-giving-and-receiving santas)))))

  (testing "duplicated santas are removed"
    (let [duplicated-santas [:a :a :b :a]
          expected {:a :b, :b :a}]
      (is (= expected (assign-giving-and-receiving duplicated-santas))))))

(deftest render-secret-santas
  (testing "Render secret santa infos nicely"
    (let [assigned-santas {:a :b, :c :d}
          expected '({:giver :a, :receiver :b},
                      {:giver :c, :receiver :d})]
      (is (= expected (render assigned-santas))))))

(deftest load-santas
  (testing "Can load santas from csv"
    (let [filename "santas.csv"
          expectedSantas '("qui666+santa1@gmail.com", "qui666+santa2@gmail.com", "qui666+santa3@gmail.com")]
      (is (= expectedSantas (get-santas filename))))))

(deftest create-email-message
  (testing "Can create an email payload"
    (let [giver-and-receiver {:giver "cj@gmail.com" :receiver "rb@gmail.com"}]
      (is (=
            {:to "cj@gmail.com" :from from :subject subject :body "Please get a lovely gift for rb@gmail.com - the budget is £20"}
            (create-email giver-and-receiver))))))