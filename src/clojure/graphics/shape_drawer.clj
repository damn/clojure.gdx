(ns clojure.graphics.shape-drawer)

(defprotocol ShapeDrawer
  (set-color! [_ color])
  (ellipse! [_ x y radius-x radius-y])
  (filled-ellipse! [_ x y radius-x radius-y])
  (circle! [_ x y radius])
  (filled-circle! [_ x y radius])
  (arc! [_ center-x center-y radius start-angle degree])
  (sector! [_ center-x center-y radius start-angle degree])
  (rectangle! [_ x y w h])
  (filled-rectangle! [_ x y w h])
  (line! [_ sx sy ex ey])
  (with-line-width [_ width draw-fn]))
