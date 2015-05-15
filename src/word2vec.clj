(ns word2vec
  (:require [clojure-word2vec.core :refer [create-input-format get-matches]]  			                      
            [clojure.java.io :as io]
            [clojure.tools.cli :refer [parse-opts]])
  (:gen-class))

(defn run
	[data-file num-features]
	(let [data (create-input-format data-file) ;; Format input data
		  model (clojure-word2vec.core/word2vec data) ;; Train the model
		  main-features (take num-features (.getVocab model))] 

		;; Print the most important features and their embedded words  
		(doseq [feature main-features]
  			(println feature ":" (get-matches model feature)))))

(def cli-options
  [["-f" "--file DATA-FILE" "Textual data file" :default "./textualData.txt"]
   ["-n" "--num-features NUM-FEATURES" "Number of features to print" :default 100]
   ["-h" "--help"]])

(defn -main [& args]
	(let [opts (parse-opts args cli-options)]
		  (if (:help (:options opts))
		  	(println (:summary opts))		  
		  	(let [data-file (:file (:options opts))
		  		  num-features (:num-features (:options opts))]
			(run data-file (int num-features))))))