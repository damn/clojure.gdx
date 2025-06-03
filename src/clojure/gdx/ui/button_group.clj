(ns clojure.gdx.ui.button-group
  (:import (com.badlogic.gdx.scenes.scene2d.ui Button
                                               ButtonGroup)))

(defn create [{:keys [max-check-count min-check-count]}]
  (doto (ButtonGroup.)
    (.setMaxCheckCount max-check-count)
    (.setMinCheckCount min-check-count)))

(defn checked [button-group]
  (ButtonGroup/.getChecked button-group))

(defn add! [button-group button]
  (ButtonGroup/.add button-group ^Button button))

(defn remove! [button-group button]
  (ButtonGroup/.remove button-group ^Button button))
