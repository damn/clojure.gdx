(ns clojure.graphics)

(defprotocol Graphics
  (delta-time [_])
  (frames-per-second [_])
  (new-cursor [_ pixmap hotspot-x hotspot-y])
  (set-cursor! [_ cursor]))
