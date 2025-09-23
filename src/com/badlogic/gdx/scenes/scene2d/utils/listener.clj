(ns com.badlogic.gdx.scenes.scene2d.utils.listener
  (:require clojure.scene2d.event)
  (:import (com.badlogic.gdx.scenes.scene2d Event)
           (com.badlogic.gdx.scenes.scene2d.utils ChangeListener
                                                  ClickListener)))

(extend-type Event
  clojure.scene2d.event/Event
  (stage [event]
    (.getStage event)))

(defn change [f]
  (proxy [ChangeListener] []
    (changed [event actor]
      (f event actor))))

(defn click [f]
  (proxy [ClickListener] []
    (clicked [event x y]
      (f event x y))))
