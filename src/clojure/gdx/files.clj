(ns clojure.gdx.files
  (:import (com.badlogic.gdx Files)))

(defn internal [files file]
  (Files/.internal files file))
