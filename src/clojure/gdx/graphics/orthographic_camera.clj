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

(defn reset-zoom! [cam]
  (set-zoom! cam 1))

(defn inc-zoom! [cam by]
  (set-zoom! cam (max 0.1 (+ (zoom cam) by))))

(defn frustum [^OrthographicCamera camera]
  (let [plane-points (mapv vector3/clojurize (.planePoints (.frustum camera)))
        frustum-points (take 4 plane-points)
        left-x   (apply min (map first  frustum-points))
        right-x  (apply max (map first  frustum-points))
        bottom-y (apply min (map second frustum-points))
        top-y    (apply max (map second frustum-points))]
    [left-x right-x bottom-y top-y]))

(defn visible-tiles [camera]
  (let [[left-x right-x bottom-y top-y] (frustum camera)]
    (for [x (range (int left-x)   (int right-x))
          y (range (int bottom-y) (+ 2 (int top-y)))]
      [x y])))

(defn calculate-zoom
  "calculates the zoom value for camera to see all the 4 points."
  [camera & {:keys [left top right bottom]}]
  (let [viewport-width  (viewport-width  camera)
        viewport-height (viewport-height camera)
        [px py] (position camera)
        px (float px)
        py (float py)
        leftx (float (left 0))
        rightx (float (right 0))
        x-diff (max (- px leftx) (- rightx px))
        topy (float (top 1))
        bottomy (float (bottom 1))
        y-diff (max (- topy py) (- py bottomy))
        vp-ratio-w (/ (* x-diff 2) viewport-width)
        vp-ratio-h (/ (* y-diff 2) viewport-height)
        new-zoom (max vp-ratio-w vp-ratio-h)]
    new-zoom))
