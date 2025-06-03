(ns clojure.gdx.shape-drawer
  (:require [clojure.gdx :as gdx]
            [clojure.graphics.shape-drawer]
            [clojure.gdx.math-utils :as math-utils])
  (:import (space.earlygrey.shapedrawer ShapeDrawer)))

(defn create [batch texture-region]
  (let [this (ShapeDrawer. (:sprite-batch/java-object batch)
                           (:texture-region/java-object texture-region))]
    (reify clojure.graphics.shape-drawer/ShapeDrawer
      (set-color! [_ color]
        (.setColor this (gdx/->color color)))

      (ellipse! [_ x y radius-x radius-y]
        (.ellipse this
                  (float x)
                  (float y)
                  (float radius-x)
                  (float radius-y)))

      (filled-ellipse! [_ x y radius-x radius-y]
        (.filledEllipse this
                        (float x)
                        (float y)
                        (float radius-x)
                        (float radius-y)))

      (circle! [_ x y radius]
        (.circle this
                 (float x)
                 (float y)
                 (float radius)))

      (filled-circle! [_ x y radius]
        (.filledCircle this
                       (float x)
                       (float y)
                       (float radius)))

      (arc! [_ center-x center-y radius start-angle degree]
        (.arc this
              (float center-x)
              (float center-y)
              (float radius)
              (float (math-utils/degree->radians start-angle))
              (float (math-utils/degree->radians degree))))

      (sector! [_ center-x center-y radius start-angle degree]
        (.sector this
                 (float center-x)
                 (float center-y)
                 (float radius)
                 (float (math-utils/degree->radians start-angle))
                 (float (math-utils/degree->radians degree))))

      (rectangle! [_ x y w h]
        (.rectangle this
                    (float x)
                    (float y)
                    (float w)
                    (float h)))

      (filled-rectangle! [_ x y w h]
        (.filledRectangle this
                          (float x)
                          (float y)
                          (float w)
                          (float h)))

      (line! [_ sx sy ex ey]
        (.line this
               (float sx)
               (float sy)
               (float ex)
               (float ey)))

      (with-line-width [_ width draw-fn]
        (let [old-line-width (.getDefaultLineWidth this)]
          (.setDefaultLineWidth this (float (* width old-line-width)))
          (draw-fn)
          (.setDefaultLineWidth this (float old-line-width)))))))
