(ns clojure.gdx.scenes.scene2d.actor
  (:import (com.badlogic.gdx.scenes.scene2d Actor)))

(defn create [act draw]
  (proxy [Actor] []
    (act [delta]
      (act this delta)
      (proxy-super act delta))
    (draw [batch parent-alpha]
      (draw this batch parent-alpha))))

(defn get-stage [^Actor actor]
  (.getStage actor))

(defn get-x [^Actor actor]
  (.getX actor))

(defn get-y [^Actor actor]
  (.getY actor))

(defn get-name [^Actor actor]
  (.getName actor))

(defn user-object [^Actor actor]
  (.getUserObject actor))

(defn set-user-object! [^Actor actor object]
  (.setUserObject actor object))

(defn visible? [^Actor actor]
  (.isVisible actor))

(defn set-visible! [^Actor actor visible?]
  (.setVisible actor visible?))

(defn set-touchable! [^Actor actor touchable]
  (.setTouchable actor touchable))

(defn remove! [^Actor actor]
  (.remove actor))

(defn parent [^Actor actor]
  (.getParent actor))

(defn stage->local-coordinates [^Actor actor vector2]
  (.stageToLocalCoordinates actor vector2))

(defn hit [^Actor actor [x y]]
  (.hit actor x y true))

(defn set-name! [^Actor actor name]
  (.setName actor name))

(defn set-position! [^Actor actor x y]
  (.setPosition actor x y))

(defn get-width [^Actor actor]
  (.getWidth actor))

(defn get-height [^Actor actor]
  (.getHeight actor))

(defn add-listener! [^Actor actor listener]
  (.addListener actor listener))
