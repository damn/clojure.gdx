(ns clojure.gdx.math.vector2
  (:require [clojure.gdx.math.utils :refer [equal?]])
  (:import (com.badlogic.gdx.math Vector2)))

(defn- m-v2
  (^Vector2 [[x y]] (Vector2. x y))
  (^Vector2 [x y]   (Vector2. x y)))

(defn- ->p [^Vector2 v]
  [(.x v) (.y v)])

(defn scale [v n]
  (->p (.scl (m-v2 v) (float n))))

(defn normalise [v]
  (->p (.nor (m-v2 v))))

(defn add [v1 v2]
  (->p (.add (m-v2 v1) (m-v2 v2))))

(defn length [v]
  (.len (m-v2 v)))

(defn distance [v1 v2]
  (.dst (m-v2 v1) (m-v2 v2)))

(defn normalised? [v]
  (equal? 1 (length v)))

(defn direction [[sx sy] [tx ty]]
  (normalise [(- (float tx) (float sx))
              (- (float ty) (float sy))]))

(defn angle-from-vector
  "converts theta of Vector2 to angle from top (top is 0 degree, moving left is 90 degree etc.), counterclockwise"
  [v]
  (.angleDeg (m-v2 v) (Vector2. 0 1)))

(comment

 (pprint
  (for [v [[0 1]
           [1 1]
           [1 0]
           [1 -1]
           [0 -1]
           [-1 -1]
           [-1 0]
           [-1 1]]]
    [v
     (.angleDeg (m-v2 v) (Vector2. 0 1))
     (get-angle-from-vector (m-v2 v))]))

 )

(defn normal-vectors [[x y]]
  [[(- (float y))         x]
   [          y (- (float x))]])

(defn diagonal-direction? [[x y]]
  (and (not (zero? (float x)))
       (not (zero? (float y)))))

(defn add-vs [vs]
  (normalise (reduce add [0 0] vs)))
