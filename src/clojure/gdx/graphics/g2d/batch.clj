(ns clojure.gdx.graphics.g2d.batch
  (:import (com.badlogic.gdx.graphics.g2d Batch)))

(defn draw! [^Batch batch texture-region x y [w h] rotation]
  (.draw batch
         texture-region
         x
         y
         (/ (float w) 2) ; origin-x
         (/ (float h) 2) ; origin-y
         w
         h
         1 ; scale-x
         1 ; scale-y
         rotation))

(defn set-color! [batch color]
  (Batch/.setColor batch color))

(defn set-projection-matrix! [batch matrix]
  (Batch/.setProjectionMatrix batch matrix))

(defn begin! [batch]
  (Batch/.begin batch))

(defn end! [batch]
  (Batch/.end batch))
