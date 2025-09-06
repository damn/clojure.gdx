(ns clojure.gdx.scenes.scene2d.stage
  (:import (com.badlogic.gdx.scenes.scene2d Stage)))

(defn act! [stage]
  (Stage/.act stage))

(defn draw! [stage]
  (Stage/.draw stage))

(defn add! [^Stage stage actor]
  (.addActor stage actor))

(defn clear! [^Stage stage]
  (.clear stage))

(defn root [^Stage stage]
  (.getRoot stage))

(defn hit [stage [x y]]
  (Stage/.hit stage x y true))
