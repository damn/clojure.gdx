(ns clojure.utils.disposable)

(defprotocol Disposable
  (dispose! [_]
            "Releases all resources of the object."))
