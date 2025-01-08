(ns clojure.gdx.input)

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
