(ns clojure.gdx.graphics.g2d.bitmap-font
  (:import (com.badlogic.gdx.graphics.g2d BitmapFont)))

(defn line-height
  "Returns the line height, which is the distance from one line of text to the next."
  [^BitmapFont font]
  (.getLineHeight font))

(defn data
  "Gets the underlying {@link BitmapFontData} for this BitmapFont."
  [^BitmapFont font]
  (.getData font))

(defn set-use-integer-positions!
  "Specifies whether to use integer positions. Default is to use them so filtering doesn't kick in as badly."
  [^BitmapFont font boolean]
  (.setUseIntegerPositions font boolean))
