(ns clojure.gdx.math.utils
  (:import (com.badlogic.gdx.math MathUtils)))

(defn equal?
  "Returns true if a is nearly equal to b. The function uses the default floating error tolerance."
  [a b]
  (MathUtils/isEqual a b))

(defn clamp [value min max]
  (MathUtils/clamp (float value) (float min) (float max)))

(defn degree->radians [degree]
  (* MathUtils/degreesToRadians (float degree)))
