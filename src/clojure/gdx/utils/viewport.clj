(ns clojure.gdx.utils.viewport
  (:import (com.badlogic.gdx.math Vector2)
           (com.badlogic.gdx.utils.viewport Viewport)))

(defn unproject
  "Transforms the specified screen coordinate to world coordinates.

  Returns:
  The vector that was passed in, transformed to world coordinates.
  See Also:

  Camera.unproject(Vector3)"
  [viewport x y]
  (let [v2 (Viewport/.unproject viewport (Vector2. x y))]
    [(.x v2) (.y v2)]))

(defn resize
  "Configures this viewport's screen bounds using the specified screen size and calls apply(boolean). Typically called from ApplicationListener.resize(int, int) or Screen.resize(int, int).

  The default implementation only calls apply(boolean)."
  [viewport w h & {:keys [center-camera?]}]
  (Viewport/.update viewport w h (boolean center-camera?)))

