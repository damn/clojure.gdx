(ns clojure.gdx.utils.viewport
  (:import (com.badlogic.gdx.utils.viewport Viewport)))

(defn camera [^Viewport this]
  (.getCamera this))

(defn world-width [^Viewport this]
  (.getWorldWidth this))

(defn world-height [^Viewport this]
  (.getWorldHeight this))

(defn unproject [^Viewport this vector2]
  (.unproject this vector2))

(defn update! [^Viewport viewport width height {:keys [center?]}]
  (.update viewport width height (boolean center?)))
