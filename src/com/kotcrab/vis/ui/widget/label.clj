(ns com.kotcrab.vis.ui.widget.label
  (:require [clojure.scene2d.ui.widget :as widget])
  (:import (com.kotcrab.vis.ui.widget VisLabel)))

(defn create [{:keys [label/text] :as opts}]
  (doto (VisLabel. ^CharSequence text)
    (widget/set-opts! opts)))
