(ns com.badlogic.gdx.graphics.color
  (:import (com.badlogic.gdx.graphics Color)
           (com.badlogic.gdx.utils NumberUtils)))

(defn ->java ^Color [[r g b a]]
  (Color. r g b a))

(defn float-bits ^Float [[r g b a]]
  (let [color (bit-or (bit-shift-left (int (* 255 (float a))) 24)
                      (bit-shift-left (int (* 255 (float b))) 16)
                      (bit-shift-left (int (* 255 (float g))) 8)
                      (int (* 255 (float r))))]
    (NumberUtils/intToFloatColor (unchecked-int color))))

(comment
 ; Make independent of libgdx:

 (comment
  (float-bits [0.3 0.5 0.5 1])
  -8.4903526E37

  (float-bits2 [0.3 0.5 0.5 1])
  )

 (defn int-to-float-color [value]
   (Float/intBitsToFloat (unchecked-int (bit-and value 0xfeffffff))))

 (defn float-bits2 [[r g b a]]
   (let [color (bit-or (bit-shift-left (int (* 255 (float a))) 24)
                       (bit-shift-left (int (* 255 (float b))) 16)
                       (bit-shift-left (int (* 255 (float g))) 8)
                       (int (* 255 (float r))))]
     (int-to-float-color (unchecked-int color)))))
