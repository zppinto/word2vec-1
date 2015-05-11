(ns word2vec
  (:require [clojure-word2vec.core :refer [create-input-format get-matches]]  			
            [clojure.java.io :as io]
            [clojure.edn :as edn]
            [clojure.java.io :as io]))

(defn read-filtered-dataset
  [inpfile]
  (with-open [r (io/reader (java.util.zip.GZIPInputStream.
                  (io/input-stream inpfile)))]
    (mapv edn/read-string (line-seq r))))

(def data
  (create-input-format "ulysses.txt"))

(def model (clojure-word2vec.core/word2vec data :window-size 15))

(println (take 20 (.getVocab model)))

(def matches (get-matches model "woman"))

(def appvec
  (-> (read-filtered-dataset "resources/apple-data.txt.gz") clojure-word2vec.core/word2vec))

(println matches)

(print (take 20 (.getVocab appvec)))

(println (get-matches appvec "radeon"))
(println (get-matches appvec "seagate"))
(println (get-matches appvec "nano"))
(println (get-matches appvec "projector"))
(println (get-matches appvec "raid"))
(println (get-matches appvec "quicktime"))
(println (get-matches appvec "powermac"))