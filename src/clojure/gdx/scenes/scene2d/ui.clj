(ns clojure.gdx.scenes.scene2d.ui
  (:require [clojure.gdx.scenes.scene2d :as scene2d]
            [clojure.gdx.scenes.scene2d.actor :as actor]
            [clojure.gdx.scenes.scene2d.group :as group]
            [clojure.gdx.scenes.scene2d.ui.widget-group :as widget-group])
  (:import (com.badlogic.gdx.scenes.scene2d.ui HorizontalGroup
                                               Stack
                                               Widget)))

(defn horizontal-group [{:keys [space pad] :as opts}]
  (let [group (scene2d/proxy-group HorizontalGroup [])]
    (when space (.space group (float space)))
    (when pad   (.pad   group (float pad)))
    (group/set-opts! group opts)))

(defmethod actor/build :actor.type/horizontal-group [opts]
  (horizontal-group opts))

(defn stack [opts]
  (doto (scene2d/proxy-group Stack [])
    (widget-group/set-opts! opts)))

(defmethod actor/build :actor.type/stack [opts]
  (stack opts))

(defn widget [opts]
  (proxy [Widget] []
    (draw [_batch _parent-alpha]
      (when-let [f (:draw opts)]
        (scene2d/try-draw this f)))))

(defmethod actor/build :actor.type/widget [opts]
  (widget opts))
