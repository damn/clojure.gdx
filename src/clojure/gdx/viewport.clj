(ns clojure.gdx.viewport
  (:require [clojure.gdx.math.vector2 :as vector2]
            [clojure.gdx.utils.viewport :as viewport]))

(def camera       viewport/camera)
(def world-width  viewport/world-width)
(def world-height viewport/world-height)
(def update!      viewport/update!)

(defn unproject [viewport [x y]]
  (-> viewport
      (viewport/unproject (vector2/->java x y))
      vector2/->clj))
