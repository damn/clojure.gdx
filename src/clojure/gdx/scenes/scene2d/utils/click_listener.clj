(ns clojure.gdx.scenes.scene2d.utils.click-listener
  (:import (com.badlogic.gdx.scenes.scene2d.utils ClickListener)))

(defn create [f]
  (proxy [ClickListener] []
    (clicked [event x y]
      (f event x y))))
