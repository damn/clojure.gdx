(ns clojure.core-inject)

(defn opts-get [options k]
  (when-not (contains? options k)
    (throw (IllegalArgumentException. (str "Unknown Key: " k ". \nOptions are:\n" (sort (keys options))))))
  (k options))
