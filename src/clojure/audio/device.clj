(ns clojure.audio.device)

(defprotocol Device
  (latency [_])
  (mono? [_])
  (pause [_])
  (resume [_])
  (set-volume [_ volume])
  (write-samples [_ samples offset num-samples]))
