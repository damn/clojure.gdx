(ns clojure.gdx.scenes.scene2d
  (:require [clojure.gdx.scenes.scene2d.group :as group]
            [clojure.gdx.scenes.scene2d.stage :as stage])
  (:import (clojure.lang ILookup)
           (clojure.gdx.scenes.scene2d Stage)
           (com.badlogic.gdx.scenes.scene2d Group)))

(defmacro proxy-group
  "For actors inheriting from Group, implements `clojure.lang.ILookup` (`get`)
  via [find-actor-with-id]."
  [class args]
  `(proxy [~class ILookup] ~args
     (valAt
       ([id#]
        (group/find-actor-with-id ~'this id#))
       ([id# not-found#]
        (or (group/find-actor-with-id ~'this id#) not-found#)))))

(defn group []
  (proxy-group Group []))

(defn stage [viewport batch]
  (proxy [Stage ILookup] [viewport batch (atom nil)]
    (valAt [id]
      (group/find-actor-with-id (stage/root this) id))))
