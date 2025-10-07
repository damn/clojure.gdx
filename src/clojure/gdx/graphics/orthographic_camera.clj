(ns clojure.gdx.graphics.orthographic-camera
  "A camera with orthographic projection."
  (:require [clojure.gdx.math.vector3 :as vector3])
  (:import (com.badlogic.gdx.graphics OrthographicCamera)))

(defn create
  ([]
   (OrthographicCamera.))
  ([& {:keys [y-down? world-width world-height]}]
   (doto (OrthographicCamera.)
     (.setToOrtho y-down? world-width world-height))))

(defn viewport-height [^OrthographicCamera camera]
  (.viewportHeight camera))

(defn viewport-width [^OrthographicCamera camera]
  (.viewportWidth camera))

(defn position [^OrthographicCamera camera]
  (vector3/clojurize (.position camera)))

(defn frustum [^OrthographicCamera camera]
  (.frustum camera))

(defn zoom
  "the zoom of the camera (default `1`)."
  [^OrthographicCamera camera]
  (.zoom camera))

(defn combined
  "the combined projection and view matrix"
  [^OrthographicCamera camera]
  (.combined camera))

(defn set-position! [^OrthographicCamera this [x y]]
  (set! (.x (.position this)) (float x))
  (set! (.y (.position this)) (float y))
  (.update this))

(defn set-zoom! [^OrthographicCamera this amount]
  (set! (.zoom this) amount)
  (.update this))
