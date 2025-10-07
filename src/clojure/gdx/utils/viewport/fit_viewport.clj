(ns clojure.gdx.utils.viewport.fit-viewport
  (:import (com.badlogic.gdx.utils.viewport FitViewport)))

(defn create [width height camera]
  (FitViewport. width height camera))
