(ns clojure.gdx.colors
  (:require [com.badlogic.gdx.graphics.color :as color])
  (:import (com.badlogic.gdx.graphics Colors)))

(defn put! [colors]
  (doseq [[name color-params] colors]
    (Colors/put name (color/->java color-params))))
