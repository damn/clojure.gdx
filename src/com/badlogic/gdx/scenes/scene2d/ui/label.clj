(ns com.badlogic.gdx.scenes.scene2d.ui.label
  (:require [clojure.scene2d.ui.label])
  (:import (com.badlogic.gdx.scenes.scene2d.ui Label)))

(extend-type Label
  clojure.scene2d.ui.label/Label
  (set-text! [label text]
    (.setText label (str text))))
