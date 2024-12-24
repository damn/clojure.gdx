(ns clojure.gdx.graphics
  (:import (com.badlogic.gdx Graphics)))

(defn cursor [graphics pixmap hotspot-x hotspot-y]
  (Graphics/.newCursor graphics pixmap hotspot-x hotspot-y))

(defn set-cursor [graphics cursor]
  (Graphics/.setCursor graphics cursor))

(defn frames-per-second [graphics]
  (Graphics/.getFramesPerSecond graphics))

(defn delta-time [graphics]
  (Graphics/.getDeltaTime graphics))
