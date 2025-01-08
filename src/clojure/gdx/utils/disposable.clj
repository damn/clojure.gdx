(ns clojure.gdx.utils.disposable)

(defn dispose
  "Releases all resources of this object."
  [disposable]
  (Disposable/.dispose disposable))
