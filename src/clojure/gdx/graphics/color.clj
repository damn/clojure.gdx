(ns clojure.gdx.graphics.color
  (:import (com.badlogic.gdx.graphics Color)))

(def ^Color ^{:doc "The color black."} black Color/BLACK)
(def ^Color ^{:doc "The color white."} white Color/WHITE)

(defn create
  "Creates a color object, holding the r, g, b and alpha component as floats in the range [0,1]. All methods perform clamping on the internal values after execution."
  ([r g b]
   (create r g b 1))
  ([r g b a]
   (Color. (float r) (float g) (float b) (float a))))
