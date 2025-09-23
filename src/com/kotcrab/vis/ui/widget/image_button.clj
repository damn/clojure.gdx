(ns com.kotcrab.vis.ui.widget.image-button
  (:require [clojure.graphics.texture-region :as texture-region]
            [clojure.scene2d.actor :as actor]
            [clojure.scene2d.event :as event]
            [clojure.scene2d.stage :as stage]
            [clojure.scene2d.ui.table :as table]
            [com.badlogic.gdx.scenes.scene2d.utils.drawable :as drawable]
            [com.badlogic.gdx.scenes.scene2d.utils.listener :as listener])
  (:import (com.badlogic.gdx.scenes.scene2d.utils Drawable)
           (com.kotcrab.vis.ui.widget VisImageButton)))

(defn create
  [{:keys [drawable/texture-region
           on-clicked
           drawable/scale]
    :as opts}]
  (let [scale (or scale 1)
        [w h] (texture-region/dimensions texture-region)
        drawable (drawable/create texture-region
                                  :width  (* scale w)
                                  :height (* scale h))
        image-button (VisImageButton. ^Drawable drawable)]
    (when on-clicked
      (.addListener image-button (listener/change
                                  (fn [event actor]
                                    (on-clicked actor (stage/get-ctx (event/stage event)))))))
    (when-let [tooltip (:tooltip opts)]
      (actor/add-tooltip! image-button tooltip))
    (table/set-opts! image-button opts)))
