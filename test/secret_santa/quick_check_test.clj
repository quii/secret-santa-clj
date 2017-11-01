(ns secret-santa.quick-check-test
  (:require [clojure.test :refer :all]
            [secret-santa.core :refer :all]
            [clojure.test.check.clojure-test :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            ))

(defn check-santa-gives-once [assignments santa]
  (= 1 (count
         (filter #(= (first %) santa) assignments))))

(def every-santa-gives-once
  (prop/for-all [n (gen/vector gen/string)]
                (let [assignments (assign-giving-and-receiving n)]
                  (every? true? (map #(check-santa-gives-once assignments %) n)))
                ))




(tc/quick-check 100 every-santa-gives-once)

(defspec first-element-is-min-after-sorting                 ;; the name of the test
         100                                                ;; the number of iterations for test.check to test
         every-santa-gives-once)