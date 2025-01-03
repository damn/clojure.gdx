(ns clojure.gdx.scene2d.ui.table
  (:require [clojure.gdx.scene2d.ui.cell :as cell])
  (:import (com.badlogic.gdx.scenes.scene2d Actor)
           (com.badlogic.gdx.scenes.scene2d.ui Table)))

(defn add-rows!
  "rows is a seq of seqs of columns.
  Elements are actors or nil (for just adding empty cells ) or a map of
  {:actor :expand? :bottom?  :colspan int :pad :pad-bottom}. Only :actor is required."
  [^Table table rows]
  (doseq [row rows]
    (doseq [props-or-actor row]
      (cond
       (map? props-or-actor) (-> (.add table ^Actor (:actor props-or-actor))
                                 (cell/set-opts (dissoc props-or-actor :actor)))
       :else (.add table ^Actor props-or-actor)))
    (.row table))
  table)

(defn set-opts [^Table table {:keys [rows cell-defaults]}]
  (cell/set-opts (.defaults table) cell-defaults)
  (add-rows! table rows))
