(ns secret-santa.core
    (:gen-class))

(require '[clojure.data.csv :as csv]
         '[clojure.java.io :as io])

(defn get-santas-data [filename]
      (with-open [reader (io/reader filename)]
                 (doall
                   (csv/read-csv reader)))
      )

(defn get-santas [filename]
      (rest (map first (get-santas-data filename))))

(defn shuffle-list [x]
      (take (count x) (rest (cycle x))))

(defn assign-giving-and-receiving [santas]
  (let [unique-santas (distinct santas)]
    (zipmap unique-santas (shuffle-list unique-santas))))

(defn render [assignments]
      (map (fn [[giver receiver]] {:giver giver :receiver receiver}) assignments))

(defn app [args]
      (->
        (get-santas (first args))
        assign-giving-and-receiving
        render))

(defn -main
      "Secret santa time"
      [& args]
      (println (app args)))



