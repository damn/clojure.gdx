(ns clojure.gdx.tiled
  (:require [clojure.tiled]
            [com.badlogic.gdx.maps.map-properties :as properties]
            [com.badlogic.gdx.maps.tiled.tiled-map-tile-layer :as layer]
            [com.badlogic.gdx.maps.tiled.tiles :as tiles])
  (:import (com.badlogic.gdx.maps.tiled TiledMap
                                        TmxMapLoader)
           (com.badlogic.gdx.utils Disposable)))

(defn- tm-add-layer!
  "Returns nil."
  [^TiledMap tiled-map
   {:keys [name
           visible?
           properties
           tiles]}]
  (let [props (.getProperties tiled-map)
        layer (layer/create {:width      (.get props "width")
                             :height     (.get props "height")
                             :tilewidth  (.get props "tilewidth")
                             :tileheight (.get props "tileheight")
                             :name name
                             :visible? visible?
                             :map-properties (properties/create properties)
                             :tiles tiles})]
    (.add (.getLayers tiled-map) layer))
  nil)

(defn- reify-tiled-map [^TiledMap this]
  (reify
    Disposable
    (dispose [_]
      (.dispose this))

    clojure.lang.ILookup
    (valAt [_ key]
      (case key
        :tiled-map/java-object this
        :tiled-map/width  (.get (.getProperties this) "width")
        :tiled-map/height (.get (.getProperties this) "height")))

    clojure.tiled/HasMapProperties
    (get-property [_ k]
      (.get (.getProperties this) key))
    (map-properties [_]
      (properties/->clj (.getProperties this)))

    clojure.tiled/TMap
    (layers [_]
      (.getLayers this))

    (layer-index [_ layer]
      (let [idx (.getIndex (.getLayers this) ^String (clojure.tiled/layer-name layer))]
        (when-not (= idx -1)
          idx)))

    (get-layer [_ layer-name]
      (.get (.getLayers this) ^String layer-name))

    (add-layer! [_ layer-declaration]
      (tm-add-layer! this layer-declaration))))

(defn tmx-tiled-map
  "Has to be disposed because it loads textures.
  Loads through internal file handle."
  [file-name]
  (->> file-name
       (.load (TmxMapLoader.))
       reify-tiled-map))

(defn create-tiled-map [{:keys [properties
                                layers]}]
  (let [tiled-map (TiledMap.)]
    (properties/add! (.getProperties tiled-map) properties)
    (doseq [layer layers]
      (tm-add-layer! tiled-map layer))
    (reify-tiled-map tiled-map)))

(def copy-tile (memoize tiles/copy))
(def static-tiled-map-tile tiles/static-tiled-map-tile)
