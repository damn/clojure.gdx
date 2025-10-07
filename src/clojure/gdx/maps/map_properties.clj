(ns clojure.gdx.maps.map-properties
  (:import (com.badlogic.gdx.maps MapProperties)))

(defn add! [^MapProperties map-properties m]
  (doseq [[k v] m]
    (assert (string? k))
    (.put map-properties k v)))

(defn create [m]
  (doto (MapProperties.)
    (add! m)))

(defn ->clj [^MapProperties map-properties]
  (zipmap (.getKeys   map-properties)
          (.getValues map-properties)))
