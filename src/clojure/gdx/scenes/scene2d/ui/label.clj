(ns clojure.gdx.scenes.scene2d.ui.label
  (:import (com.badlogic.gdx.scenes.scene2d.ui Label)))

(defn set-text! [^Label label text]
  (.setText label (str text)))
