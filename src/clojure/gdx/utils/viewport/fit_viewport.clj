(ns clojure.gdx.utils.viewport.fit-viewport
  (:import (com.badlogic.gdx.utils.viewport FitViewport)))


; create
(defn fit-viewport
  "A ScalingViewport that uses Scaling.fit so it keeps the aspect ratio by scaling the world up to fit the screen, adding black bars (letterboxing) for the remaining space."
  [width height camera]
  (proxy [FitViewport ILookup] [width height camera]
    (valAt
      ([key]
       (k->viewport-field this key))
      ([key _not-found]
       (k->viewport-field this key)))))

