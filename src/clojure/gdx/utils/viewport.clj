(ns clojure.gdx.utils.viewport
  (:import (com.badlogic.gdx.math Vector2)
           (com.badlogic.gdx.utils.viewport Viewport)))

(defn unproject [^Viewport viewport x y]
  (let [vector2 (.unproject viewport (Vector2. x y))]
    [(.x vector2)
     (.y vector2)]))

(defn update! [^Viewport viewport width height center-camera?]
  (.update viewport width height center-camera?))
