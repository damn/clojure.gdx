(ns clojure.gdx.stage
  (:require com.badlogic.gdx.scenes.scene2d.actor
            com.badlogic.gdx.scenes.scene2d.group
            com.badlogic.gdx.scenes.scene2d.ui.horizontal-group
            com.badlogic.gdx.scenes.scene2d.ui.label
            com.badlogic.gdx.scenes.scene2d.ui.stack
            com.badlogic.gdx.scenes.scene2d.ui.table
            com.badlogic.gdx.scenes.scene2d.ui.widget
            com.badlogic.gdx.scenes.scene2d.ui.widget-group
            com.badlogic.gdx.scenes.scene2d.ui.window
            clojure.scene2d.stage)
  (:import (com.badlogic.gdx.scenes.scene2d StageWithCtx)))

(defn create [viewport batch state]
  (StageWithCtx. viewport batch state))

(extend-type StageWithCtx
  clojure.scene2d.stage/Stage
  (get-ctx [this]
    @(.ctx this))

  (act! [this]
    (.act this))

  (draw! [this]
    (.draw this))

  (add! [this actor]
    (.addActor this actor))

  (clear! [this]
    (.clear this))

  (root [this]
    (.getRoot this))

  (hit [this [x y]]
    (.hit this x y true))

  (viewport [this]
    (.getViewport this)))
