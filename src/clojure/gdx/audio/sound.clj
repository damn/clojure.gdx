(ns clojure.gdx.audio.sound
  (:import (com.badlogic.gdx.audio Sound)))

(defn play! [^Sound sound]
  (.play sound))

(defn dispose! [^Sound sound]
  (.dispose sound))
