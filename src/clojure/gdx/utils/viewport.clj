(ns clojure.gdx.utils.viewport
  (:refer-clojure :exclude [update])
  (:import (clojure.lang ILookup)
           (com.badlogic.gdx.math Vector2)
           (com.badlogic.gdx.utils.viewport FitViewport Viewport)))

(defn- k->field [^Viewport vp k]
  (case k
    :camera            (.getCamera          vp)
    :left-gutter-width (.getLeftGutterWidth vp)
    :right-gutter-x    (.getRightGutterX    vp)
    :top-gutter-height (.getTopGutterHeight vp)
    :top-gutter-y      (.getTopGutterY      vp)))

(defn fit [width height camera]
  (proxy [FitViewport ILookup] [width height camera]
    (valAt
      ([key]
       (k->field this key))
      ([key _not-found]
       (k->field this key)))))

(defn update [viewport w h & {:keys [center-camera?]}]
  (Viewport/.update viewport w h (boolean center-camera?)))

(defn unproject [viewport x y]
  (let [v2 (Viewport/.unproject viewport (Vector2. x y))]
    [(.x v2) (.y v2)]))

