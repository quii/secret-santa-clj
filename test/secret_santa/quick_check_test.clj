(ns secret-santa.quick-check-test
  (:require [clojure.test :refer :all]
            [secret-santa.core :refer :all]
            [clojure.test.check.clojure-test :refer :all]
            [clojure.test.check :as tc]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            ))

(defn check-santas-appear-once [original-santas assigned-santas]
  (let [appeared-once (distinct? assigned-santas)
        all-appeared (= (set assigned-santas) (set original-santas))]
    (and all-appeared appeared-once)))

(def every-santa-gives-and-receives-once
  (prop/for-all [santas (gen/vector gen/int)]
                (let [assignments (assign-giving-and-receiving santas)
                      givers (keys assignments)
                      receivers (vals assignments)]
                  (true? (check-santas-appear-once santas givers))
                  (true? (check-santas-appear-once santas receivers)))))

(defspec santa-gives-once 100 every-santa-gives-and-receives-once)
