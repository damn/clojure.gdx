(ns clojure.gdx.tiled-map-renderer
  (:require [com.badlogic.gdx.graphics.color :as color]
            [clojure.tiled :as tiled])
  (:import (com.badlogic.gdx.maps.tiled.renderers Orthogonal)
           (com.badlogic.gdx.maps.tiled.renderers.orthogonal ColorSetter)))

(defn draw! [tiled-map-renderer world-viewport tiled-map color-setter]
  (let [^Orthogonal renderer (tiled-map-renderer tiled-map)
        camera (:viewport/camera world-viewport)]
    (.setColorSetter renderer (reify ColorSetter
                                (apply [_ color x y]
                                  (color/float-bits (color-setter color x y)))))
    (.setView renderer camera)
    (->> tiled-map
         tiled/layers
         (filter tiled/visible?)
         (map (partial tiled/layer-index tiled-map))
         int-array
         (.render renderer))))

(defn create [world-unit-scale batch]
  (memoize (fn [tiled-map]
             (Orthogonal. (:tiled-map/java-object tiled-map)
                          (float world-unit-scale)
                          batch))))
