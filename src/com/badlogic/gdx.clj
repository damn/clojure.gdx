(ns com.badlogic.gdx
  (:require [clojure.gdx.application.extends])
  (:import (com.badlogic.gdx Gdx)))

(defn get-state []
  {:ctx/app      Gdx/app
   :ctx/audio    Gdx/audio
   :ctx/files    Gdx/files
   :ctx/graphics Gdx/graphics
   :ctx/input    Gdx/input})
