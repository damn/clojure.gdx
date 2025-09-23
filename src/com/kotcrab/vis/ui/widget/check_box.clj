(ns com.kotcrab.vis.ui.widget.check-box
  (:import (clojure.lang ILookup)
           (com.badlogic.gdx.scenes.scene2d.ui Button)
           (com.badlogic.gdx.scenes.scene2d.utils ChangeListener)
           (com.kotcrab.vis.ui.widget VisCheckBox)))

(defn create
  [{:keys [text on-clicked checked?]}]
  (let [^Button button (proxy [VisCheckBox ILookup] [(str text)]
                         (valAt [k]
                           (case k
                             :check-box/checked? (VisCheckBox/.isChecked this))))]
    (.setChecked button checked?)
    (.addListener button
                  (proxy [ChangeListener] []
                    (changed [event ^Button actor]
                      (on-clicked (.isChecked actor)))))
    button))
