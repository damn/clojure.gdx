(ns clojure.gdx.graphics.pixmap
  (:import (com.badlogic.gdx.files FileHandle)
           (com.badlogic.gdx.graphics Color Pixmap Pixmap$Format)))

(defn create
  ([^FileHandle file-handle]
   (Pixmap. file-handle))
  ([width height ^Pixmap$Format format]
   (Pixmap. (int width) (int height) format)))

(def format-RGBA8888 Pixmap$Format/RGBA8888)

(defn set-color [^Pixmap pixmap ^Color color]
  (.setColor pixmap color))

(defn draw-pixel [^Pixmap pixmap x y]
  (.drawPixel pixmap x y))
