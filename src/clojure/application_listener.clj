(ns clojure.application-listener)

(defprotocol Listener
  (create! [_])
  (dispose! [_])
  (render! [_])
  (resize! [_ width height])
  (pause! [_])
  (resume! [_]))
