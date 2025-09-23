(ns com.badlogic.gdx.scenes.scene2d.actor
  (:require [clojure.scene2d :as scene2d]
            [clojure.scene2d.actor :as actor]
            [clojure.scene2d.ctx :as ctx]
            [clojure.scene2d.stage :as stage]
            [com.badlogic.gdx.math.vector2 :as vector2]
            [com.badlogic.gdx.scenes.scene2d.touchable :as touchable])
  (:import (com.badlogic.gdx.scenes.scene2d Actor)))

(defn- get-ctx [actor]
  (when-let [stage (actor/get-stage actor)]
    (stage/get-ctx stage)))

(def ^:private opts-fn-map
  {:actor/name (fn [a v] (actor/set-name! a v))
   :actor/user-object (fn [a v] (actor/set-user-object! a v))
   :actor/visible?  (fn [a v] (actor/set-visible! a v))
   :actor/touchable (fn [a v] (actor/set-touchable! a v))
   :actor/listener (fn [a v] (actor/add-listener! a v))
   :actor/position (fn [actor [x y]]
                     (actor/set-position! actor x y))
   :actor/center-position (fn [actor [x y]]
                            (actor/set-position! actor
                                                 (- x (/ (actor/get-width  actor) 2))
                                                 (- y (/ (actor/get-height actor) 2))))})

(extend-type Actor
  clojure.scene2d.actor/Actor
  (get-stage [actor]
    (.getStage actor))

  (get-x [actor]
    (.getX actor))

  (get-y [actor]
    (.getY actor))

  (get-name [actor]
    (.getName actor))

  (user-object [actor]
    (.getUserObject actor))

  (set-user-object! [actor object]
    (.setUserObject actor object))

  (visible? [actor]
    (.isVisible actor))

  (set-visible! [actor visible?]
    (.setVisible actor visible?))

  (set-touchable! [actor touchable]
    (.setTouchable actor (touchable/k->value touchable)))

  (remove! [actor]
    (.remove actor))

  (parent [actor]
    (.getParent actor))

  (stage->local-coordinates [actor position]
    (-> actor
        (.stageToLocalCoordinates (vector2/->java position))
        vector2/->clj))

  (hit [actor [x y]]
    (.hit actor x y true))

  (set-name! [actor name]
    (.setName actor name))

  (set-position! [actor x y]
    (.setPosition actor x y))

  (get-width [actor]
    (.getWidth actor))

  (get-height [actor]
    (.getHeight actor))

  (add-listener! [actor listener]
    (.addListener actor listener))

  (set-opts! [actor opts]
    (doseq [[k v] opts
            :let [f (get opts-fn-map k)]
            :when f]
      (f actor v))
    actor)

  (act [actor delta f]
    (when-let [ctx (get-ctx actor)]
      (f actor delta ctx)))

  (draw [actor f]
    (when-let [ctx (get-ctx actor)]
      (ctx/draw! ctx (f actor ctx)))))

(defn- create*
  [{:keys [actor/act
           actor/draw]
    :as opts}]
  (doto (proxy [Actor] []
          (act [delta] ; TODO call proxy super required ?-> fixes tooltips in pure scene2d? maybe also other ones..
            (act this delta))
          (draw [batch parent-alpha]
            (draw this batch parent-alpha)))
    (actor/set-opts! opts)))

(defmethod scene2d/build :actor.type/actor
  [{:keys [act draw]
    :as opts}]
  (create*
   (assoc opts
          :actor/act (fn [actor delta]
                       (when act
                         (actor/act actor delta act)))
          :actor/draw (fn [actor _batch _parent-alpha]
                        (when draw
                          (actor/draw actor draw))))))
