(ns clojure.gdx.graphics.g2d.batch
  "A Batch is used to draw 2D rectangles that reference a texture (region). The class will batch the drawing commands and optimize them for processing by the GPU.

  To draw something with a Batch one has to first call the begin() method which will setup appropriate render states. When you are done with drawing you have to call end() which will actually draw the things you specified.

  All drawing commands of the Batch operate in screen coordinates. The screen coordinate system has an x-axis pointing to the right, an y-axis pointing upwards and the origin is in the lower left corner of the screen. You can also provide your own transformation and projection matrices if you so wish.

  A Batch is managed. In case the OpenGL context is lost all OpenGL resources a Batch uses internally get invalidated. A context is lost when a user switches to another application or receives an incoming call on Android. A Batch will be automatically reloaded after the OpenGL context is restored.

  A Batch is a pretty heavy object so you should only ever have one in your program.

  A Batch works with OpenGL ES 2.0. It will use its own custom shader to draw all provided sprites. You can set your own custom shader via setShader(ShaderProgram).

  A Batch has to be disposed if it is no longer used.

  https://javadoc.io/static/com.badlogicgames.gdx/gdx/1.13.0/com/badlogic/gdx/graphics/g2d/Batch.html"
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
