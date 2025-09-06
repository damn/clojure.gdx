(ns clojure.gdx
  (:import (com.badlogic.gdx Gdx)))

(defn post-runnable! [f]
  (.postRunnable Gdx/app f))

(defn graphics []
  Gdx/graphics)

(defn files []
  Gdx/files)

(defn audio []
  (Gdx/audio))

(defn input []
  Gdx/input)
