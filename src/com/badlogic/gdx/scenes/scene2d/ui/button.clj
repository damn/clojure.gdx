(ns com.badlogic.gdx.scenes.scene2d.ui.button
  (:require [clojure.scene2d.actor :as actor])
  (:import (com.badlogic.gdx.scenes.scene2d.ui Button)))

(defn- button-class? [actor]
  (some #(= Button %) (supers (class actor))))

(defn is?
  "Returns true if the actor or its parent is a button."
  [actor]
  (or (button-class? actor)
      (and (actor/parent actor)
           (button-class? (actor/parent actor)))))
