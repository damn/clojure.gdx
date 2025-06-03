(ns clojure.graphics.texture)

(defprotocol Texture
  (region [texture]
          [texture x y w h]))

; clojure.graphics.2d.texture-region ?
(defprotocol TextureRegion
  (sub-region [texture-region x y w h]))
