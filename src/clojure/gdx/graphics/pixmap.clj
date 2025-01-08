(ns clojure.gdx.graphics.pixmap
  (:import (com.badlogic.gdx.files FileHandle)
           (com.badlogic.gdx.graphics Color Pixmap Pixmap$Format)))

(defn create
  "A Pixmap represents an image in memory. It has a width and height expressed in pixels as well as a Pixmap.Format specifying the number and order of color components per pixel. Coordinates of pixels are specified with respect to the top left corner of the image, with the x-axis pointing to the right and the y-axis pointing downwards.

  By default all methods use blending. You can disable blending with setBlending(Blending), which may reduce blitting time by ~30%. The drawPixmap(Pixmap, int, int, int, int, int, int, int, int) method will scale and stretch the source image to a target image. There either nearest neighbour or bilinear filtering can be used.

  A Pixmap stores its data in native heap memory. It is mandatory to call dispose() when the pixmap is no longer needed, otherwise memory leaks will result"
  ([^FileHandle file-handle]
   (Pixmap. file-handle))
  ([width height ^Pixmap$Format format]
   (Pixmap. (int width) (int height) format)))

(defn draw-pixel
  "Draws a pixel at the given location with the current color."
  [^Pixmap pixmap x y]
  (.drawPixel pixmap x y))

(def format-RGBA8888 Pixmap$Format/RGBA8888)

(defn set-color [^Pixmap pixmap ^Color color]
  (.setColor pixmap color))
