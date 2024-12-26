(ns clojure.gdx
  (:require [clojure.gdx.interop :refer [k->input-button k->input-key]])
  (:import (com.badlogic.gdx Gdx Application Files Graphics Input)
           (com.badlogic.gdx.audio Sound)
           (com.badlogic.gdx.graphics OrthographicCamera)
           (com.badlogic.gdx.graphics.g2d SpriteBatch)
           (com.badlogic.gdx.math MathUtils)
           (com.badlogic.gdx.utils Disposable)))

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
