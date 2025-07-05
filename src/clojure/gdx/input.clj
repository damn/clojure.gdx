(ns clojure.gdx.input
  (:require [clojure.core-inject :refer [opts-get]]
            [clojure.gdx.input.buttons :as input.buttons]
            [clojure.gdx.input.keys    :as input.keys])
  (:import (com.badlogic.gdx Input)))

(defn button-just-pressed? [^Input this button]
  (.isButtonJustPressed this (opts-get input.buttons/k->value button)))

(defn key-pressed? [^Input this key]
  (.isKeyPressed this (opts-get input.keys/k->value key)))

(defn key-just-pressed? [^Input this key]
  (.isKeyJustPressed this (opts-get input.keys/k->value key)))

(defn mouse-position [^Input this]
  [(.getX this)
   (.getY this)])

(defn set-processor! [^Input this input-processor]
  (.setInputProcessor this input-processor))
