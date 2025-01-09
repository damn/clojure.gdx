(ns clojure.files)

(defprotocol Files
  (internal [_ path])
  (classpath [_ path])
  (external [_ path])
  (absolute [_ path])
  (local [_ path])
  (external-storage-path [_])
  (external-storage-available? [_])
  (local-storage-path [_])
  (local-storage-available? [_])
  (file-handle [_ path file-type]))
