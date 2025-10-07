(ns clojure.gdx.graphics.g2d.batch
  (:import (com.badlogic.gdx.graphics.g2d Batch)))

(defn draw!
  ([^Batch batch texture-region x y w h]
   (.draw batch
          texture-region
          (float x)
          (float y)
          (float w)
          (float h)))
  ([^Batch batch texture-region x y origin-x origin-y width height scale-x scale-y rotation]
   (.draw batch
          texture-region
          x
          y
          origin-x
          origin-y
          width
          height
          scale-x
          scale-y
          rotation)))

(defn set-color! [^Batch batch [r g b a]]
  (.setColor batch r g b a))

(defn set-projection-matrix! [^Batch batch matrix]
  (.setProjectionMatrix batch matrix))

(defn begin! [^Batch batch]
  (.begin batch))

(defn end! [^Batch batch]
  (.end batch))
