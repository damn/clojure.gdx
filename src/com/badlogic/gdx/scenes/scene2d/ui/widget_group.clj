(ns com.badlogic.gdx.scenes.scene2d.ui.widget-group
  (:require [clojure.scene2d.group :as group]
            [clojure.scene2d.ui.widget-group :as widget-group])
  (:import (com.badlogic.gdx.scenes.scene2d.ui WidgetGroup)))

(extend-type WidgetGroup
  widget-group/WidgetGroup
  (pack! [widget-group]
    (.pack widget-group))

  (set-opts! [widget-group {:keys [fill-parent? pack?]
                            :as opts}]
    (.setFillParent widget-group (boolean fill-parent?))
    (when pack? (.pack widget-group))
    (group/set-opts! widget-group opts)))
