(ns clojure.gdx.utils.viewport
  "Manages a camera and determines how world coordinates are mapped to and from the screen."
  (:refer-clojure :exclude [update])
  (:import
           (com.badlogic.gdx.math )
           (com.badlogic.gdx.utils.viewport FitViewport Viewport)))





(defn resize
  "Configures this viewport's screen bounds using the specified screen size and calls apply(boolean). Typically called from ApplicationListener.resize(int, int) or Screen.resize(int, int).

  The default implementation only calls apply(boolean)."
  [viewport w h & {:keys [center-camera?]}]
  (Viewport/.update viewport w h (boolean center-camera?)))
