(ns clojure.gdx.math-utils
  (:import (com.badlogic.gdx.math MathUtils)))

(defn degree->radians [degree]
  (* MathUtils/degreesToRadians (float degree)))

(defn clamp [value min max]
  (MathUtils/clamp (float value)
                   (float min)
                   (float max)))

(defn equal? [x y]
  (MathUtils/isEqual x y))
