(ns clojure.java.awt.taskbar
  (:require [clojure.java.io :as io])
  (:import (java.awt Taskbar
                     Toolkit)))

(defn set-icon! [io-resource]
  (.setIconImage (Taskbar/getTaskbar)
                 (.getImage (Toolkit/getDefaultToolkit)
                            (io/resource io-resource))))
