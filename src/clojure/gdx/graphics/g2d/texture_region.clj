(ns clojure.gdx.graphics.g2d.texture-region
  (:import (com.badlogic.gdx.graphics.g2d TextureRegion)))

(defn dimensions [^TextureRegion texture-region]
  [(.getRegionWidth  texture-region)
   (.getRegionHeight texture-region)])

(defn region [^TextureRegion texture-region x y w h]
  (TextureRegion. texture-region
                  (int x)
                  (int y)
                  (int w)
                  (int h)))
