(ns com.badlogic.gdx.scenes.scene2d.group
  (:require [clojure.scene2d :as scene2d]
            [clojure.scene2d.actor :as actor]
            [clojure.scene2d.group :as group])
  (:import (com.badlogic.gdx.scenes.scene2d Actor
                                            Group)))

(extend-type Group
  group/Group
  (add! [group actor]
    (.addActor group actor))

  (find-actor [group name]
    (.findActor group name))

  (clear-children! [group]
    (.clearChildren group))

  (children [group]
    (.getChildren group))

  (set-opts! [group opts]
    (run! (fn [actor-or-decl]
            (group/add! group (if (instance? Actor actor-or-decl)
                                actor-or-decl
                                (scene2d/build actor-or-decl))))
          (:group/actors opts))
    (actor/set-opts! group opts)))

(defmethod scene2d/build :actor.type/group [opts]
  (doto (Group.)
    (group/set-opts! opts)))
