(ns clojure.gdx.math.frustum
  (:import (com.badlogic.gdx.math Frustum Vector3)))

(defn- vector3->clj-vec [^Vector3 v3]
  [(.x v3)
   (.y v3)
   (.z v3)])

(defn plane-points [^Frustum frustum]
  (map vector3->clj-vec (.planePoints frustum)))


(comment
 ; TODO tests?!
 (require '[gdl.graphics :as g])

 (map vector3->clj-vec (plane-points (.frustum g/camera))))
