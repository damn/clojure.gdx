(ns clojure.gdx.application
  (:require [clojure.application :as application]
            [clojure.gdx.application.extends]
            [clojure.java.io :as io])
  (:import (com.badlogic.gdx ApplicationListener
                             Gdx)
           (com.badlogic.gdx.backends.lwjgl3 Lwjgl3Application
                                             Lwjgl3ApplicationConfiguration
                                             Lwjgl3WindowConfiguration)
           (com.badlogic.gdx.utils SharedLibraryLoader
                                   Os)
           (java.awt Taskbar
                     Toolkit)
           (org.lwjgl.system Configuration)))

(let [value->keyword {Os/Android :android
                      Os/IOS     :ios
                      Os/Linux   :linux
                      Os/MacOsX  :mac
                      Os/Windows :windows}]
  (defn- operating-system []
    (value->keyword SharedLibraryLoader/os)))

(defn- set-taskbar-icon! [path]
  (.setIconImage (Taskbar/getTaskbar)
                 (.getImage (Toolkit/getDefaultToolkit)
                            (io/resource path))))

(defn- set-glfw-async! []
  (.set Configuration/GLFW_LIBRARY_NAME "glfw_async"))

(defn- ->ApplicationListener
  [listener]
  (reify ApplicationListener
    (create [_]
      (application/create listener
                          {:ctx/app      Gdx/app
                           :ctx/audio    Gdx/audio
                           :ctx/files    Gdx/files
                           :ctx/graphics Gdx/graphics
                           :ctx/input    Gdx/input}))
    (dispose [_]
      (application/dispose listener))
    (render [_]
      (application/render listener))
    (resize [_ width height]
      (application/resize listener width height))
    (pause [_]
      (application/pause listener))
    (resume [_]
      (application/resume listener))))

(defn- set-window-config-option! [^Lwjgl3WindowConfiguration object k v]
  (case k
    :windowed-mode (.setWindowedMode object
                                     (int (:width v))
                                     (int (:height v)))
    :title (.setTitle object (str v))))

(defn- ->Lwjgl3ApplicationConfiguration [config]
  (let [obj (Lwjgl3ApplicationConfiguration.)]
    (doseq [[k v] config]
      (case k
        :foreground-fps (.setForegroundFPS obj (int v))
        (set-window-config-option! obj k v)))
    obj))

(defn start!
  [{:keys [listener config]}]
  (when (= (operating-system) :mac)
    (set-glfw-async!)
    #_(set-taskbar-icon! "icon.png"))
  (Lwjgl3Application. (->ApplicationListener listener)
                      (->Lwjgl3ApplicationConfiguration config)))
