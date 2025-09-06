(ns clojure.gdx.math.rectangle
  (:refer-clojure :exclude [contains?])
  (:import (com.badlogic.gdx.math Rectangle)))

(defn create [x y width height]
  (Rectangle. x y width height))

(defn contains?
  "whether the point is contained in the rectangle"
  [rectangle [x y]]
  (Rectangle/.contains rectangle x y)
  ; return this.x <= x && this.x + this.width >= x && this.y <= y && this.y + this.height >= y;
  )
