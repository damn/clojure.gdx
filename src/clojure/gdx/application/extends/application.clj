(ns clojure.gdx.application.extends.application
  (:require clojure.application)
  (:import (com.badlogic.gdx Application)))

(extend-type Application
  clojure.application/Application
  (post-runnable! [this f]
    (.postRunnable this f)))
