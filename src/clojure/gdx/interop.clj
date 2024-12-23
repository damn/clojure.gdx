(ns clojure.gdx.interop
  (:require [clojure.gdx :as gdx]
            [clojure.string :as str])
  (:import (com.badlogic.gdx Gdx)
           (com.badlogic.gdx.utils Align)))

(defn bind-clojure.gdx-components
  "Creates global bindings for core LibGDX components in the `clojure.gdx` namespace,
  simplifying access to application, audio, graphics, input, and networking systems.

  Call this in `application/create` in a libgdx application backend implementation."
  []
  (.bindRoot #'gdx/app      Gdx/app)
  (.bindRoot #'gdx/audio    Gdx/audio)
  (.bindRoot #'gdx/files    Gdx/files)
  (.bindRoot #'gdx/gl       Gdx/gl)
  (.bindRoot #'gdx/gl20     Gdx/gl20)
  (.bindRoot #'gdx/gl30     Gdx/gl30)
  (.bindRoot #'gdx/gl31     Gdx/gl31)
  (.bindRoot #'gdx/gl32     Gdx/gl32)
  (.bindRoot #'gdx/graphics Gdx/graphics)
  (.bindRoot #'gdx/input    Gdx/input)
  (.bindRoot #'gdx/net      Gdx/net))

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
