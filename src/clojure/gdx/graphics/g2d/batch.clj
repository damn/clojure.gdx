(ns clojure.gdx.graphics.g2d.batch
  (:import (com.badlogic.gdx.graphics.g2d Batch)))

(def set-color!             Batch/.setColor)
(def set-projection-matrix! Batch/.setProjectionMatrix)
(def begin!                 Batch/.begin)
(def end!                   Batch/.end)

(defn draw!
  [^Batch batch
   texture-region
   {:keys [x y origin-x origin-y w h scale-x scale-y rotation]}]
  (.draw batch
         texture-region
         x
         y
         origin-x
         origin-y
         w
         h
         scale-x
         scale-y
         rotation))
