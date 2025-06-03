(ns clojure.gdx.ui.menu
  (:require [clojure.gdx.ui :as ui])
  (:import (com.badlogic.gdx.scenes.scene2d Actor
                                            Group
                                            Touchable)
           (com.badlogic.gdx.scenes.scene2d.ui Label
                                               Table)
           (com.kotcrab.vis.ui.widget Menu
                                      MenuBar
                                      MenuItem
                                      PopupMenu)))

(defn- set-label-text-actor [label text-fn]
  (ui/actor {:act (fn [_this _delta ctx]
                    (Label/.setText label (str (text-fn ctx))))}))

(defn- add-upd-label!
  ([table text-fn icon]
   (let [icon (ui/image-widget icon {})
         label (ui/label "")
         sub-table (ui/table {:rows [[icon label]]})]
     (ui/add! table (set-label-text-actor label text-fn))
     (.expandX (.right (Table/.add table sub-table)))))
  ([table text-fn]
   (let [label (ui/label "")]
     (ui/add! table (set-label-text-actor label text-fn))
     (.expandX (.right (Table/.add table label))))))

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
                                     (.addListener (ui/change-listener
                                                    (fn [actor ctx]
                                                      (when on-click
                                                        (on-click actor ctx))))))))
    (MenuBar/.addMenu menu-bar app-menu)))

(defn create [{:keys [menus update-labels]}]
  (ui/table {:rows [[{:actor (let [menu-bar (MenuBar.)]
                               (run! #(add-menu! menu-bar %) menus)
                               (add-update-labels! menu-bar update-labels)
                               (MenuBar/.getTable menu-bar))
                      :expand-x? true
                      :fill-x? true
                      :colspan 1}]
                    [{:actor (doto (ui/label "")
                               (Actor/.setTouchable Touchable/disabled))
                      :expand? true
                      :fill-x? true
                      :fill-y? true}]]
             :fill-parent? true}))
