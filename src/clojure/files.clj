(ns clojure.files)

(defprotocol Files
  (internal [_ path] "Convenience method that returns a {@link FileType#Internal} file handle."))
