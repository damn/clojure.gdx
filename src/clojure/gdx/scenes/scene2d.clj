(ns clojure.gdx.scenes.scene2d
  (:require [clojure.gdx.scenes.scene2d.actor :as actor]
            [clojure.gdx.scenes.scene2d.group :as group]
            [clojure.gdx.scenes.scene2d.stage :as stage])
  (:import (clojure.lang ILookup)
           (clojure.gdx.scenes.scene2d Stage)
           (com.badlogic.gdx.scenes.scene2d Actor
                                            Group)))

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

(defn group [opts]
  (doto (proxy-group Group [])
    (group/set-opts! opts)))

(defn stage [viewport batch]
  (proxy [Stage ILookup] [viewport batch (atom nil)]
    (valAt [id]
      (group/find-actor-with-id (stage/root this) id))))

; actor was removed -> stage nil -> context nil -> error on text-buttons/etc.
(defn try-act [actor delta f]
  (when-let [ctx (when-let [stage (actor/get-stage actor)]
                   (stage/get-ctx stage))]
    (f actor delta ctx)))

(defprotocol Context
  (handle-draws! [_ draws]))

(defn try-draw [actor f]
  (when-let [ctx (when-let [stage (actor/get-stage actor)]
                   (stage/get-ctx stage))]
    (handle-draws! ctx (f actor ctx))))

; TODO have to call proxy super (fixes tooltips in pure scene2d?)
(defn actor [opts]
  (doto (proxy [Actor] []
          (act [delta]
            (when-let [f (:act opts)]
              (try-act this delta f)))
          (draw [_batch _parent-alpha]
            (when-let [f (:draw opts)]
              (try-draw this f))))
    (actor/set-opts! opts)))

(defmethod actor/build :actor.type/actor [opts] (actor opts))
(defmethod actor/build :actor.type/group [opts] (group opts))
