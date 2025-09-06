(ns clojure.gdx.scenes.scene2d.ui.window
  (:require [clojure.gdx.scenes.scene2d.actor :as actor])
  (:import (com.badlogic.gdx.scenes.scene2d.ui Window)))

(defn find-ancestor ^Window [actor]
  (if-let [p (actor/parent actor)]
    (if (instance? Window p)
      p
      (find-ancestor p))
    (throw (Error. (str "Actor has no parent window " actor)))))

(defn pack-ancestors! [actor]
  (.pack (find-ancestor actor)))
