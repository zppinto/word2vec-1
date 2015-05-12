(ns word2vec
  (:require [clojure-word2vec.core :refer [create-input-format get-matches]]  			
            [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.java.io :as io]))

;; A textual document to learn from.
(def data (create-input-format "textualData.txt"))

;; Train the model
(def model (clojure-word2vec.core/word2vec data :window-size 15))

;; Print the most important features and their embedded words
(def main-features (remove [] (.getVocab model)))

(doseq [feature main-features]
  (println feature ":" (get-matches model feature)))