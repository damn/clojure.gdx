(ns clojure.gdx.graphics.colors
  (:import (com.badlogic.gdx.graphics Colors)))

(defn put [name-str color]
  (Colors/put name-str color))
