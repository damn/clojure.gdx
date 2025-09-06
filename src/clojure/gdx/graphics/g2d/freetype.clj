(ns clojure.gdx.graphics.g2d.freetype
  (:require [clojure.gdx.graphics.texture.filter :as texture-filter]
            [clojure.gdx.graphics.g2d.bitmap-font :as bitmap-font]
            [clojure.gdx.graphics.g2d.freetype.parameter :as parameter])
  (:import (com.badlogic.gdx.graphics.g2d.freetype FreeTypeFontGenerator)))


(defn generate-font
  [file-handle
   {:keys [size
           quality-scaling
           enable-markup?
           use-integer-positions?]}]
  (let [generator (FreeTypeFontGenerator. file-handle)
        font (.generateFont generator (parameter/create {:size (* size quality-scaling)
                                                         ; :texture-filter/linear because scaling to world-units
                                                         :min-filter (texture-filter/k->value :linear)
                                                         :mag-filter (texture-filter/k->value :linear)}))]
    (bitmap-font/configure! font {:scale (/ quality-scaling)
                                  :enable-markup? enable-markup?
                                  :use-integer-positions? use-integer-positions?})))
