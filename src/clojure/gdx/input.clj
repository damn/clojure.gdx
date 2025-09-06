(ns clojure.gdx.input
  (:require [clojure.gdx.input.buttons :as input.buttons]
            [clojure.gdx.input.keys :as input.keys])
  (:import (com.badlogic.gdx Input)))

(defn button-just-pressed? [^Input this button]
  {:pre [(contains? input.buttons/k->value button)]}
  (.isButtonJustPressed this (input.buttons/k->value button)))

(defn key-pressed? [^Input this key]
  {:pre [(contains? input.keys/k->value key)]}
  (.isKeyPressed this (input.keys/k->value key)))

(defn key-just-pressed? [^Input this key]
  {:pre [(contains? input.keys/k->value key)]}
  (.isKeyJustPressed this (input.keys/k->value key)))

(defn set-processor! [^Input this input-processor]
  (.setInputProcessor this input-processor))

(defn mouse-position [^Input this]
  [(Input/.getX this)
   (Input/.getY this)])
