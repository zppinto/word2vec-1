(ns word2vec
  (:require [clojure-word2vec.core :refer [create-input-format get-matches]]  			                      
            [clojure.java.io :as io]))

(def data-file "textualData.txt")

;; Format input data
(def data (create-input-format data-file))

;; Train the model
(def model (clojure-word2vec.core/word2vec data))

;; Print the most important features and their embedded words
(def main-features (take 200 (.getVocab model)))

(doseq [feature main-features]
  (println feature ":" (get-matches model feature)))