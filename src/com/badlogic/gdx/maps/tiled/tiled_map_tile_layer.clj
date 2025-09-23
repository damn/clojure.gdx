(ns com.badlogic.gdx.maps.tiled.tiled-map-tile-layer
  (:require [clojure.tiled :as tiled]
            [com.badlogic.gdx.maps.map-properties :as properties])
  (:import (com.badlogic.gdx.maps.tiled TiledMapTileLayer
                                        TiledMapTileLayer$Cell)))

(extend-type TiledMapTileLayer
  tiled/HasMapProperties
  (get-property [layer k]
    (.get (.getProperties layer) k))

  (map-properties [layer]
    (properties/->clj (.getProperties layer)))

  tiled/TMapLayer
  (set-visible! [layer boolean]
    (.setVisible layer boolean))

  (visible? [layer]
    (.isVisible layer))

  (layer-name [layer]
    (.getName layer))

  (tile-at [layer [x y]]
    (when-let [cell (.getCell layer x y)]
      (.getTile cell)))

  (property-value [layer [x y] property-key]
    (if-let [cell (.getCell layer x y)]
      (if-let [value (.get (.getProperties (.getTile cell)) property-key)]
        value
        :undefined)
      :no-cell)))

(defn create
  [{:keys [width
           height
           tilewidth
           tileheight
           name
           visible?
           map-properties
           tiles]}]
  {:pre [(string? name)
         (boolean? visible?)]}
  (let [layer (doto (TiledMapTileLayer. width height tilewidth tileheight)
                (.setName name)
                (.setVisible visible?))]
    (.putAll (.getProperties layer) map-properties)
    (doseq [[[x y] tiled-map-tile] tiles
            :when tiled-map-tile]
      (.setCell layer x y (doto (TiledMapTileLayer$Cell.)
                            (.setTile tiled-map-tile))))
    layer))
