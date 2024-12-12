(ns clojure.gdx.assets
  (:refer-clojure :exclude [load type])
  (:import (com.badlogic.gdx.assets AssetManager)))

(defn manager ^AssetManager []
  (proxy [AssetManager clojure.lang.IFn] []
    (invoke [^String path]
      (if (AssetManager/.contains this path)
        (AssetManager/.get this path)
        (throw (IllegalArgumentException. (str "Asset cannot be found: " path)))))))

(defn- asset-type->class [k]
  (case k
    :sound   com.badlogic.gdx.audio.Sound
    :texture com.badlogic.gdx.graphics.Texture))

(defn load [manager file asset-type]
  (AssetManager/.load manager ^String file (asset-type->class asset-type)))

(defn finish-loading [manager]
  (AssetManager/.finishLoading manager))

(defn type [manager asset-name]
  (asset-type->class (AssetManager/.getAssetType manager asset-name)))

(defn names [manager]
  (AssetManager/.getAssetNames manager))

(defn dispose [manager]
  (AssetManager/.dispose manager))
