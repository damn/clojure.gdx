(ns clojure.gdx.scenes.scene2d.stage
  (:import (clojure.gdx.scenes.scene2d Stage)))

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

(defn get-ctx [^Stage stage]
  @(.ctx ^Stage stage))

(defn set-ctx! [^Stage stage ctx]
  (reset! (.ctx ^Stage stage) ctx))
