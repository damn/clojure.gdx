(ns clojure.graphics.camera)

(defprotocol Camera
  (zoom [_])
  (position [_] "Returns camera position as [x y] vector.")
  (combined [_] "The combined projection and view matrix.")
  (frustum [_] "Returns `[left-x right-x bottom-y top-y]`.")
  (set-position! [_ [x y]])
  (set-zoom! [_ value] "Initial zoom is `1`.")
  (viewport-width [_])
  (viewport-height [_])
  (reset-zoom! [_]  "Sets the zoom value to 1.")
  (inc-zoom! [_ amount] "Applies the amount to the current zoom, not lower than `0.1`."))
