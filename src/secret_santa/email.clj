(ns secret-santa.email
  (:require [clojure.java.io :as io])
  (:import (java.io PushbackReader)))

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

(defn load-config [path]
  (with-open [r (io/reader path)]
    (read (PushbackReader. r))))

(defn google-deets [path]
  (merge (load-config "conf.clj") {:ssl true}))

(defn send-email [email]
  (send-message google-deets email))

(defn send-emails [emails]
  (json/write-str (merge (map send-email emails) emails)))