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

(defn assign-santas [santas]
  (apply assoc {} santas))

(defn app [] (assign-santas (shuffle (get-santas "santas.csv"))))

(defn -main
  "Secret santa time"
  [& args]
  (println (app)))



