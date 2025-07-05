(ns clojure.gdx.graphics.orthographic-camera
  (:require [clojure.gdx.math.vector3 :as vector3])
  (:import (clojure.lang ILookup)
           (com.badlogic.gdx.graphics OrthographicCamera)))

(defn create
  ([]
   (proxy [OrthographicCamera ILookup] []
     (valAt [k]
       (case k
         :camera/zoom (.zoom this)
         :camera/frustum {:frustum/plane-points (mapv vector3/clojurize (.planePoints (.frustum this)))}
         :camera/position (vector3/clojurize (.position this))
         :camera/viewport-width  (.viewportWidth  this)
         :camera/viewport-height (.viewportHeight this)))))
  ([& {:keys [y-down? world-width world-height]}]
   (doto (create)
     (.setToOrtho y-down? world-width world-height))))
