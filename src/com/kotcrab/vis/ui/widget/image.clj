(ns com.kotcrab.vis.ui.widget.image
  (:require [clojure.scene2d.ui.widget :as widget])
  (:import (com.badlogic.gdx.graphics Texture)
           (com.badlogic.gdx.graphics.g2d TextureRegion)
           (com.badlogic.gdx.scenes.scene2d.utils Drawable)
           (com.badlogic.gdx.utils Align
                                   Scaling)
           (com.kotcrab.vis.ui.widget VisImage)))

(defmulti ^:private image* type)

(defmethod image* Drawable [^Drawable drawable]
  (VisImage. drawable))

(defmethod image* Texture [texture]
  (VisImage. (TextureRegion. ^Texture texture)))

(defmethod image* TextureRegion [texture-region]
  (VisImage. ^TextureRegion texture-region))

(defn create
  [{:keys [image/object
           scaling
           align
           fill-parent?]
    :as opts}]
  (let [^VisImage image (image* object)]
    (when (= :center align)
      (.setAlign image Align/center))
    (when (= :fill scaling)
      (.setScaling image Scaling/fill))
    (when fill-parent?
      (.setFillParent image true))
    (widget/set-opts! image opts)))
