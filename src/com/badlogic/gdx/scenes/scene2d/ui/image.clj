(ns com.badlogic.gdx.scenes.scene2d.ui.image
  (:import (com.badlogic.gdx.scenes.scene2d.ui Image)))

(defn set-drawable! [image drawable]
  (Image/.setDrawable image drawable))
