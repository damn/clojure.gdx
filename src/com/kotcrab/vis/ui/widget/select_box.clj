(ns com.kotcrab.vis.ui.widget.select-box
  (:import (clojure.lang ILookup)
           (com.kotcrab.vis.ui.widget VisSelectBox)))

(defn create [{:keys [items selected]}]
  (doto (proxy [VisSelectBox ILookup] []
          (valAt [k]
            (case k
              :select-box/selected (VisSelectBox/.getSelected this))))
    (.setItems ^"[Lcom.badlogic.gdx.scenes.scene2d.Actor;" (into-array items))
    (.setSelected selected)))
