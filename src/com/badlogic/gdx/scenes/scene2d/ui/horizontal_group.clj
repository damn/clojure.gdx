(ns com.badlogic.gdx.scenes.scene2d.ui.horizontal-group
  (:require [clojure.scene2d :as scene2d]
            [clojure.scene2d.group :as group])
  (:import (com.badlogic.gdx.scenes.scene2d.ui HorizontalGroup)))

(defmethod scene2d/build :actor.type/horizontal-group
  [{:keys [space pad] :as opts}]
  (let [group (HorizontalGroup.)]
    (when space (.space group (float space)))
    (when pad   (.pad   group (float pad)))
    (group/set-opts! group opts)))
