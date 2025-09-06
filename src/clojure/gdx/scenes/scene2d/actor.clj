(ns clojure.gdx.scenes.scene2d.actor
  (:import (com.badlogic.gdx.scenes.scene2d Actor
                                            Touchable)
           (com.badlogic.gdx.math Vector2)))

(defn create [act draw]
  (proxy [Actor] []
    (act [delta]
      (act this delta))
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
  (.setTouchable actor (case touchable
                         :disabled Touchable/disabled)))

(defn remove! [^Actor actor]
  (.remove actor))

(defn parent [^Actor actor]
  (.getParent actor))

(defn hit [^Actor actor [x y]]
  (let [v (.stageToLocalCoordinates actor (Vector2. x y))]
    (.hit actor (.x v) (.y v) true)))

(defn set-opts!
  [^Actor actor
   {:keys [id
           name
           user-object
           visible?
           center-position
           position] :as opts}]
  (when id
    (set-user-object! actor id))
  (when name
    (.setName actor name))
  (when user-object
    (set-user-object! actor user-object))
  (when (contains? opts :visible?)
    (set-visible! actor visible?))
  (when-let [[x y] center-position]
    (.setPosition actor
                  (- x (/ (.getWidth  actor) 2))
                  (- y (/ (.getHeight actor) 2))))
  (when-let [[x y] position]
    (.setPosition actor x y))
  (when-let [touchable (:actor/touchable opts)]
    (set-touchable! actor touchable))
  actor)

(defn toggle-visible! [actor]
  (set-visible! actor (not (visible? actor))))
