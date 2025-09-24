(ns clojure.gdx.application.extends.disposable
  (:require clojure.disposable)
  (:import (com.badlogic.gdx.utils Disposable)))

(extend-type Disposable
  clojure.disposable/Disposable
  (dispose! [this]
    (.dispose this)))
