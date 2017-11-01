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
  (prop/for-all [santas (gen/vector gen/string)]
                (let [assignments (assign-giving-and-receiving santas)
                      santa-gives-once (fn [santa] (check-santa-gives-once assignments santa))]
                  (every? true? (map santa-gives-once santas)))
                ))

(defspec santa-gives-once 100 every-santa-gives-once)