(ns secret-santa.core-test
  (:require [clojure.test :refer :all]
            [secret-santa.core :refer :all]
            ))


;; Possible property based tests
;; Everyone should have a present!
;; Everyone should give a present!
;; It would be *nice* if the giver and receiver aren't a pair

(deftest assign-secret-santas
  (testing "Secret santas get assigned"
           (let [
          santas `(:a :b :c :d)
          expected {:a :b, :b :c, :c :d, :d :a}
          ]
                (is (= expected (assign-giving-and-receiving santas)))))
  )

(deftest render-secret-santas
         (testing "Render secret santa infos nicely"
                  (let [
                        assigned-santas {:a :b, :c :d}
                        expected '({:giver :a, :receiver :b},
                                    {:giver :c, :receiver :d})
                        ]
                       (is (= expected (render assigned-santas)))
                       )))

(deftest load-santas
  (testing "Can load santas from csv"
    (let [
          filename "santas.csv"
          expectedSantas '("ruth@gmail.com", "simone@gmail.com", "jayne@gmail.com")
          ]
      (is (= expectedSantas (get-santas filename)))
      )))