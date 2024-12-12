(ns clojure.gdx.graphics.g2d.texture-region
  (:import (com.badlogic.gdx.graphics Texture)
           (com.badlogic.gdx.graphics.g2d TextureRegion)))

(defn create
  ([^Texture texture]
   (TextureRegion. texture))
  ([^Texture texture x y w h]
   (TextureRegion. texture (int x) (int y) (int w) (int h))))

(defn ->create [^TextureRegion texture-region x y w h]
  (TextureRegion. texture-region (int x) (int y) (int w) (int h)))

(defn dimensions [^TextureRegion texture-region]
  [(.getRegionWidth  texture-region)
   (.getRegionHeight texture-region)])
