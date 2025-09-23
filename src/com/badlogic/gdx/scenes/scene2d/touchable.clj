(ns com.badlogic.gdx.scenes.scene2d.touchable
  (:import (com.badlogic.gdx.scenes.scene2d Touchable)))

(defn k->value [k]
  (case k
    :enabled       Touchable/enabled
    :disabled      Touchable/disabled
    :children-only Touchable/childrenOnly))
