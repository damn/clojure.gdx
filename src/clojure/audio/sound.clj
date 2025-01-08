(ns clojure.audio.sound
  (:refer-clojure :exclude [loop]))

(defprotocol Sound
  (loop [_] [_ volume] [_ volume pitch pan])
  (play [_] [_ volume] [_ volume pitch pan])
  (resume [_] [_ id])
  (set-looping [_ id looping?])
  (set-pan [_ id pan volume])
  (set-pitch [_ id pitch])
  (set-volume [_ id volume])
  (stop [_]))
