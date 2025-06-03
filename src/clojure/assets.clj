(ns clojure.assets)

(defprotocol Assets
  (all-of-type [_ asset-type]
               "Type is `:sound` or `:texture`. Returns all asset-paths of the given type."))
