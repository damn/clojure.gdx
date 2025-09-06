(ns clojure.gdx.utils.screen
  (:import (com.badlogic.gdx.graphics Color)
           (com.badlogic.gdx.utils ScreenUtils)))

(defn clear! []
  (ScreenUtils/clear Color/BLACK))
