(ns com.badlogic.gdx.backends.lwjgl3.application.config
  (:require [clojure.java.awt :as awt]
            [clojure.lwjgl.system.configuration :as lwjgl-system-configuration]
            [com.badlogic.gdx.backends.lwjgl3.window.config :as window.config]
            [com.badlogic.gdx.utils.shared-library-loader :as shared-library-loader])
  (:import (com.badlogic.gdx.backends.lwjgl3 Lwjgl3ApplicationConfiguration)))

(defn- set-mac-os-config!
  [{:keys [glfw-async?
           taskbar-icon]}]
  (when glfw-async?
    (lwjgl-system-configuration/set-glfw-library-name! "glfw_async"))
  (when-let [path taskbar-icon]
    (awt/set-taskbar-icon! path)))

(defn ->java [config]
  (let [obj (Lwjgl3ApplicationConfiguration.)]
    (doseq [[k v] config]
      (case k
        :foreground-fps (.setForegroundFPS obj (int v))

        :mac (when (= (shared-library-loader/os) :mac) (set-mac-os-config! v))

        (window.config/set-option! obj k v)
        ))
    obj))
