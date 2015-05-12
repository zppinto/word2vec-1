(ns word2vec
  (:require [clojure-word2vec.core :refer [create-input-format get-matches]]  			
            [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.java.io :as io]))

(def stop-words (line-seq (io/reader (io/resource "stopwords.txt"))))

;; A raw textual document to learn from.
(def raw-data-file "textualData.txt")
(def data-file "textualData-processed.txt")

;; Lowercased text, and stopwords and non-textual features removed
(def raw-text (slurp (str "resources/" data-file)))

(def clean-text (->> raw-text
                      clojure.string/lower-case
                      (re-seq #"[a-zA-Z\n]+")
                      (remove (set stop-words))))

;; New textual file
(spit (str "resources/" data-file) (print-str clean-text))

;; Format input data
(def data (create-input-format data-file))

;; Train the model
(def model (clojure-word2vec.core/word2vec data :window-size 15))

;; Print the most important features and their embedded words
(def main-features (take 50 (.getVocab model)))

(doseq [feature main-features]
  (println feature ":" (get-matches model feature)))