(ns com.badlogic.gdx.scenes.scene2d.ui.stack
  (:require [clojure.scene2d :as scene2d]
            [clojure.scene2d.ui.widget-group :as widget-group])
  (:import (com.badlogic.gdx.scenes.scene2d.ui Stack)))

(defmethod scene2d/build :actor.type/stack [opts]
  (doto (Stack.)
    (widget-group/set-opts! opts)))
