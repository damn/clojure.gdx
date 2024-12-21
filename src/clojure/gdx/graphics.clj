(ns clojure.gdx.graphics
  (:import (com.badlogic.gdx Gdx)))

(defn cursor [pixmap hotspot-x hotspot-y]
  (.newCursor Gdx/graphics pixmap hotspot-x hotspot-y))

(defn set-cursor [cursor]
  (.setCursor Gdx/graphics cursor))

(defn frames-per-second []
  (.getFramesPerSecond Gdx/graphics))

(defn delta-time []
  (.getDeltaTime Gdx/graphics))
