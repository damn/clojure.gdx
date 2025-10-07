(ns clojure.gdx.input.buttons
  (:import (com.badlogic.gdx Input$Buttons)))

(def k->value
  {:back    Input$Buttons/BACK
   :forward Input$Buttons/FORWARD
   :left    Input$Buttons/LEFT
   :middle  Input$Buttons/MIDDLE
   :right   Input$Buttons/RIGHT})
