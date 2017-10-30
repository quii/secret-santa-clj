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

(defn assign-santas [santas]
  (zipmap santas (shuffle-list santas)))

(defn app [] (assign-santas (shuffle (get-santas "santas.csv"))))

(defn -main
  "Secret santa time"
  [& args]
  (println (app)))



