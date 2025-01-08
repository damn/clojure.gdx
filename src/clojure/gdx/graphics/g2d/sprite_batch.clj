(ns clojure.gdx.graphics.g2d.sprite-batch
  "Draws batched quads using indices."
  (:import (com.badlogic.gdx.graphics.g2d SpriteBatch)))

(defn create
  "Constructs a new sprite-batch with a size of 1000, one buffer, and the default shader."
  []
  (SpriteBatch.))
