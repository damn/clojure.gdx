(ns clojure.gdx.maps.tiled
  (:import (com.badlogic.gdx.graphics.g2d TextureRegion)
           (com.badlogic.gdx.maps MapProperties)
           (com.badlogic.gdx.maps.tiled.tiles StaticTiledMapTile)
           (com.badlogic.gdx.maps.tiled TiledMap
                                        TiledMapTileLayer
                                        TiledMapTileLayer$Cell
                                        TmxMapLoader)
           (com.badlogic.gdx.utils Disposable)))

(defn- add-props! [^MapProperties mp properties]
  (doseq [[k v] properties]
    (assert (string? k))
    (.put mp k v)))

(defn- props->clj-map [^MapProperties mp]
  (zipmap (.getKeys   mp)
          (.getValues mp)))

(defn- create-layer
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

(defn- tm-add-layer!
  "Returns nil."
  [^TiledMap tiled-map
   {:keys [name
           visible?
           properties
           tiles]}]
  (let [props (.getProperties tiled-map)
        layer (create-layer {:width      (.get props "width")
                             :height     (.get props "height")
                             :tilewidth  (.get props "tilewidth")
                             :tileheight (.get props "tileheight")
                             :name name
                             :visible? visible?
                             :map-properties (doto (MapProperties.)
                                               (add-props! properties))
                             :tiles tiles})]
    (.add (.getLayers tiled-map) layer))
  nil)

(def copy-tile
  "Memoized function. Copies the given [[static-tiled-map-tile]].

  Tiles are usually shared by multiple cells, see: https://libgdx.com/wiki/graphics/2d/tile-maps#cells"
  (memoize
   (fn [^StaticTiledMapTile tile]
     (assert tile)
     (StaticTiledMapTile. tile))))

(defn static-tiled-map-tile
  "Creates a `StaticTiledMapTile` with the given `texture-region` and property."
  [texture-region property-name property-value]
  {:pre [texture-region
         (string? property-name)]}
  (let [tile (StaticTiledMapTile. ^TextureRegion texture-region)]
    (.put (.getProperties tile) property-name property-value)
    tile))

(defprotocol HasMapProperties
  (map-properties [_]
                  "Returns the map-properties of the given tiled-map or tiled-map-layer as clojure map."))

(defprotocol TMap
  (layers [tiled-map]
          "Returns the layers of the tiled-map (instance of [[TMapLayer]]).")

  (layer-index [tiled-map layer]
               "Returns nil or the integer index of the layer.")

  (get-layer [tiled-map layer-name]
             "Returns the layer with name (string).")

  (add-layer! [tiled-map {:keys [name
                                 visible?
                                 properties
                                 tiles]}]
              "`properties` is optional. Returns nil."))

(defprotocol TMapLayer
  (tile-at [_ [x y]]
           "If a cell is defined at the position, returns the tile. Otherwise returns nil.")
  (layer-name [layer])
  (set-visible! [layer boolean])
  (visible? [layer])
  (property-value [layer [x y] property-key]
                  "Returns the property value of the tile at the cell in layer.
                  If there is no cell at this position in the layer returns `:no-cell`.
                  If the property value is undefined returns `:undefined`."))

(defn- reify-tiled-layer [^TiledMapTileLayer this]
  (reify
    clojure.lang.ILookup
    (valAt [_ key]
      (.get (.getProperties this) key))

    HasMapProperties
    (map-properties [_]
      (props->clj-map (.getProperties this)))

    TMapLayer
    (set-visible! [_ boolean]
      (.setVisible this boolean))

    (visible? [_]
      (.isVisible this))

    (layer-name [_]
      (.getName this))

    (tile-at [_ [x y]]
      (when-let [cell (.getCell this x y)]
        (.getTile cell)))

    (property-value [_ [x y] property-key]
      (if-let [cell (.getCell this x y)]
        (if-let [value (.get (.getProperties (.getTile cell)) property-key)]
          value
          :undefined)
        :no-cell))))

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

    HasMapProperties
    (map-properties [_]
      (props->clj-map (.getProperties this)))

    TMap
    (layers [_]
      (map reify-tiled-layer (.getLayers this)))

    (layer-index [_ layer]
      (let [idx (.getIndex (.getLayers this) ^String (layer-name layer))]
        (when-not (= idx -1)
          idx)))

    (get-layer [_ layer-name]
      (reify-tiled-layer (.get (.getLayers this) ^String layer-name)))

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
    (add-props! (.getProperties tiled-map) properties)
    (doseq [layer layers]
      (tm-add-layer! tiled-map layer))
    (reify-tiled-map tiled-map)))
