(ns clojure.gdx.input
  "Interface to the input facilities. This allows polling the state of the keyboard, the touch screen and the accelerometer. On some backends (desktop, gwt, etc) the touch screen is replaced by mouse input. The accelerometer is of course not available on all backends.

  Instead of polling for events, one can process all input events with an InputProcessor. You can set the InputProcessor via the setInputProcessor(InputProcessor) method. It will be called before the ApplicationListener.render() method in each frame.

  Keyboard keys are translated to the constants in Input.Keys transparently on all systems. Do not use system specific key constants.

  The class also offers methods to use (and test for the presence of) other input systems like vibration, compass, on-screen keyboards, and cursor capture. Support for simple input dialogs is also provided. "
  (:require [clojure.gdx.interop :refer [k->input-button k->input-key]])
  (:import (com.badlogic.gdx Input)))

(defn x
  "The x coordinate of the last touch on touch screen devices and the current mouse position on desktop for the first pointer in screen coordinates. The screen origin is the top left corner."
  [input]
  (Input/.getX input))

(defn y
  "The y coordinate of the last touch on touch screen devices and the current mouse position on desktop for the first pointer in screen coordinates. The screen origin is the top left corner."
  [input]
  (Input/.getY input))

(defn button-just-pressed?
  "Returns whether a given button has just been pressed. Button constants can be found in Input.Buttons. On Android only the Buttons#LEFT constant is meaningful before version 4.0. On WebGL (GWT), only LEFT, RIGHT and MIDDLE buttons are supported."
  [input b]
  (Input/.isButtonJustPressed input (k->input-button b)))

(defn key-just-pressed? [input k]
  (Input/.isKeyJustPressed input (k->input-key k)))

(defn key-pressed? [input k]
  (Input/.isKeyPressed input (k->input-key k)))

(defn set-processor [input input-processor]
  (Input/.setInputProcessor input input-processor))
