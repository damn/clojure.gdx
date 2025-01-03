(ns clojure.gdx.scene2d.group
  (:require [clojure.gdx.scene2d.actor :as actor])
  (:import (com.badlogic.gdx.scenes.scene2d Group)))

(defn children
  "Returns an ordered list of child actors in this group."
  [group]
  (seq (Group/.getChildren group)))

(defn clear-children
  "Removes all actors from this group and unfocuses them."
  [group]
  (Group/.clearChildren group))

(defn add-actor!
  "Adds an actor as a child of this group, removing it from its previous parent. If the actor is already a child of this group, no changes are made."
  [group actor]
  (Group/.addActor group actor))

(defn find-actor [group name]
  (Group/.findActor group name))

(defn find-actor-with-id [group id]
  (let [actors (children group)
        ids (keep actor/user-object actors)]
    (assert (or (empty? ids)
                (apply distinct? ids)) ; TODO could check @ add
            (str "Actor ids are not distinct: " (vec ids)))
    (first (filter #(= id (actor/user-object %)) actors))))
