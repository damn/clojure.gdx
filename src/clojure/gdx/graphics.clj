(ns clojure.gdx.graphics
  (:import (com.badlogic.gdx Graphics)))

(defn delta-time [^Graphics graphics]
  (.getDeltaTime graphics))

(defn frames-per-second [^Graphics graphics]
  (.getFramesPerSecond graphics))

(defn new-cursor [^Graphics graphics pixmap hotspot-x hotspot-y]
  (.newCursor graphics pixmap hotspot-x hotspot-y))

(defn set-cursor! [^Graphics graphics cursor]
  (.setCursor graphics cursor))
