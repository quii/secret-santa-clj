(ns secret-santa.core-test
  (:require [clojure.test :refer :all]
            [secret-santa.core :refer :all]
            ))

(deftest secret-santa
  (testing "Secret santas get assigned"
    (let [
          santas `(:a :b :c :d)
          expected {:a :b, :c :d}
          ]
      (is (= expected (assign-santas santas)))))
  )


(deftest load-santas
  (testing "Can load santas from csv"
    (let [
          filename "santas.csv"
          expectedSantas '("ruth@gmail.com", "simone@gmail.com", "jayne@gmail.com")
          ]
      (is (= expectedSantas (get-santas filename)))
      )))