(ns clojure.gdx.vis-ui
  (:require [com.badlogic.gdx.utils.align :as align]
            com.kotcrab.vis.ui.widget.menu
            com.kotcrab.vis.ui.widget.select-box
            com.kotcrab.vis.ui.widget.label
            com.kotcrab.vis.ui.widget.text-field
            com.kotcrab.vis.ui.widget.check-box
            com.kotcrab.vis.ui.widget.table
            com.kotcrab.vis.ui.widget.image-button
            com.kotcrab.vis.ui.widget.text-button
            com.kotcrab.vis.ui.widget.window
            com.kotcrab.vis.ui.widget.image
            [clojure.disposable :as disposable]
            [clojure.scene2d :as scene2d]
            [clojure.scene2d.actor :as actor]
            [clojure.scene2d.stage :as stage])
  (:import (clojure.lang MultiFn)
           (com.badlogic.gdx.scenes.scene2d Actor)
           (com.kotcrab.vis.ui VisUI
                               VisUI$SkinScale)
           (com.kotcrab.vis.ui.widget Separator
                                      Tooltip
                                      VisLabel
                                      VisScrollPane)))

(doseq [[k method-fn] {:actor.type/menu-bar     com.kotcrab.vis.ui.widget.menu/create
                       :actor.type/select-box   com.kotcrab.vis.ui.widget.select-box/create
                       :actor.type/label        com.kotcrab.vis.ui.widget.label/create
                       :actor.type/text-field   com.kotcrab.vis.ui.widget.text-field/create
                       :actor.type/check-box    com.kotcrab.vis.ui.widget.check-box/create
                       :actor.type/table        com.kotcrab.vis.ui.widget.table/create
                       :actor.type/image-button com.kotcrab.vis.ui.widget.image-button/create
                       :actor.type/text-button  com.kotcrab.vis.ui.widget.text-button/create
                       :actor.type/window       com.kotcrab.vis.ui.widget.window/create
                       :actor.type/image        com.kotcrab.vis.ui.widget.image/create}]
  (assert (keyword? k))
  (MultiFn/.addMethod clojure.scene2d/build k method-fn))

(defmethod scene2d/build :actor.type/separator-horizontal [_]
  (Separator. "default"))

(defmethod scene2d/build :actor.type/separator-vertical [_]
  (Separator. "vertical"))

(defmethod scene2d/build :actor.type/scroll-pane
  [{:keys [scroll-pane/actor
           actor/name]}]
  (doto (VisScrollPane. actor)
    (.setFlickScroll false)
    (.setFadeScrollBars false)
    (actor/set-name! name)))

(defn load! [{:keys [skin-scale]}]
  ; app crashes during startup before VisUI/dispose and we do clojure.tools.namespace.refresh-> gui elements not showing.
  ; => actually there is a deeper issue at play
  ; we need to dispose ALL resources which were loaded already ...
  (when (VisUI/isLoaded)
    (VisUI/dispose))
  (VisUI/load (case skin-scale
                :x1 VisUI$SkinScale/X1
                :x2 VisUI$SkinScale/X2))
  (-> (VisUI/getSkin)
      (.getFont "default-font")
      .getData
      .markupEnabled
      (set! true))
  ;(set! Tooltip/DEFAULT_FADE_TIME (float 0.3))
  ;Controls whether to fade out tooltip when mouse was moved. (default false)
  ;(set! Tooltip/MOUSE_MOVED_FADEOUT true)
  (set! Tooltip/DEFAULT_APPEAR_DELAY_TIME (float 0))
  (reify disposable/Disposable
    (dispose! [_]
      (VisUI/dispose))))

(let [update-fn (fn [tooltip-text]
                  (fn [tooltip]
                    (when-not (string? tooltip-text)
                      (let [actor (Tooltip/.getTarget tooltip)
                            ; acturs might be initialized without a stage yet so we do when-let
                            ; FIXME double when-let
                            ctx (when-let [stage (actor/get-stage actor)]
                                  (stage/get-ctx stage))]
                        (when ctx ; ctx is only set later for update!/draw! ... not at starting of initialisation
                          (Tooltip/.setText tooltip (str (tooltip-text ctx))))))))]
  (extend-type Actor
    actor/Tooltip
    (add-tooltip! [actor tooltip-text]
      (let [text? (string? tooltip-text)
            label (doto (VisLabel. ^CharSequence (str (if text? tooltip-text "")))
                    (.setAlignment (align/k->value :center)))
            update-text! (update-fn tooltip-text)]
        (doto (proxy [Tooltip] []
                ; hooking into getWidth because at
                ; https://github.com/kotcrab/vis-blob/master/ui/src/main/java/com/kotcrab/vis/ui/widget/Tooltip.java#L271
                ; when tooltip position gets calculated we setText (which calls pack) before that
                ; so that the size is correct for the newly calculated text.
                (getWidth []
                  (update-text! this)
                  (let [^Tooltip this this]
                    (proxy-super getWidth))))
          (.setTarget  actor)
          (.setContent label)))
      actor)

    (remove-tooltip! [actor]
      (Tooltip/removeTooltip actor))))
