(ns clojure.gdx.interop
  (:import (com.badlogic.gdx.utils Align)))

(defn k->align [k]
  (case k
    :center Align/center
    :left   Align/left
    :right  Align/right))
