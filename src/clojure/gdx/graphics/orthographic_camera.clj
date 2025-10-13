(ns clojure.gdx.graphics.orthographic-camera
  (:import (com.badlogic.gdx.graphics OrthographicCamera)))

(defn create []
  (OrthographicCamera.))

(defn set-to-ortho! [camera y-down? world-width world-height]
  (OrthographicCamera/.setToOrtho camera y-down? world-width world-height))
