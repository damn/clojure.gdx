(ns clojure.gdx.graphics.g2d.bitmap-font
  "Renders bitmap fonts. The font consists of 2 files: an image file or TextureRegion containing the glyphs and a file in the AngleCode BMFont text format that describes where each glyph is on the image.

  Text is drawn using a Batch. Text can be cached in a BitmapFontCache for faster rendering of static text, which saves needing to compute the location of each glyph each frame.

  * The texture for a BitmapFont loaded from a file is managed. dispose() must be called to free the texture when no longer needed. A BitmapFont loaded using a TextureRegion is managed if the region's texture is managed. Disposing the BitmapFont disposes the region's texture, which may not be desirable if the texture is still being used elsewhere.

  The code was originally based on Matthias Mann's TWL BitmapFont class. Thanks for sharing, Matthias! :)"
  (:import (com.badlogic.gdx.graphics.g2d BitmapFont BitmapFont$BitmapFontData)))

(defn line-height
  "Returns the line height, which is the distance from one line of text to the next."
  [font]
  (BitmapFont/.getLineHeight font))

(defn draw
  "Draws text at the specified position."
  [& {:keys [font batch text x y target-width align wrap?]
      :or {target-width 0 wrap? false}}]
  (BitmapFont/.draw font
                    batch
                    text
                    (float x)
                    (float y)
                    (float target-width)
                    align
                    wrap?))

(defn data
  "Gets the underlying BitmapFont.BitmapFontData for this BitmapFont."
  [font]
  (BitmapFont/.getData font))

(defn scale-x [^BitmapFont$BitmapFontData data]
  (.scaleX data))

(defn set-scale
  "Scales the font by the specified amount in both directions."
  [^BitmapFont$BitmapFontData data scale-xy]
  (.setScale data (float scale-xy)))
