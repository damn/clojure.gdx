(ns clojure.lwjgl.system.configuration
  (:import (org.lwjgl.system Configuration)))

(defn set-glfw-library-name! [str]
  (.set Configuration/GLFW_LIBRARY_NAME str))
