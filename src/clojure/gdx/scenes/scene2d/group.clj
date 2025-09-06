(ns clojure.gdx.scenes.scene2d.group
  (:require [clojure.gdx.scenes.scene2d.actor :as actor])
  (:import (clojure.lang ILookup)
           (com.badlogic.gdx.scenes.scene2d Group)))

(defn add! [^Group group actor]
  (.addActor group actor))

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

(defmacro proxy-ILookup
  "For actors inheriting from Group, implements `clojure.lang.ILookup` (`get`)
  via [find-actor-with-id]."
  [class args]
  `(proxy [~class ILookup] ~args
     (valAt
       ([id#]
        (find-actor-with-id ~'this id#))
       ([id# not-found#]
        (or (find-actor-with-id ~'this id#) not-found#)))))

(defn create []
  (proxy-ILookup Group []))
