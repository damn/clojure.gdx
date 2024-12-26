(ns clojure.gdx
  (:require [clojure.gdx.interop :refer [k->input-button k->input-key]])
  (:import (com.badlogic.gdx Gdx Application Files Graphics Input)
           (com.badlogic.gdx.audio Sound)
           (com.badlogic.gdx.files FileHandle)
           (com.badlogic.gdx.graphics Color Colors OrthographicCamera Pixmap Pixmap$Format)
           (com.badlogic.gdx.graphics.g2d Batch SpriteBatch)
           (com.badlogic.gdx.math MathUtils)
           (com.badlogic.gdx.utils Disposable ScreenUtils)))

(defn context []
  {:app      Gdx/app
   :audio    Gdx/audio
   :files    Gdx/files
   :gl       Gdx/gl
   :gl20     Gdx/gl20
   :gl30     Gdx/gl30
   :gl31     Gdx/gl31
   :gl32     Gdx/gl32
   :graphics Gdx/graphics
   :input    Gdx/input
   :net      Gdx/net})

(defn exit [context]
  (Application/.exit (:app context)))

(defn post-runnable [context runnable]
  (Application/.postRunnable (:app context) runnable))

(defn internal-file [context file]
  (Files/.internal (:files context) file))

(defn delta-time [context]
  (Graphics/.getDeltaTime (:graphics context)))

(defn frames-per-second [context]
  (Graphics/.getFramesPerSecond (:graphics context)))

(defn cursor [context pixmap hotspot-x hotspot-y]
  (Graphics/.newCursor (:graphics context) pixmap hotspot-x hotspot-y))

(defn set-cursor [context cursor]
  (Graphics/.setCursor (:graphics context) cursor))

(defn input-x [context]
  (Input/.getX (:input context)))

(defn input-y [context]
  (Input/.getY (:input context)))

(defn button-just-pressed? [context b]
  (Input/.isButtonJustPressed (:input context) (k->input-button b)))

(defn key-just-pressed? [context k]
  (Input/.isKeyJustPressed (:input context) (k->input-key k)))

(defn key-pressed? [context k]
  (Input/.isKeyPressed (:input context) (k->input-key k)))

(defn set-input-processor [context input-processor]
  (Input/.setInputProcessor (:input context) input-processor))

(defn play
  "Plays the sound. If the sound is already playing, it will be played again, concurrently.

  Returns:
  the id of the sound instance if successful, or -1 on failure."
  [sound]
  (Sound/.play sound))

(defn sprite-batch
  "Constructs a new `com.badlogic.gdx.graphics.g2d.SpriteBatch` with a size of 1000, one buffer, and the default shader."
  []
  (SpriteBatch.))

(def dispose Disposable/.dispose)

(defn orthographic-camera []
  (OrthographicCamera.))

(defn equal? [a b]
  (MathUtils/isEqual a b))

(defn clamp [value min max]
  (MathUtils/clamp (float value) (float min) (float max)))

(defn degree->radians [degree]
  (* MathUtils/degreesToRadians (float degree)))

(def ^Color black Color/BLACK)
(def ^Color white Color/WHITE)

(defn color
  ([r g b]
   (color r g b 1))
  ([r g b a]
   (Color. (float r) (float g) (float b) (float a))))

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

(defn def-color
  "A general purpose class containing named colors that can be changed at will. For example, the markup language defined by the BitmapFontCache class uses this class to retrieve colors and the user can define his own colors.

  Convenience method to add a color with its name. The invocation of this method is equivalent to the expression Colors.getColors().put(name, color)

  Parameters:
  name - the name of the color
  color - the color
  Returns:
  the previous color associated with name, or null if there was no mapping for name ."
  [name-str color]
  (Colors/put name-str color))

(defn pixmap
  ([^FileHandle file-handle]
   (Pixmap. file-handle))
  ([width height ^Pixmap$Format format]
   (Pixmap. (int width) (int height) format)))

(defn draw-pixel [^Pixmap pixmap x y]
  (.drawPixel pixmap x y))

(defn clear-screen [color]
  (ScreenUtils/clear color))
