(ns clojure.gdx.graphics.pixmap
  (:import (com.badlogic.gdx.graphics Color Pixmap Pixmap$Format)))

(def format-RGBA8888 Pixmap$Format/RGBA8888)

(defn set-color [^Pixmap pixmap ^Color color]
  (.setColor pixmap color))
