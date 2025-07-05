(ns clojure.gdx.files.file-handle
  (:refer-clojure :exclude [list])
  (:import (com.badlogic.gdx.files FileHandle)))

(defn list [fh]
  (FileHandle/.list fh))

(def directory? FileHandle/.isDirectory)
(def path       FileHandle/.path)
(def extension  FileHandle/.extension)
