(ns clojure.gdx.graphics.texture
  (:import (com.badlogic.gdx.graphics Texture
                                      Texture$TextureFilter
                                      Pixmap)))

(defn create [^Pixmap pixmap]
  (Texture. pixmap))

(def filter-linear Texture$TextureFilter/Linear)
