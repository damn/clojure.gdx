(ns clojure.gdx.graphics.g2d.bitmap-font
  (:require [clojure.gdx.utils.align :as align]
            [clojure.string :as str])
  (:import (com.badlogic.gdx.graphics.g2d BitmapFont)))

(defn configure!
  [^BitmapFont font
   {:keys [scale
           enable-markup?
           use-integer-positions?]}]
  (.setScale (.getData font) scale)
  (set! (.markupEnabled (.getData font)) enable-markup?)
  (.setUseIntegerPositions font use-integer-positions?)
  font)

(defn- text-height [^BitmapFont font text]
  (-> text
      (str/split #"\n")
      count
      (* (.getLineHeight font))))

(defn draw!
  "font, h-align, up? and scale are optional.
  h-align one of: :center, :left, :right. Default :center.
  up? renders the font over y, otherwise under.
  scale will multiply the drawn text size with the scale."
  [^BitmapFont font
   batch
   {:keys [scale text x y up? h-align target-width wrap?]}]
  {:pre [(or (nil? h-align)
             (contains? align/k->value h-align))]}
  (let [old-scale (.scaleX (.getData font))]
    (.setScale (.getData font) (float (* old-scale scale)))
    (.draw font
           batch
           text
           (float x)
           (float (+ y (if up? (text-height font text) 0)))
           (float target-width)
           (get align/k->value (or h-align :center))
           wrap?)
    (.setScale (.getData font) (float old-scale))))
