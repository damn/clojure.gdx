(ns clojure.gdx.utils.viewport
  (:import (clojure.lang ILookup)
           (com.badlogic.gdx.math Vector2)
           (com.badlogic.gdx.utils.viewport Viewport
                                            FitViewport)))

(defn update! [viewport width height & {:keys [center?]}]
  (Viewport/.update viewport width height center?))

(defn unproject [viewport x y]
  (let [vector2 (.unproject viewport (Vector2. x y))]
    [(.x vector2)
     (.y vector2)]))

(defn fit [width height camera]
  (proxy [FitViewport ILookup] [width height camera]
    (valAt [k]
      (case k
        :viewport/width             (FitViewport/.getWorldWidth      this)
        :viewport/height            (FitViewport/.getWorldHeight     this)
        :viewport/camera            (FitViewport/.getCamera          this)
        :viewport/left-gutter-width (FitViewport/.getLeftGutterWidth this)
        :viewport/right-gutter-x    (FitViewport/.getRightGutterX    this)
        :viewport/top-gutter-height (FitViewport/.getTopGutterHeight this)
        :viewport/top-gutter-y      (FitViewport/.getTopGutterY      this)))))
