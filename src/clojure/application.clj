(ns clojure.application)

(defprotocol Listener
  "A `Listener` is called when the application is created, resumed, rendering, paused or destroyed. All methods are called in a thread that has the OpenGL context current. You can thus safely create and manipulate graphics resources.

  The `Listener` protocol follows the standard Android activity life-cycle and is emulated on the desktop accordingly."
  (create [_ context] "Called when the application is first created.

                      Parameters:

                      `context` - the gdx context.")

  (dispose [_] "Called when the application is destroyed.")

  (pause [_] "Called when the application is paused.")

  (render [_] "Called when the application should render itself.")

  (resize [_ width height] "Called when the application is resized. This can happen at any point during a non-paused state but will never happen before a call to `create`.

                           Parameters:

                           `width` - the new width in pixels

                           `height` - the new height in pixels")

  (resume [_] "Called when the application is resumed from a paused state. On Android this happens when the activity gets focus again. On the desktop this method will never be called."))

(defprotocol Application
  (exit [_])
  (post-runnable [_ runnable]))
