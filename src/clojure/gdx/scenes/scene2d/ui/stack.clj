(ns clojure.gdx.scenes.scene2d.ui.stack
  (:require [clojure.gdx.scenes.scene2d.group :as group])
  (:import (com.badlogic.gdx.scenes.scene2d.ui Stack)))

(defn create []
  (group/proxy-ILookup Stack []))
