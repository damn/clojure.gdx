(ns clojure.gdx.freetype
  (:require [com.badlogic.gdx.graphics.texture.filter :as texture-filter]
            [com.badlogic.gdx.graphics.g2d.bitmap-font :as bitmap-font])
  (:import (com.badlogic.gdx.graphics.g2d.freetype FreeTypeFontGenerator
                                                   FreeTypeFontGenerator$FreeTypeFontParameter)))

(defn- create-parameter
  [{:keys [size
           min-filter
           mag-filter]}]
  (let [params (FreeTypeFontGenerator$FreeTypeFontParameter.)]
    (set! (.size params) size)
    (set! (.minFilter params) min-filter)
    (set! (.magFilter params) mag-filter)
    params))

(defn generate-font
  [file-handle
   {:keys [size
           quality-scaling
           enable-markup?
           use-integer-positions?]}]
  (let [generator (FreeTypeFontGenerator. file-handle)
        font (.generateFont generator (create-parameter {:size (* size quality-scaling)
                                                         ; :texture-filter/linear because scaling to world-units
                                                         :min-filter (texture-filter/k->value :linear)
                                                         :mag-filter (texture-filter/k->value :linear)}))]
    (bitmap-font/configure! font {:scale (/ quality-scaling)
                                  :enable-markup? enable-markup?
                                  :use-integer-positions? use-integer-positions?})))
