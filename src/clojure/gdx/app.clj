(ns clojure.gdx.app
  (:import (com.badlogic.gdx Gdx)))

(defn exit []
  (.exit Gdx/app))

(defmacro post-runnable [& exprs]
  `(.postRunnable Gdx/app (fn [] ~@exprs)))
