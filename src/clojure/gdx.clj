(ns clojure.gdx
  "Bindings for core LibGDX components, providing simplified access
  to application, audio, graphics, input, and networking systems."
  (:import (com.badlogic.gdx Gdx)))

(defn bind-gdx-components
  "Creates global bindings for core LibGDX components in the current namespace,
  simplifying access to application, audio, graphics, input, and networking systems."
  []
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
