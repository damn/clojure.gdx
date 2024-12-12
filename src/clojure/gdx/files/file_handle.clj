(ns clojure.gdx.files.file-handle
  (:import (com.badlogic.gdx.files FileHandle)))

(def list       FileHandle/.list)
(def directory? FileHandle/.isDirectory)
(def extension  FileHandle/.extension)
(def path       FileHandle/.path)
