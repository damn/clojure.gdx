(ns clojure.tiled)

(defprotocol HasMapProperties
  (map-properties [_]))

(defprotocol TiledMap
  (layers [tiled-map])

  (layer-index [tiled-map layer]
               "Returns nil or the integer index of the layer.")

  (get-layer [tiled-map layer-name]
             "Returns the layer with name (string).")

  (add-layer! [tiled-map {:keys [name
                                 visible?
                                 properties
                                 tiles]}]
              "`properties` is optional. Returns nil."))

(defprotocol TiledMapTileLayer
  (tile-at [_ [x y]]
           "If a cell is defined at the position, returns the tile. Otherwise returns nil.")
  (layer-name [layer])
  (set-visible! [layer boolean])
  (visible? [layer])
  (property-value [layer [x y] property-key]
                  "Returns the property value of the tile at the cell in layer.
                  If there is no cell at this position in the layer returns `:no-cell`.
                  If the property value is undefined returns `:undefined`."))

(defn map-positions
  "Returns a sequence of all `[x y]` positions in the `tiled-map`."
  [tiled-map]
  (for [x (range (:tiled-map/width  tiled-map))
        y (range (:tiled-map/height tiled-map))]
    [x y]))

(defn positions-with-property
  "Returns a sequence of `[[x y] value]` for all tiles who have `property-key`."
  [tiled-map layer-name property-key]
  {:pre [tiled-map
         (string? layer-name)
         (string? property-key)]}
  (let [layer (get-layer tiled-map layer-name)]
    (for [position (map-positions tiled-map)
          :let [value (property-value layer position property-key)]
          :when (not (#{:undefined :no-cell} value))]
      [position value])))

(defn- tile-movement-property [tiled-map layer position]
  (let [value (property-value layer position "movement")]
    (assert (not= value :undefined)
            (str "Value for :movement at position "
                 position  " / mapeditor inverted position: " [(position 0)
                                                               (- (dec (:tiled-map/height tiled-map))
                                                                  (position 1))]
                 " and layer " (layer-name layer) " is undefined."))
    (when-not (= :no-cell value)
      value)))

(defn- movement-property-layers [tiled-map]
  (->> tiled-map
       layers
       reverse
       (filter #(get % "movement-properties"))))

(defn movement-properties [tiled-map position]
  (for [layer (movement-property-layers tiled-map)]
    [(layer-name layer)
     (tile-movement-property tiled-map layer position)]))

(defn movement-property [tiled-map position]
  (or (->> tiled-map
           movement-property-layers
           (some #(tile-movement-property tiled-map % position)))
      "none"))
