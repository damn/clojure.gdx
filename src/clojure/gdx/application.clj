(ns clojure.gdx.application
  (:require [clojure.application :as application]
            [clojure.audio]
            [clojure.audio.sound]
            [clojure.disposable]
            [clojure.files]
            [clojure.files.file-handle]
            [clojure.graphics]
            [clojure.graphics.pixmap]
            [clojure.graphics.texture]
            [clojure.graphics.texture-region]
            [clojure.input]
            [clojure.java.io :as io])
  (:import (com.badlogic.gdx Application
                             ApplicationListener
                             Audio
                             Files
                             Gdx
                             Graphics
                             Input
                             Input$Buttons
                             Input$Keys)
           (com.badlogic.gdx.backends.lwjgl3 Lwjgl3Application
                                             Lwjgl3ApplicationConfiguration
                                             Lwjgl3WindowConfiguration)
           (com.badlogic.gdx.audio Sound)
           (com.badlogic.gdx.files FileHandle)
           (com.badlogic.gdx.graphics GL20
                                      Pixmap
                                      Pixmap$Format
                                      Texture)
           (com.badlogic.gdx.graphics.g2d TextureRegion)
           (com.badlogic.gdx.utils Disposable
                                   SharedLibraryLoader
                                   Os)
           (java.awt Taskbar
                     Toolkit)
           (org.lwjgl.system Configuration)))

(let [value->keyword {Os/Android :android
                      Os/IOS     :ios
                      Os/Linux   :linux
                      Os/MacOsX  :mac
                      Os/Windows :windows}]
  (defn- operating-system []
    (value->keyword SharedLibraryLoader/os)))

(defn- set-taskbar-icon! [path]
  (.setIconImage (Taskbar/getTaskbar)
                 (.getImage (Toolkit/getDefaultToolkit)
                            (io/resource path))))

(defn- set-glfw-async! []
  (.set Configuration/GLFW_LIBRARY_NAME "glfw_async"))

(defn- ->ApplicationListener
  [listener]
  (reify ApplicationListener
    (create [_]
      (application/create listener
                          {:ctx/app      Gdx/app
                           :ctx/audio    Gdx/audio
                           :ctx/files    Gdx/files
                           :ctx/graphics Gdx/graphics
                           :ctx/input    Gdx/input}))
    (dispose [_]
      (application/dispose listener))
    (render [_]
      (application/render listener))
    (resize [_ width height]
      (application/resize listener width height))
    (pause [_]
      (application/pause listener))
    (resume [_]
      (application/resume listener))))

(defn- set-window-config-option! [^Lwjgl3WindowConfiguration object k v]
  (case k
    :windowed-mode (.setWindowedMode object
                                     (int (:width v))
                                     (int (:height v)))
    :title (.setTitle object (str v))))

(defn- ->Lwjgl3ApplicationConfiguration [config]
  (let [obj (Lwjgl3ApplicationConfiguration.)]
    (doseq [[k v] config]
      (case k
        :foreground-fps (.setForegroundFPS obj (int v))
        (set-window-config-option! obj k v)))
    obj))

(defn start!
  [{:keys [listener config]}]
  (when (= (operating-system) :mac)
    (set-glfw-async!)
    (set-taskbar-icon! "icon.png"))
  (Lwjgl3Application. (->ApplicationListener listener)
                      (->Lwjgl3ApplicationConfiguration config)))

(extend-type Application
  clojure.application/Application
  (post-runnable! [this f]
    (.postRunnable this f)))

(extend-type Audio
  clojure.audio/Audio
  (sound [this file-handle]
    (.newSound this file-handle)))

(extend-type Sound
  clojure.audio.sound/Sound
  (play! [this]
    (.play this)))

(extend-type Files
  clojure.files/Files
  (internal [this path]
    (.internal this path)))

(extend-type FileHandle
  clojure.files.file-handle/FileHandle
  (list [this]
    (.list this))
  (directory? [this]
    (.isDirectory this))
  (extension [this]
    (.extension this))
  (path [this]
    (.path this)))

(extend-type Graphics
  clojure.graphics/Graphics
  (delta-time [this]
    (.getDeltaTime this))
  (frames-per-second [this]
    (.getFramesPerSecond this))
  (set-cursor! [this cursor]
    (.setCursor this cursor))
  (cursor [this pixmap hotspot-x hotspot-y]
    (.newCursor this pixmap hotspot-x hotspot-y))
  (clear!
    ([this [r g b a]]
     (clojure.graphics/clear! this r g b a))
    ([this r g b a]
     (let [clear-depth? false
           apply-antialiasing? false
           gl20 (.getGL20 this)]
       (GL20/.glClearColor gl20 r g b a)
       (let [mask (cond-> GL20/GL_COLOR_BUFFER_BIT
                    clear-depth? (bit-or GL20/GL_DEPTH_BUFFER_BIT)
                    (and apply-antialiasing? (.coverageSampling (.getBufferFormat this)))
                    (bit-or GL20/GL_COVERAGE_BUFFER_BIT_NV))]
         (GL20/.glClear gl20 mask)))))
  (texture [this file-handle]
    (Texture. ^FileHandle file-handle))
  (pixmap
    ([this ^FileHandle file-handle]
     (Pixmap. file-handle))
    ([this width height pixmap-format]
     (Pixmap. (int width)
              (int height)
              (case pixmap-format
                :pixmap.format/RGBA8888 Pixmap$Format/RGBA8888)))))

(extend-type Texture
  clojure.graphics.texture/Texture
  (region
    ([this]
     (TextureRegion. this))
    ([this [x y w h]]
     (TextureRegion. this
                     (int x)
                     (int y)
                     (int w)
                     (int h)))
    ([this x y w h]
     (TextureRegion. this
                     (int x)
                     (int y)
                     (int w)
                     (int h)))))

(extend-type TextureRegion
  clojure.graphics.texture-region/TextureRegion
  (dimensions [this]
    [(.getRegionWidth  this)
     (.getRegionHeight this)]))

(extend-type Pixmap
  clojure.graphics.pixmap/Pixmap
  (set-color! [this [r g b a]]
    (.setColor this r g b a))

  (draw-pixel! [this x y]
    (.drawPixel this x y))

  (texture [this]
    (Texture. this)))

(def ^:private input-buttons-k->value
  {:back    Input$Buttons/BACK
   :forward Input$Buttons/FORWARD
   :left    Input$Buttons/LEFT
   :middle  Input$Buttons/MIDDLE
   :right   Input$Buttons/RIGHT})

(def ^:private input-keys-k->value
  {:a                   Input$Keys/A
   :alt-left            Input$Keys/ALT_LEFT
   :alt-right           Input$Keys/ALT_RIGHT
   :any-key             Input$Keys/ANY_KEY
   :apostrophe          Input$Keys/APOSTROPHE
   :at                  Input$Keys/AT
   :b                   Input$Keys/B
   :back                Input$Keys/BACK
   :backslash           Input$Keys/BACKSLASH
   :backspace           Input$Keys/BACKSPACE
   :button-a            Input$Keys/BUTTON_A
   :button-b            Input$Keys/BUTTON_B
   :button-c            Input$Keys/BUTTON_C
   :button-circle       Input$Keys/BUTTON_CIRCLE
   :button-l1           Input$Keys/BUTTON_L1
   :button-l2           Input$Keys/BUTTON_L2
   :button-mode         Input$Keys/BUTTON_MODE
   :button-r1           Input$Keys/BUTTON_R1
   :button-r2           Input$Keys/BUTTON_R2
   :button-select       Input$Keys/BUTTON_SELECT
   :button-start        Input$Keys/BUTTON_START
   :button-thumbl       Input$Keys/BUTTON_THUMBL
   :button-thumbr       Input$Keys/BUTTON_THUMBR
   :button-x            Input$Keys/BUTTON_X
   :button-y            Input$Keys/BUTTON_Y
   :button-z            Input$Keys/BUTTON_Z
   :c                   Input$Keys/C
   :call                Input$Keys/CALL
   :camera              Input$Keys/CAMERA
   :caps-lock           Input$Keys/CAPS_LOCK
   :center              Input$Keys/CENTER
   :clear               Input$Keys/CLEAR
   :colon               Input$Keys/COLON
   :comma               Input$Keys/COMMA
   :control-left        Input$Keys/CONTROL_LEFT
   :control-right       Input$Keys/CONTROL_RIGHT
   :d                   Input$Keys/D
   :del                 Input$Keys/DEL
   :down                Input$Keys/DOWN
   :dpad-center         Input$Keys/DPAD_CENTER
   :dpad-down           Input$Keys/DPAD_DOWN
   :dpad-left           Input$Keys/DPAD_LEFT
   :dpad-right          Input$Keys/DPAD_RIGHT
   :dpad-up             Input$Keys/DPAD_UP
   :e                   Input$Keys/E
   :end                 Input$Keys/END
   :endcall             Input$Keys/ENDCALL
   :enter               Input$Keys/ENTER
   :envelope            Input$Keys/ENVELOPE
   :equals              Input$Keys/EQUALS
   :escape              Input$Keys/ESCAPE
   :explorer            Input$Keys/EXPLORER
   :f                   Input$Keys/F
   :f1                  Input$Keys/F1
   :f10                 Input$Keys/F10
   :f11                 Input$Keys/F11
   :f12                 Input$Keys/F12
   :f13                 Input$Keys/F13
   :f14                 Input$Keys/F14
   :f15                 Input$Keys/F15
   :f16                 Input$Keys/F16
   :f17                 Input$Keys/F17
   :f18                 Input$Keys/F18
   :f19                 Input$Keys/F19
   :f2                  Input$Keys/F2
   :f20                 Input$Keys/F20
   :f21                 Input$Keys/F21
   :f22                 Input$Keys/F22
   :f23                 Input$Keys/F23
   :f24                 Input$Keys/F24
   :f3                  Input$Keys/F3
   :f4                  Input$Keys/F4
   :f5                  Input$Keys/F5
   :f6                  Input$Keys/F6
   :f7                  Input$Keys/F7
   :f8                  Input$Keys/F8
   :f9                  Input$Keys/F9
   :focus               Input$Keys/FOCUS
   :forward-del         Input$Keys/FORWARD_DEL
   :g                   Input$Keys/G
   :grave               Input$Keys/GRAVE
   :h                   Input$Keys/H
   :headsethook         Input$Keys/HEADSETHOOK
   :home                Input$Keys/HOME
   :i                   Input$Keys/I
   :insert              Input$Keys/INSERT
   :j                   Input$Keys/J
   :k                   Input$Keys/K
   :l                   Input$Keys/L
   :left                Input$Keys/LEFT
   :left-bracket        Input$Keys/LEFT_BRACKET
   :m                   Input$Keys/M
   :media-fast-forward  Input$Keys/MEDIA_FAST_FORWARD
   :media-next          Input$Keys/MEDIA_NEXT
   :media-play-pause    Input$Keys/MEDIA_PLAY_PAUSE
   :media-previous      Input$Keys/MEDIA_PREVIOUS
   :media-rewind        Input$Keys/MEDIA_REWIND
   :media-stop          Input$Keys/MEDIA_STOP
   :menu                Input$Keys/MENU
   :meta-alt-left-on    Input$Keys/META_ALT_LEFT_ON
   :meta-alt-on         Input$Keys/META_ALT_ON
   :meta-alt-right-on   Input$Keys/META_ALT_RIGHT_ON
   :meta-shift-left-on  Input$Keys/META_SHIFT_LEFT_ON
   :meta-shift-on       Input$Keys/META_SHIFT_ON
   :meta-shift-right-on Input$Keys/META_SHIFT_RIGHT_ON
   :meta-sym-on         Input$Keys/META_SYM_ON
   :minus               Input$Keys/MINUS
   :mute                Input$Keys/MUTE
   :n                   Input$Keys/N
   :notification        Input$Keys/NOTIFICATION
   :num                 Input$Keys/NUM
   :num-0               Input$Keys/NUM_0
   :num-1               Input$Keys/NUM_1
   :num-2               Input$Keys/NUM_2
   :num-3               Input$Keys/NUM_3
   :num-4               Input$Keys/NUM_4
   :num-5               Input$Keys/NUM_5
   :num-6               Input$Keys/NUM_6
   :num-7               Input$Keys/NUM_7
   :num-8               Input$Keys/NUM_8
   :num-9               Input$Keys/NUM_9
   :num-lock            Input$Keys/NUM_LOCK
   :numpad-0            Input$Keys/NUMPAD_0
   :numpad-1            Input$Keys/NUMPAD_1
   :numpad-2            Input$Keys/NUMPAD_2
   :numpad-3            Input$Keys/NUMPAD_3
   :numpad-4            Input$Keys/NUMPAD_4
   :numpad-5            Input$Keys/NUMPAD_5
   :numpad-6            Input$Keys/NUMPAD_6
   :numpad-7            Input$Keys/NUMPAD_7
   :numpad-8            Input$Keys/NUMPAD_8
   :numpad-9            Input$Keys/NUMPAD_9
   :numpad-add          Input$Keys/NUMPAD_ADD
   :numpad-comma        Input$Keys/NUMPAD_COMMA
   :numpad-divide       Input$Keys/NUMPAD_DIVIDE
   :numpad-dot          Input$Keys/NUMPAD_DOT
   :numpad-enter        Input$Keys/NUMPAD_ENTER
   :numpad-equals       Input$Keys/NUMPAD_EQUALS
   :numpad-left-paren   Input$Keys/NUMPAD_LEFT_PAREN
   :numpad-multiply     Input$Keys/NUMPAD_MULTIPLY
   :numpad-right-paren  Input$Keys/NUMPAD_RIGHT_PAREN
   :numpad-subtract     Input$Keys/NUMPAD_SUBTRACT
   :o                   Input$Keys/O
   :p                   Input$Keys/P
   :page-down           Input$Keys/PAGE_DOWN
   :page-up             Input$Keys/PAGE_UP
   :pause               Input$Keys/PAUSE
   :period              Input$Keys/PERIOD
   :pictsymbols         Input$Keys/PICTSYMBOLS
   :plus                Input$Keys/PLUS
   :pound               Input$Keys/POUND
   :power               Input$Keys/POWER
   :print-screen        Input$Keys/PRINT_SCREEN
   :q                   Input$Keys/Q
   :r                   Input$Keys/R
   :right               Input$Keys/RIGHT
   :right-bracket       Input$Keys/RIGHT_BRACKET
   :s                   Input$Keys/S
   :scroll-lock         Input$Keys/SCROLL_LOCK
   :search              Input$Keys/SEARCH
   :semicolon           Input$Keys/SEMICOLON
   :shift-left          Input$Keys/SHIFT_LEFT
   :shift-right         Input$Keys/SHIFT_RIGHT
   :slash               Input$Keys/SLASH
   :soft-left           Input$Keys/SOFT_LEFT
   :soft-right          Input$Keys/SOFT_RIGHT
   :space               Input$Keys/SPACE
   :star                Input$Keys/STAR
   :switch-charset      Input$Keys/SWITCH_CHARSET
   :sym                 Input$Keys/SYM
   :t                   Input$Keys/T
   :tab                 Input$Keys/TAB
   :u                   Input$Keys/U
   :unknown             Input$Keys/UNKNOWN
   :up                  Input$Keys/UP
   :v                   Input$Keys/V
   :volume-down         Input$Keys/VOLUME_DOWN
   :volume-up           Input$Keys/VOLUME_UP
   :w                   Input$Keys/W
   :x                   Input$Keys/X
   :y                   Input$Keys/Y
   :z                   Input$Keys/Z})

(extend-type Input
  clojure.input/Input
  (button-just-pressed? [this button]
    {:pre [(contains? input-buttons-k->value button)]}
    (.isButtonJustPressed this (input-buttons-k->value button)))

  (key-pressed? [this key]
    (assert (contains? input-keys-k->value key)
            (str "(pr-str key): "(pr-str key)))
    (.isKeyPressed this (input-keys-k->value key)))

  (key-just-pressed? [this key]
    {:pre [(contains? input-keys-k->value key)]}
    (.isKeyJustPressed this (input-keys-k->value key)))

  (set-processor! [this input-processor]
    (.setInputProcessor this input-processor))

  (mouse-position [this]
    [(.getX this)
     (.getY this)]))

(extend-type Disposable
  clojure.disposable/Disposable
  (dispose! [this]
    (.dispose this)))
