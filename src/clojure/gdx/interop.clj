(ns clojure.gdx.interop
  (:require [clojure.string :as str])
  (:import (com.badlogic.gdx.utils Align)))

(defn static-field [klass-str k]
  (eval (symbol (str "com.badlogic.gdx." klass-str "/" (str/replace (str/upper-case (name k)) "-" "_")))))

(def k->input-button (partial static-field "Input$Buttons"))
(def k->input-key    (partial static-field "Input$Keys"))
(def k->color        (partial static-field "graphics.Color"))

(defn k->align [k]
  (case k
    :center Align/center
    :left   Align/left
    :right  Align/right))
