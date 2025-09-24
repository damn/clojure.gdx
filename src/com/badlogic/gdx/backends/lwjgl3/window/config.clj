(ns com.badlogic.gdx.backends.lwjgl3.window.config
  (:import (com.badlogic.gdx.backends.lwjgl3 Lwjgl3WindowConfiguration)))

(defn set-option! [^Lwjgl3WindowConfiguration object k v]
  (case k
    :windowed-mode (.setWindowedMode object (int (:width v)) (int (:height v)))

    :title (.setTitle object (str v))))
