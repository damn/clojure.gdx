(ns clojure.gdx.utils.screen
  (:require [clojure.gdx.graphics.color :as color])
  (:import (com.badlogic.gdx.utils ScreenUtils)))

(defn clear! [color]
  (ScreenUtils/clear (color/->obj color)))
