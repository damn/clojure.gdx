(ns clojure.gdx.scenes.scene2d.ui.horizontal-group
  (:require [clojure.gdx.scenes.scene2d.group :as group])
  (:import (com.badlogic.gdx.scenes.scene2d.ui HorizontalGroup)))

(defn create [space pad]
  (let [group (group/proxy-ILookup HorizontalGroup [])]
    (when space (.space group (float space)))
    (when pad   (.pad   group (float pad)))
    group))
