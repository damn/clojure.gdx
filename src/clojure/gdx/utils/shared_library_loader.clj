(ns clojure.gdx.utils.shared-library-loader
  (:require [clojure.gdx.utils.os :as os])
  (:import (com.badlogic.gdx.utils SharedLibraryLoader)))

(defn operating-system []
  (os/value->k SharedLibraryLoader/os))
