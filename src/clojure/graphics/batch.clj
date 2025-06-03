(ns clojure.graphics.batch)

(defprotocol Batch
  (set-color! [_ color])

  (draw! [_
          texture-region
          {:keys [x
                  y
                  origin-x
                  origin-y
                  width
                  height
                  scale-x
                  scale-y
                  rotation]}]

         "Draws a rectangle with the bottom left corner at x,y and stretching the region to cover the given width and height.
         The rectangle is offset by originX, originY relative to the origin. Scale specifies the scaling factor by which the rectangle

         should be scaled around originX, originY. Rotation specifies the angle of counter clockwise rotation of the rectangle around originX, originY.

         rotation in degrees ")
      (begin! [_])
      (end! [_])
      (set-projection-matrix! [_ matrix]))
