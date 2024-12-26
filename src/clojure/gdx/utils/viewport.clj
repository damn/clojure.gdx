(ns clojure.gdx.utils.viewport
  "Manages a camera and determines how world coordinates are mapped to and from the screen."
  (:refer-clojure :exclude [update])
  (:import (clojure.lang ILookup)
           (com.badlogic.gdx.math Vector2)
           (com.badlogic.gdx.utils.viewport FitViewport Viewport)))

(defn- k->field [^Viewport vp k]
  (case k
    :width             (.getWorldWidth      vp)
    :height            (.getWorldHeight     vp)
    :camera            (.getCamera          vp)
    :left-gutter-width (.getLeftGutterWidth vp)
    :right-gutter-x    (.getRightGutterX    vp)
    :top-gutter-height (.getTopGutterHeight vp)
    :top-gutter-y      (.getTopGutterY      vp)))

(defn fit [width height camera]
  (proxy [FitViewport ILookup] [width height camera]
    (valAt
      ([key]
       (k->field this key))
      ([key _not-found]
       (k->field this key)))))

(defn update
  "Configures this viewport's screen bounds using the specified screen size and calls apply(boolean). Typically called from ApplicationListener.resize(int, int) or Screen.resize(int, int).

  The default implementation only calls apply(boolean)."
  [viewport w h & {:keys [center-camera?]}]
  (Viewport/.update viewport w h (boolean center-camera?)))

(defn unproject
  "Transforms the specified screen coordinate to world coordinates.

  Returns:
  The vector that was passed in, transformed to world coordinates.
  See Also:

  Camera.unproject(Vector3)"
  [viewport x y]
  (let [v2 (Viewport/.unproject viewport (Vector2. x y))]
    [(.x v2) (.y v2)]))
