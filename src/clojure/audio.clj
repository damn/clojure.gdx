(ns clojure.audio)

(defprotocol Audio
  (device [_ sampling-rate mono?])
  (audio-recorder [_ sampling-rate mono?])
  (sound [_ file-handle])
  (music [_ file-handle])
  (switch-output-device [_ identifier])
  (available-output-devices [_]))
