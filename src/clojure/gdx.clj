(ns clojure.gdx
  (:require [clojure.gdx.interop :refer [k->input-button k->input-key]]
            ;
            [clojure.application]
            [clojure.audio]
            [clojure.audio.sound]
            [clojure.files]
            [clojure.files.file-handle]
            [clojure.graphics]
            [clojure.graphics.2d.batch]
            [clojure.input]
            [clojure.utils.disposable])
  (:import (com.badlogic.gdx Gdx)))

(defn context []
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

(extend-type com.badlogic.gdx.Files
  clojure.files/Files
  (internal [this path]
    (.internal this path)))

(extend-type com.badlogic.gdx.Graphics
  clojure.graphics/Graphics
  (delta-time [this]
    (.getDeltaTime this))
  (frames-per-second [this]
    (.getFramesPerSecond this))
  (new-cursor [this pixmap hotspot-x hotspot-y]
    (.newCursor this pixmap hotspot-x hotspot-y))
  (set-cursor [this cursor]
    (.setCursor this cursor)))

(extend-type com.badlogic.gdx.Application
  clojure.application/Application
  (exit [this]
    (.exit this))
  (post-runnable [this runnable]
    (.postRunnable this runnable)))

(extend-type com.badlogic.gdx.Audio
  clojure.audio/Audio
  (device [_ sampling-rate mono?])
  (audio-recorder [_ sampling-rate mono?])
  (sound [_ file-handle])
  (music [_ file-handle])
  (switch-output-device [_ identifier])
  (available-output-devices [_]))

(extend-type com.badlogic.gdx.files.FileHandle
  clojure.files.file-handle/FileHandle
  (list [this]
    (.list this))
  (directory? [this]
    (.isDirectory this))
  (extension [this]
    (.extension this))
  (path [this]
    (.path this)))

(extend-type com.badlogic.gdx.Input
  clojure.input/Input
  (x [this]
    (.getX this))

  (y [this]
    (.getY this))

  (button-just-pressed? [this button]
    (.isButtonJustPressed this (k->input-button button)))

  (key-just-pressed? [this key]
    (.isKeyJustPressed this (k->input-key key)))

  (key-pressed? [this key]
    (.isKeyPressed this (k->input-key key)))

  (set-processor [this input-processor]
    (.setInputProcessor this input-processor)))

(extend-type com.badlogic.gdx.audio.Sound
  clojure.audio.sound/Sound
  (loop
    ([s]
     (.loop s))
    ([s volume]
     (.loop s volume))
    ([s volume pitch pan]
     (.loop s volume pitch pan)))
  (play
    ([s]
     (.play s))
    ([s volume]
     (.play s volume))
    ([s volume pitch pan]
     (.play s volume pitch pan)))
  (resume
    ([s]
     (.resume s))
    ([s id]
     (.resume s id)))
  (set-looping [s id looping?]
    (.setLooping s id looping?))
  (set-pan [s id pan volume]
    (.setPan s id pan volume))
  (set-pitch [s id pitch]
    (.setPitch [s id pitch]))
  (set-volume [s id volume]
    (.setVolume [s id volume]))
  (stop [s]
    (.stop s)))

(extend-type com.badlogic.gdx.utils.Disposable
  clojure.utils.disposable/Disposable
  (dispose [this]
    (.dispose this)))

(extend-type com.badlogic.gdx.graphics.g2d.Batch
  clojure.graphics.2d.batch/Batch
  (set-projection-matrix [batch projection]
    (.setProjectionMatrix batch projection))
  (begin [batch]
    (.begin batch))
  (end
    [batch]
    (.end batch))
  (set-color [batch color]
    (.setColor batch color))
  (draw [batch texture-region {:keys [x y origin-x origin-y width height scale-x scale-y rotation]}]
    (.draw batch
           texture-region
           x
           y
           origin-x
           origin-y
           width
           height
           scale-x
           scale-y
           rotation)))
