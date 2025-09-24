(ns com.badlogic.gdx.utils.shared-library-loader
  (:require [com.badlogic.gdx.utils.os :as os])
  (:import (com.badlogic.gdx.utils SharedLibraryLoader)))

(defn os []
  (os/value->keyword SharedLibraryLoader/os))
