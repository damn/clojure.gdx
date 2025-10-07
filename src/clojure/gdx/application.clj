(ns clojure.gdx.application
  (:require [clojure.gdx :as gdx])
  (:import (com.badlogic.gdx ApplicationListener)))

(defn listener
  [{:keys [create dispose render resize pause resume]}]
  (reify ApplicationListener
    (create [_]
      (create (gdx/context)))
    (dispose [_]
      (dispose))
    (render [_]
      (render))
    (resize [_ width height]
      (resize width height))
    (pause [_]
      (pause))
    (resume [_]
      (resume))))
