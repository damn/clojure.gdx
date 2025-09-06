(ns clojure.gdx.scenes.scene2d.utils
  (:require [clojure.gdx.graphics.color :as color]
            [clojure.gdx.scenes.scene2d.input-event :as input-event]
            [clojure.gdx.scenes.scene2d.stage :as stage])
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
    (clicked [event _x _y]
      (clicked (stage/get-ctx (input-event/get-stage event))))))

(defn change-listener ^ChangeListener [changed]
  (proxy [ChangeListener] []
    (changed [event actor]
      (changed actor (stage/get-ctx (input-event/get-stage event))))))
