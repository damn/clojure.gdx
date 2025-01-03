(ns clojure.gdx.scene2d.ui.utils
  (:import (com.badlogic.gdx.graphics.g2d TextureRegion)
           (com.badlogic.gdx.scenes.scene2d.utils BaseDrawable TextureRegionDrawable)))

(defn texture-region-drawable ^TextureRegionDrawable
  [^TextureRegion texture-region]
  (TextureRegionDrawable. texture-region))

(defn tint
  "Creates a new drawable that renders the same as this drawable tinted the specified color."
  [texture-region-drawable color]
  (TextureRegionDrawable/.tint texture-region-drawable color))

(defn set-min-size!
  ([drawable size]
   (set-min-size! drawable size size))
  ([drawable width height]
   (BaseDrawable/.setMinSize drawable
                             (float width)
                             (float height))))
