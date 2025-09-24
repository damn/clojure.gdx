(ns com.badlogic.gdx.application.listener
  (:require [clojure.application :as application]
            [com.badlogic.gdx :as gdx])
  (:import (com.badlogic.gdx ApplicationListener)))

(defn ->java
  [listener]
  (reify ApplicationListener
    (create [_]
      (application/create listener (gdx/get-state)))
    (dispose [_]
      (application/dispose listener))
    (render [_]
      (application/render listener))
    (resize [_ width height]
      (application/resize listener width height))
    (pause [_]
      (application/pause listener))
    (resume [_]
      (application/resume listener))))
