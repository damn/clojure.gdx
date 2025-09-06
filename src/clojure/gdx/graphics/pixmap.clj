(ns clojure.gdx.graphics.pixmap
  (:import (com.badlogic.gdx.graphics Pixmap
                                      Pixmap$Format
                                      Texture)))

(defn create
  ([]
   (Pixmap. 1 1 Pixmap$Format/RGBA8888))
  ([file-handle]
   (Pixmap. file-handle)))

(defn set-color! [pixmap color]
  (Pixmap/.setColor pixmap color))

(defn draw-pixel! [pixmap x y]
  (Pixmap/.drawPixel pixmap x y))

(defn dispose! [pixmap]
  (Pixmap/.dispose pixmap))
