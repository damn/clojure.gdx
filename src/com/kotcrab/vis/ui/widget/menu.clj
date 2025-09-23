(ns com.kotcrab.vis.ui.widget.menu
  (:require [clojure.scene2d :as scene2d]
            [clojure.scene2d.event :as event]
            [clojure.scene2d.group :as group]
            [clojure.scene2d.stage :as stage]
            [clojure.scene2d.ui.label :as label]
            [clojure.scene2d.ui.table :as table]
            [com.badlogic.gdx.scenes.scene2d.utils.listener :as listener])
  (:import (com.badlogic.gdx.scenes.scene2d.ui Cell)
           (com.kotcrab.vis.ui.widget Menu
                                      MenuBar
                                      MenuItem
                                      PopupMenu)))

(defn- set-label-text-actor [label text-fn]
  {:actor/type :actor.type/actor
   :act (fn [_this _delta ctx]
          (label/set-text! label (text-fn ctx)))})

(defn- add-upd-label!
  ([table text-fn icon]
   (let [label (scene2d/build {:actor/type :actor.type/label
                               :label/text ""})
         sub-table (scene2d/build
                    {:actor/type :actor.type/table
                     :rows [[{:actor {:actor/type :actor.type/image
                                      :image/object icon}}
                             label]]})]
     (group/add! table (scene2d/build (set-label-text-actor label text-fn)))
     (.expandX (Cell/.right (table/add! table sub-table)))))
  ([table text-fn]
   (let [label (scene2d/build {:actor/type :actor.type/label
                               :label/text ""})]
     (group/add! table (scene2d/build (set-label-text-actor label text-fn)))
     (.expandX (Cell/.right (table/add! table label))))))

(defn- add-update-labels! [menu-bar update-labels]
  (let [table (MenuBar/.getTable menu-bar)]
    (doseq [{:keys [label update-fn icon]} update-labels]
      (let [update-fn #(str label ": " (update-fn %))]
        (if icon
          (add-upd-label! table update-fn icon)
          (add-upd-label! table update-fn))))))

(defn- add-menu! [menu-bar {:keys [label items]}]
  (let [app-menu (Menu. label)]
    (doseq [{:keys [label on-click]} items]
      (PopupMenu/.addItem app-menu (doto (MenuItem. label)
                                     (.addListener (listener/change
                                                    (fn [event actor]
                                                      (when on-click
                                                        (on-click actor (stage/get-ctx (event/stage event))))))))))
    (MenuBar/.addMenu menu-bar app-menu)))

(defn create [{:keys [menus update-labels]}]
  (let [menu-bar (MenuBar.)]
    (run! #(add-menu! menu-bar %) menus)
    (add-update-labels! menu-bar update-labels)
    (MenuBar/.getTable menu-bar)))
