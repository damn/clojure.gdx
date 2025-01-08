(ns clojure.gdx.assets.manager
  "Loads and stores assets like textures, bitmapfonts, tile maps, sounds, music and so on."
  (:refer-clojure :exclude [load type])
  (:import (clojure.lang IFn)
           (com.badlogic.gdx.assets AssetManager)))

(def ^:private asset-type-class-map
  {:sound   com.badlogic.gdx.audio.Sound
   :texture com.badlogic.gdx.graphics.Texture})

(defn- asset-type->class [k]
  (get asset-type-class-map k))

(defn- class->asset-type [class]
  (some (fn [[k v]] (when (= v class) k)) asset-type-class-map))

(defn load ; TODO this is 'add!' 'clojure.mutable/add!'
  "Adds the given asset to the loading queue of the AssetManager."
  [manager file asset-type]
  (AssetManager/.load manager ^String file (asset-type->class asset-type)))

(defn finish-loading
  "Blocks until all assets are loaded."
  [manager]
  (AssetManager/.finishLoading manager))

(defn type
  "Returns:
  the type of a loaded asset. "
  [manager asset-name]
  (class->asset-type (AssetManager/.getAssetType manager asset-name)))

(defn names
  "the file names of all loaded assets."
  [manager]
  (AssetManager/.getAssetNames manager))

(defn load-all [manager assets]
  (doseq [[file asset-type] assets]
    (load manager file asset-type))
  (finish-loading manager)
  manager)

(defn create
  "Loads and stores assets like textures, bitmapfonts, tile maps, sounds, music and so on."
  ([]
   (proxy [AssetManager IFn] []
     (invoke [^String path]
       (if (AssetManager/.contains this path)
         (AssetManager/.get this path)
         (throw (IllegalArgumentException. (str "Asset cannot be found: " path)))))))
  ([assets]
   (load-all (create) assets)))
