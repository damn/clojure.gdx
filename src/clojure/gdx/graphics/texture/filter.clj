(ns clojure.gdx.graphics.texture.filter
  (:import (com.badlogic.gdx.graphics Texture$TextureFilter)))

(comment
 Nearest ; Fetch the nearest texel that best maps to the pixel on screen.
 Linear ; Fetch four nearest texels that best maps to the pixel on screen.
 MipMap ; @see TextureFilter#MipMapLinearLinear
 MipMapNearestNearest ; Fetch the best fitting image from the mip map chain based on the pixel/texel ratio and then sample the texels with a nearest filter.
 MipMapLinearNearest ; Fetch the best fitting image from the mip map chain based on the pixel/texel ratio and then sample the texels with a linear filter.
 MipMapNearestLinear ; Fetch the two best fitting images from the mip map chain and then sample the nearest texel from each of the two images, combining them to the final output pixel.
 MipMapLinearLinear ; Fetch the two best fitting images from the mip map chain and then sample the four nearest texels from each of the two images, combining them to the final output pixel.
 )

(let [mapping {:linear Texture$TextureFilter/Linear}]
  (defn k->value [k]
    (when-not (contains? mapping k)
      (throw (IllegalArgumentException. (str "Unknown Key: " k ". \nOptions are:\n" (sort (keys mapping))))))
    (k mapping)))
