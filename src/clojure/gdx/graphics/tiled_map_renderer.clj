(ns clojure.gdx.graphics.tiled-map-renderer
  (:require [clojure.gdx.maps.tiled :as tiled]
            [clojure.gdx.graphics.color :as color])
  (:import (clojure.gdx.graphics OrthogonalTiledMapRenderer
                                 ColorSetter)))

(defn draw! [tiled-map-renderer world-viewport tiled-map color-setter]
  (let [^OrthogonalTiledMapRenderer renderer (tiled-map-renderer tiled-map)
        camera (:viewport/camera world-viewport)]
    (.setColorSetter renderer (reify ColorSetter
                                (apply [_ color x y]
                                  (color/float-bits (color-setter color x y)))))
    (.setView renderer camera)
    ; there is also:
    ; OrthogonalTiledMapRenderer/.renderTileLayer (TiledMapTileLayer layer)
    ; but right order / visible only ?
    (->> tiled-map
         tiled/layers
         (filter tiled/visible?)
         (map (partial tiled/layer-index tiled-map))
         int-array
         (.render renderer))))

(defn create [world-unit-scale batch]
  (memoize (fn [tiled-map]
             (OrthogonalTiledMapRenderer. (:tiled-map/java-object tiled-map)
                                          (float world-unit-scale)
                                          batch))))
