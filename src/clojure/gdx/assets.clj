(ns clojure.gdx.assets
  "Loads and stores assets like textures, bitmapfonts, tile maps, sounds, music and so on."
  (:refer-clojure :exclude [load type])
  (:import (com.badlogic.gdx.assets AssetManager)))

(defn manager
  "Creates a new AssetManager with all default loaders."
  ^AssetManager []
  (proxy [AssetManager clojure.lang.IFn] []
    (invoke [^String path]
      (if (AssetManager/.contains this path)
        (AssetManager/.get this path)
        (throw (IllegalArgumentException. (str "Asset cannot be found: " path)))))))

(defn- asset-type->class [k]
  (case k
    :sound   com.badlogic.gdx.audio.Sound
    :texture com.badlogic.gdx.graphics.Texture))

(defn load
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
  (asset-type->class (AssetManager/.getAssetType manager asset-name)))

(defn names
  "the file names of all loaded assets."
  [manager]
  (AssetManager/.getAssetNames manager))

(defn dispose
  "Disposes all assets in the manager and stops all asynchronous loading."
  [manager]
  (AssetManager/.dispose manager))
