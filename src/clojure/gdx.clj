(ns clojure.gdx
  (:import (com.badlogic.gdx Gdx)))

(defn initialize []
  (def app      Gdx/app)
  (def audio    Gdx/audio)
  (def files    Gdx/files)
  (def gl       Gdx/gl)
  (def gl20     Gdx/gl20)
  (def gl30     Gdx/gl30)
  (def gl31     Gdx/gl31)
  (def gl32     Gdx/gl32)
  (def graphics Gdx/graphics)
  (def input    Gdx/input)
  (def net      Gdx/net))
