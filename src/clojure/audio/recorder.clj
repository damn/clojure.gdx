(ns clojure.audio.recorder
  (:refer-clojure :exclude [read]))

(defprotocol Recorder
  (read [_ samples offset num-samples]))
