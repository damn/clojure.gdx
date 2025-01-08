(ns cloure.gdx.graphics.g2d.batch
  (:import (com.badlogic.gdx.graphics.g2d Batch)))

(defn set-projection-matrix
  "Sets the projection matrix to be used by this Batch. If this is called inside a begin()/end() block, the current batch is flushed to the gpu."
  [batch projection]
  (Batch/.setProjectionMatrix batch projection))

(defn begin
  "Sets up the Batch for drawing. This will disable depth buffer writing. It enables blending and texturing. If you have more texture units enabled than the first one you have to disable them before calling this. Uses a screen coordinate system by default where everything is given in pixels. You can specify your own projection and modelview matrices via setProjectionMatrix(Matrix4) and setTransformMatrix(Matrix4)."
  [batch]
  (Batch/.begin batch))

(defn end
  "Finishes off rendering. Enables depth writes, disables blending and texturing. Must always be called after a call to begin()"
  [batch]
  (Batch/.end batch))

(defn set-color
  "Sets the color used to tint images when they are added to the Batch. Default is Color.WHITE."
  [batch color]
  (Batch/.setColor batch color))

(defn draw
  "Draws a rectangle with the bottom left corner at x,y and stretching the region to cover the given width and height. The rectangle is offset by origin-x, origin-y relative to the origin. Scale specifies the scaling factor by which the rectangle should be scaled around origin-x, origin-y. Rotation specifies the angle of counter clockwise rotation of the rectangle around origin-x, originy."
  [batch texture-region & {:keys [x y origin-x origin-y width height scale-x scale-y rotation]}]
  (Batch/.draw batch
               texture-region
               x
               y
               origin-x
               origin-y
               width
               height
               scale-x
               scale-y
               rotation))
