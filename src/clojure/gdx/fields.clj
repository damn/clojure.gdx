(ns clojure.gdx.fields
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]))

#_(def mappings
  (->> "clojure/gdx/fields.edn"
       io/resource
       slurp
       edn/read-string))

#_(defn opts-get [options k]
  (when-not (contains? options k)
    (throw (IllegalArgumentException. (str "Unknown Key: " k ". \nOptions are:\n" (sort (keys options))))))
  (k options))

#_(opts-get (get mappings 'com.badlogic.gdx.Input$Buttons)
          :forward)
