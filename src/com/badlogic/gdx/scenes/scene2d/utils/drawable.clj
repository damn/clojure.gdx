(ns com.badlogic.gdx.scenes.scene2d.utils.drawable
  (:require [com.badlogic.gdx.graphics.color :as color])
  (:import (com.badlogic.gdx.graphics.g2d TextureRegion)
           (com.badlogic.gdx.scenes.scene2d.utils BaseDrawable
                                                  TextureRegionDrawable)))

(defn create [texture-region & {:keys [width height tint-color]}]
  (let [drawable (TextureRegionDrawable. ^TextureRegion texture-region)]
    (when (and width height)
      (BaseDrawable/.setMinSize drawable (float width) (float height)))
    (if tint-color
      (TextureRegionDrawable/.tint drawable (color/->java tint-color))
      drawable)))
