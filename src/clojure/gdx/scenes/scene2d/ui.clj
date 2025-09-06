(ns clojure.gdx.scenes.scene2d.ui
  (:require [clojure.gdx.scenes.scene2d :as scene2d]
            [clojure.gdx.scenes.scene2d.actor :as actor]
            [clojure.gdx.scenes.scene2d.group :as group])
  (:import (com.badlogic.gdx.scenes.scene2d.ui HorizontalGroup
                                               Stack)))

(defn horizontal-group [{:keys [space pad] :as opts}]
  (let [group (scene2d/proxy-group HorizontalGroup [])]
    (when space (.space group (float space)))
    (when pad   (.pad   group (float pad)))
    (group/set-opts! group opts)))

(defn stack []
  (scene2d/proxy-group Stack []))

(defmethod actor/build :actor.type/horizontal-group [opts]
  (horizontal-group opts))
