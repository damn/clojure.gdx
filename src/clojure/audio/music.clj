(ns clojure.audio.music)

(defprotocol Music
  (position [_])
  (volume [_])
  (looping? [_])
  (playing? [_])
  (pause [_])
  (play [_])
  (set-looping [_ looping?])
  (set-on-completion-listener [_ listener])
  (set-pan [_ pan volume])
  (set-position [_ position])
  (set-volume [_ volume])
  (stop [_]))
