(ns clojure.gdx.graphics.camera
  (:refer-clojure :exclude [update])
  (:import (com.badlogic.gdx.graphics Camera OrthographicCamera)))

(defn set-to-ortho
  "Sets this camera to an orthographic projection, centered at (viewport-width/2, viewport-height/2), with the y-axis pointing up or down."
  [camera viewport-width viewport-height & {:keys [y-down?]}]
  (OrthographicCamera/.setToOrtho camera y-down? viewport-width viewport-height))

(defn position
  "Returns camera position as [x y] vector."
  [^Camera camera]
  [(.x (.position camera))
   (.y (.position camera))])

(defn combined
  "The combined projection and view matrix."
  [^Camera camera]
  (.combined camera))

(defn frustum [^Camera camera]
  (.frustum camera))

(defn viewport-width [^Camera camera]
  (.viewportWidth camera))

(defn viewport-height [^Camera camera]
  (.viewportHeight camera))

(defn zoom [^OrthographicCamera camera]
  (.zoom camera))

(defn set-position
  "Sets x and y."
  [^Camera camera [x y]]
  (set! (.x (.position camera)) (float x))
  (set! (.y (.position camera)) (float y)))

(defn update [^Camera camera]
  (.update camera))

(defn set-zoom [^OrthographicCamera camera amount]
  (set! (.zoom camera) amount))
