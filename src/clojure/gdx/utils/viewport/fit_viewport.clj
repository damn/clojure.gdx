(ns clojure.gdx.utils.viewport.fit-viewport
  (:import (clojure.lang ILookup)
           (com.badlogic.gdx.utils.viewport FitViewport
                                            Viewport)))

(defn create [width height camera]
  (proxy [FitViewport ILookup] [width height camera]
    (valAt [k]
      (case k
        :viewport/width  (Viewport/.getWorldWidth  this)
        :viewport/height (Viewport/.getWorldHeight this)
        :viewport/camera (Viewport/.getCamera      this)))))
