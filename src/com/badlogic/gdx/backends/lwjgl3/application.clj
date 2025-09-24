(ns com.badlogic.gdx.backends.lwjgl3.application
  (:require [com.badlogic.gdx.application.listener :as listener]
            [com.badlogic.gdx.backends.lwjgl3.application.config :as config])
  (:import (com.badlogic.gdx.backends.lwjgl3 Lwjgl3Application)))

(defn start! [{:keys [listener config]}]
  (Lwjgl3Application. (listener/->java listener)
                      (config/->java config)))
