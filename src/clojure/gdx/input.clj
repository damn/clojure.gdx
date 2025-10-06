(ns clojure.gdx.input)

(defprotocol Input
  (button-just-pressed? [_ button])
  (key-pressed? [_ key])
  (key-just-pressed? [_ key])
  (set-processor! [_ input-processor])
  (mouse-position [_]))
