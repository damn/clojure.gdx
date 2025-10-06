(ns clojure.gdx.graphics)

(defprotocol Graphics
  (delta-time [_])
  (frames-per-second [_])
  (set-cursor! [_ cursor])
  (cursor [_ pixmap hotspot-x hotspot-y])
  (clear! [_ color]))
