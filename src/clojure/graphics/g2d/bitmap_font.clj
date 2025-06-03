(ns clojure.graphics.g2d.bitmap-font)

(defprotocol BitmapFont
  (draw! [_ batch {:keys [scale x y text h-align up?]}]))
