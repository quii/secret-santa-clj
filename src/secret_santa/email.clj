(ns secret-santa.email)

(require
  '[postal.core :refer :all]
  '[clojure.data.json :as json])

(def from "Secret Santa")
(def subject "Hello Christmas Elf, Santa needs your help!")
(defn body [receiver] (str "Please get a lovely gift for " receiver " - the budget is £20"))

(defn create-email [giver-and-receiver]
  {:to      (:giver giver-and-receiver)
   :from    from
   :subject subject
   :body    (body (:receiver giver-and-receiver))})

(defn create-emails [santas] (map create-email santas))

(def google-deets {:host "smtp.gmail.com" :user "supersecretsanta84@gmail.com" :pass "xxx" :ssl true})

(defn send-email [email]
  (send-message google-deets email))

(defn send-emails [emails]
  (json/write-str (merge (map send-email emails) emails)))