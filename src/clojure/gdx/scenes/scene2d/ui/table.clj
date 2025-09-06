(ns clojure.gdx.scenes.scene2d.ui.table
  (:import (com.badlogic.gdx.scenes.scene2d Actor)
           (com.badlogic.gdx.scenes.scene2d.ui Table)))

(defn add! [table ^Actor actor]
  (Table/.add table actor))

(defn cells [^Table table]
  (.getCells table))

(defn row! [table]
  (Table/.row table))

(defn defaults [table]
  (Table/.defaults table))
