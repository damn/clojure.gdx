(ns clojure.gdx
  (:require [clojure.gdx.interop :refer [k->input-button k->input-key k->viewport-field]])
  (:import (clojure.lang IFn ILookup)
           (com.badlogic.gdx Gdx Application Files Graphics Input)
           (com.badlogic.gdx.assets AssetManager)
           (com.badlogic.gdx.audio Sound)
           (com.badlogic.gdx.files FileHandle)
           (com.badlogic.gdx.graphics Color Colors OrthographicCamera Pixmap Pixmap$Format Texture)
           (com.badlogic.gdx.graphics.g2d Batch SpriteBatch TextureRegion)
           (com.badlogic.gdx.math MathUtils Vector2 Circle Intersector Rectangle)
           (com.badlogic.gdx.utils Disposable ScreenUtils)
           (com.badlogic.gdx.utils.viewport FitViewport Viewport)))

(defn context
  "Returns a map of the libgdx context. Call after application `create`."
  []
  {::app      Gdx/app
   ::audio    Gdx/audio
   ::files    Gdx/files
   ::gl       Gdx/gl
   ::gl20     Gdx/gl20
   ::gl30     Gdx/gl30
   ::gl31     Gdx/gl31
   ::gl32     Gdx/gl32
   ::graphics Gdx/graphics
   ::input    Gdx/input
   ::net      Gdx/net})

(defn exit
  "Schedule an exit from the application. On android, this will cause a call to `pause` and `dispose` some time in the future,
  it will not immediately finish your application. On iOS this should be avoided in production as it breaks Apples guidelines."
  [context]
  (Application/.exit (::app context)))

(defn post-runnable
  "Posts a Runnable on the main loop thread. In a multi-window application, the [context] values may be unpredictable at the time the Runnable is executed. If graphics or input are needed, they should be bound to a variable to be used in the Runnable."
  [context runnable]
  (Application/.postRunnable (::app context) runnable))

(defn internal-file
  "Path relative to the asset directory on Android and to the application's root directory on the desktop. On the desktop, if the file is not found, then the classpath is checked. This enables files to be found when using JWS or applets. Internal files are always readonly."
  [context file]
  (Files/.internal (::files context) file))

(defn delta-time
  "The time span between the current frame and the last frame in seconds."
  [context]
  (Graphics/.getDeltaTime (::graphics context)))

(defn frames-per-second
  "The average number of frames per second."
  [context]
  (Graphics/.getFramesPerSecond (::graphics context)))

(defn cursor
  "Create a new cursor represented by the Pixmap. The Pixmap must be in RGBA8888 format, width & height must be powers-of-two greater than zero (not necessarily equal) and of a certain minimum size (32x32 is a safe bet), and alpha transparency must be single-bit (i.e., 0x00 or 0xFF only). This function returns a Cursor object that can be set as the system cursor by calling setCursor(Cursor) .

  Parameters:

  pixmap - the mouse cursor image as a Pixmap

  xHotspot - the x location of the hotspot pixel within the cursor image (origin top-left corner)

  yHotspot - the y location of the hotspot pixel within the cursor image (origin top-left corner)

  Returns:

  a cursor object that can be used by calling setCursor(Cursor) or null if not supported"
  [context pixmap hotspot-x hotspot-y]
  (Graphics/.newCursor (::graphics context) pixmap hotspot-x hotspot-y))

(defn set-cursor
  "Only viable on the lwjgl-backend and on the gwt-backend. Browsers that support cursor:url() and support the png format (the pixmap is converted to a data-url of type image/png) should also support custom cursors. Will set the mouse cursor image to the image represented by the Cursor. It is recommended to call this function in the main render thread, and maximum one time per frame.

  Parameters:
  cursor - the mouse cursor as a Cursor"
  [context cursor]
  (Graphics/.setCursor (::graphics context) cursor))

(defn input-x
  "The x coordinate of the last touch on touch screen devices and the current mouse position on desktop for the first pointer in screen coordinates. The screen origin is the top left corner."
  [context]
  (Input/.getX (::input context)))

(defn input-y
  "The y coordinate of the last touch on touch screen devices and the current mouse position on desktop for the first pointer in screen coordinates. The screen origin is the top left corner."
  [context]
  (Input/.getY (::input context)))

(defn button-just-pressed?
  "Returns whether a given button has just been pressed. Button constants can be found in Input.Buttons. On Android only the Buttons#LEFT constant is meaningful before version 4.0.

  Parameters:
  button - the button to check.

  Returns:
  true or false."
  [context b]
  (Input/.isButtonJustPressed (::input context) (k->input-button b)))

(defn key-just-pressed?
  "Returns whether the key has just been pressed.

  Parameters:
  key - The key code as found in Input.Keys.

  Returns:
  true or false. "
  [context k]
  (Input/.isKeyJustPressed (::input context) (k->input-key k)))

(defn key-pressed?
  "Returns whether the key is pressed.

  Parameters:
  key - The key code as found in Input.Keys.

  Returns:
  true or false."
  [context k]
  (Input/.isKeyPressed (::input context) (k->input-key k)))

(defn set-input-processor
  "Sets the InputProcessor that will receive all touch and key input events. It will be called before the `ApplicationListener.render()` method each frame.

  Parameters:
  processor - the InputProcessor "
  [context input-processor]
  (Input/.setInputProcessor (::input context) input-processor))

(defn play
  "Plays the sound. If the sound is already playing, it will be played again, concurrently.

  Returns:
  the id of the sound instance if successful, or -1 on failure."
  [sound]
  (Sound/.play sound))

(defn sprite-batch
  "Draws batched quads using indices.

  Constructs a new sprite-batch with a size of 1000, one buffer, and the default shader."
  []
  (SpriteBatch.))

(defn dispose
  "Releases all resources of this object."
  [disposable]
  (Disposable/.dispose disposable))

(defn orthographic-camera
  "A camera with orthographic projection."
  []
  (OrthographicCamera.))

(defn equal?
  "Returns true if a is nearly equal to b. The function uses the default floating error tolerance."
  [a b]
  (MathUtils/isEqual a b))

(defn clamp [value min max]
  (MathUtils/clamp (float value) (float min) (float max)))

(defn degree->radians [degree]
  (* MathUtils/degreesToRadians (float degree)))

(def ^Color ^{:doc "The color black."} black Color/BLACK)
(def ^Color ^{:doc "The color white."} white Color/WHITE)

(defn color
  "Creates a color object, holding the r, g, b and alpha component as floats in the range [0,1]. All methods perform clamping on the internal values after execution."
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
  "A Pixmap represents an image in memory. It has a width and height expressed in pixels as well as a Pixmap.Format specifying the number and order of color components per pixel. Coordinates of pixels are specified with respect to the top left corner of the image, with the x-axis pointing to the right and the y-axis pointing downwards.

  By default all methods use blending. You can disable blending with setBlending(Blending), which may reduce blitting time by ~30%. The drawPixmap(Pixmap, int, int, int, int, int, int, int, int) method will scale and stretch the source image to a target image. There either nearest neighbour or bilinear filtering can be used.

  A Pixmap stores its data in native heap memory. It is mandatory to call dispose() when the pixmap is no longer needed, otherwise memory leaks will result"
  ([^FileHandle file-handle]
   (Pixmap. file-handle))
  ([width height ^Pixmap$Format format]
   (Pixmap. (int width) (int height) format)))

(defn draw-pixel
  "Draws a pixel at the given location with the current color."
  [^Pixmap pixmap x y]
  (.drawPixel pixmap x y))

(defn clear-screen
  "Clears the color currently bound OpenGL frame buffer with the specified Color."
  [color]
  (ScreenUtils/clear color))

(defn fit-viewport
  "A ScalingViewport that uses Scaling.fit so it keeps the aspect ratio by scaling the world up to fit the screen, adding black bars (letterboxing) for the remaining space."
  [width height camera]
  (proxy [FitViewport ILookup] [width height camera]
    (valAt
      ([key]
       (k->viewport-field this key))
      ([key _not-found]
       (k->viewport-field this key)))))

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

(defmulti
  ^{:doc "Checks if the two shapes (circle or rectangle) are overlapping."}
  overlaps?
  (fn [a b] [(class a) (class b)]))

(defmethod overlaps? [Circle Circle]
  [^Circle a ^Circle b]
  (Intersector/overlaps a b))

(defmethod overlaps? [Rectangle Rectangle]
  [^Rectangle a ^Rectangle b]
  (Intersector/overlaps a b))

(defmethod overlaps? [Rectangle Circle]
  [^Rectangle rect ^Circle circle]
  (Intersector/overlaps circle rect))

(defmethod overlaps? [Circle Rectangle]
  [^Circle circle ^Rectangle rect]
  (Intersector/overlaps circle rect))

(defn texture-region
  "Defines a rectangular area of a texture. The coordinate system used has its origin in the upper left corner with the x-axis pointing to the right and the y axis pointing downwards.

  Parameters:
  width - The width of the texture region. May be negative to flip the sprite when drawn.
  height - The height of the texture region. May be negative to flip the sprite when drawn."
  ([^Texture texture]
   (TextureRegion. texture))
  ([^Texture texture x y w h]
   (TextureRegion. texture (int x) (int y) (int w) (int h))))

(defn ->texture-region
  "Constructs a region with the same texture as the specified region and sets the coordinates relative to the specified region.

  Parameters:
  width - The width of the texture region. May be negative to flip the sprite when drawn.
  height - The height of the texture region. May be negative to flip the sprite when drawn. "
  [^TextureRegion texture-region x y w h]
  (TextureRegion. texture-region (int x) (int y) (int w) (int h)))

(defn dimensions
  "Returns the region's width and height as vector [w h]."
  [^TextureRegion texture-region]
  [(.getRegionWidth  texture-region)
   (.getRegionHeight texture-region)])

(defn texture
  "A texture wraps a standard OpenGL ES texture.

  A texture can be managed. If the OpenGL context is lost all managed textures get invalidated. This happens when a user switches to another application or receives an incoming call. Managed textures get reloaded automatically.

  A texture has to be bound via the `GLTexture.bind()` method in order for it to be applied to geometry. The texture will be bound to the currently active texture unit specified via `GL20.glActiveTexture(int)`.

  You can draw Pixmaps to a texture at any time. The changes will be automatically uploaded to texture memory. This is of course not extremely fast so use it with care. It also only works with unmanaged textures.

  A texture must be disposed when it is no longer used"
  [^Pixmap pixmap]
  (Texture. pixmap))

(defn asset-manager
  "Loads and stores assets like textures, bitmapfonts, tile maps, sounds, music and so on."
  []
  (proxy [AssetManager IFn] []
    (invoke [^String path]
      (if (AssetManager/.contains this path)
        (AssetManager/.get this path)
        (throw (IllegalArgumentException. (str "Asset cannot be found: " path)))))))
