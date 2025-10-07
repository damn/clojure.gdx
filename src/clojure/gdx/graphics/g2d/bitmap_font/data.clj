(ns clojure.gdx.graphics.g2d.bitmap-font.data
  (:import (com.badlogic.gdx.graphics.g2d BitmapFont$BitmapFontData)))

(defn set-scale!
  "Scales the font by the specified amount in both directions.
  throws IllegalArgumentException if scaleX or scaleY is zero."
  [^BitmapFont$BitmapFontData data scale-xy]
  (.setScale data scale-xy))

(defn enable-markup!
  [^BitmapFont$BitmapFontData data boolean]
  (set! (.markupEnabled data) boolean))

(defn scale-x
  [^BitmapFont$BitmapFontData data]
  (.scaleX data))
