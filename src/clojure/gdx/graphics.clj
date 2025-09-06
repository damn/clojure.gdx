(ns clojure.gdx.graphics
  (:import (com.badlogic.gdx Graphics)))

(defn delta-time [graphics]
  (Graphics/.getDeltaTime graphics))

(defn frames-per-second [graphics]
  (Graphics/.getFramesPerSecond graphics))

(defn set-cursor! [graphics cursor]
  (Graphics/.setCursor graphics cursor))

(defn cursor [graphics pixmap hotspot-x hotspot-y]
  (Graphics/.newCursor graphics pixmap hotspot-x hotspot-y))
