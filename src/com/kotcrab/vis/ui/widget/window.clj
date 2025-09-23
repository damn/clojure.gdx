(ns com.kotcrab.vis.ui.widget.window
  (:require [clojure.scene2d.actor :as actor]
            [clojure.scene2d.ui.window]
            [clojure.scene2d.ui.table :as table])
  (:import (com.badlogic.gdx.scenes.scene2d.ui Label
                                               Window)
           (com.kotcrab.vis.ui.widget VisWindow)))

(defn create
  [{:keys [title
           modal?
           close-button?
           center?
           close-on-escape?]
    :as opts}]
  (let [window (doto (VisWindow. ^String title true) ; true = showWindowBorder
                 (.setModal (boolean modal?)))]
    (when close-button?    (.addCloseButton window))
    (when center?          (.centerWindow   window))
    (when close-on-escape? (.closeOnEscape  window))
    (table/set-opts! window opts)))

(extend-type com.badlogic.gdx.scenes.scene2d.Actor
  clojure.scene2d.ui.window/TitleBar
  ; TODO buggy FIXME
  (title-bar? [actor]
    (when (instance? Label actor)
      (when-let [p (actor/parent actor)]
        (when-let [p (actor/parent p)]
          (and (instance? VisWindow actor)
               (= (.getTitleLabel ^Window p) actor)))))))
