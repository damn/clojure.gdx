(ns clojure.gdx
  (:import (com.badlogic.gdx Gdx)))

(defn context []
  {:audio    Gdx/audio
   :files    Gdx/files
   :graphics Gdx/graphics
   :input    Gdx/input})
