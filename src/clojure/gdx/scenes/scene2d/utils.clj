(ns clojure.gdx.scenes.scene2d.utils
  (:require [clojure.gdx.graphics.color :as color])
  (:import (com.badlogic.gdx.graphics.g2d TextureRegion)
           (com.badlogic.gdx.scenes.scene2d.utils BaseDrawable
                                                  ChangeListener
                                                  ClickListener
                                                  TextureRegionDrawable)))

(defn drawable [texture-region & {:keys [width height tint-color]}]
  (let [drawable (TextureRegionDrawable. ^TextureRegion texture-region)]
    (when (and width height)
      (BaseDrawable/.setMinSize drawable (float width) (float height)))
    (if tint-color
      (TextureRegionDrawable/.tint drawable (color/->obj tint-color))
      drawable)))

(defn click-listener [clicked]
  (proxy [ClickListener] []
    (clicked [event x y]
      (clicked event x y))))

(defn change-listener ^ChangeListener [changed]
  (proxy [ChangeListener] []
    (changed [event actor]
      (changed event actor))))
