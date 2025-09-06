(ns clojure.gdx.scenes.scene2d.group
  (:require [clojure.gdx.scenes.scene2d.actor :as actor])
  (:import (com.badlogic.gdx.scenes.scene2d Group)))

(defn add! [group actor-or-decl]
  (Group/.addActor group (actor/build? actor-or-decl)))

(defn find-actor [^Group group name]
  (.findActor group name))

(defn clear-children! [^Group group]
  (.clearChildren group))

(defn children [^Group group]
  (.getChildren group))

(defn find-actor-with-id [group id]
  (let [actors (children group)
        ids (keep actor/user-object actors)]
    (assert (or (empty? ids)
                (apply distinct? ids)) ; TODO could check @ add
            (str "Actor ids are not distinct: " (vec ids)))
    (first (filter #(= id (actor/user-object %)) actors))))

(defn set-opts! [group opts]
  (run! (partial add! group) (:actors opts))
  (actor/set-opts! group opts))
