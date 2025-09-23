(ns clojure.gdx.sprite-batch
  (:require [clojure.graphics.g2d.batch])
  (:import (com.badlogic.gdx.graphics.g2d SpriteBatch)))

(defn create []
  (SpriteBatch.))

(extend-type SpriteBatch
  clojure.graphics.g2d.batch/Batch
  (draw! [this texture-region x y [w h] rotation]
    (.draw this
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

  (set-color! [this [r g b a]]
    (.setColor this r g b a))

  (set-projection-matrix! [this matrix]
    (.setProjectionMatrix this matrix))

  (begin! [this]
    (.begin this))

  (end! [this]
    (.end this)))
