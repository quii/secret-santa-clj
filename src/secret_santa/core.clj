(ns secret-santa.core
  (:gen-class))

(require '[clojure.data.csv :as csv]
         '[clojure.java.io :as io]
         '[postal.core :refer :all]
         '[clojure.data.json :as json])

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

(def from "Secret Santa")
(def subject "Hello Christmas Elf, Santa needs your help!")
(defn body [receiver] (str "Please get a lovely gift for " receiver))

(defn create-email [giver-and-receiver]
  {:to      (:giver giver-and-receiver)
   :from    from
   :subject subject
   :body    (body (:receiver giver-and-receiver))})

(defn create-emails [santas] (map create-email santas))

(defn app [args]
  (->
    (get-santas (first args))
    shuffle
    assign-giving-and-receiving
    render
    create-emails
    json/write-str))


(def google-deets {:host "smtp.gmail.com" :user "supersecretsanta84@gmail.com " :pass "1PepperPot" :ssl true})

(defn send-email [email]
  (send-message google-deets email))

;(defn -main
;  "Secret santa time"
;  [& args]
;  (println (app args)))

(defn -main
  "Secret santa time"
  [& args]
  (->
    {:giver "ruthmoog@gmail.com" :receiver "qui666@gmail.com"}
    create-email
    send-email))



