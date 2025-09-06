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

(defn group []
  (proxy-group Group []))

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

(defmulti create :actor/type)

(defmethod create :actor.type/actor [opts] (actor opts))

(defn construct? ^Actor [actor-declaration]
  (try
   (cond
    (instance? Actor actor-declaration) actor-declaration
    (map? actor-declaration) (create actor-declaration)
    (nil? actor-declaration) nil
    :else (throw (ex-info "Cannot find constructor"
                          {:instance-actor? (instance? Actor actor-declaration)
                           :map? (map? actor-declaration)})))
   (catch Throwable t
     (throw (ex-info "Cannot create-actor"
                     {:actor-declaration actor-declaration}
                     t)))))
