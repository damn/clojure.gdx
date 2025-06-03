(ns clojure.graphics.pixmap)

(defprotocol Pixmap
  (set-color! [_ color])
  (draw-pixel! [_ x y])
  (texture [_]))
