(ns clojure.gdx.scenes.scene2d.utils.texture-region-drawable
  (:import (com.badlogic.gdx.graphics.g2d TextureRegion)
           (com.badlogic.gdx.scenes.scene2d.utils TextureRegionDrawable)))

(defn create [texture-region & {:keys [width height tint-color]}]
  (let [drawable (TextureRegionDrawable. ^TextureRegion texture-region)]
    (when (and width height)
      (.setMinSize drawable (float width) (float height)))
    (if tint-color
      (.tint drawable tint-color)
      drawable)))
