(ns clojure.gdx.utils.screen
  (:import (com.badlogic.gdx.utils ScreenUtils)))

(defn clear
  "Clears the color currently bound OpenGL frame buffer with the specified Color."
  [color]
  (ScreenUtils/clear color))
