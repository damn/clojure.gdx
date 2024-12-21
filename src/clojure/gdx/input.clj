(ns clojure.gdx.input
  (:require [clojure.gdx.interop :refer [k->input-button k->input-key]])
  (:import (com.badlogic.gdx Gdx)))

(defn x []
  (.getX Gdx/input))

(defn y []
  (.getY Gdx/input))

(defn button-just-pressed? [b]
  (.isButtonJustPressed Gdx/input (k->input-button b)))

(defn key-just-pressed? [k]
  (.isKeyJustPressed Gdx/input (k->input-key k)))

(defn key-pressed? [k]
  (.isKeyPressed Gdx/input (k->input-key k)))

(defn set-processor [input-processor]
  (.setInputProcessor Gdx/input processor))
