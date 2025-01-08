(ns clojure.graphics)

(defprotocol Graphics
  (delta-time [_] "The time span between the current frame and the last frame in seconds.")
  (frames-per-second [_] "The average number of frames per second.")

  ; TODO just 'cursor' is ok

  (new-cursor [_ pixmap hotspot-x hotspot-y]
              "Create a new cursor represented by the Pixmap. The Pixmap must be in RGBA8888 format, width & height must be powers-of-two greater than zero (not necessarily equal) and of a certain minimum size (32x32 is a safe bet), and alpha transparency must be single-bit (i.e., 0x00 or 0xFF only). This function returns a Cursor object that can be set as the system cursor by calling setCursor(Cursor) .

              Parameters:

              pixmap - the mouse cursor image as a Pixmap

              xHotspot - the x location of the hotspot pixel within the cursor image (origin top-left corner)

              yHotspot - the y location of the hotspot pixel within the cursor image (origin top-left corner)

              Returns:

              a cursor object that can be used by calling setCursor(Cursor) or null if not supported")
  (set-cursor [_ cursor]
              "Only viable on the lwjgl-backend and on the gwt-backend. Browsers that support cursor:url() and support the png format (the pixmap is converted to a data-url of type image/png) should also support custom cursors. Will set the mouse cursor image to the image represented by the Cursor. It is recommended to call this function in the main render thread, and maximum one time per frame.

              Parameters:
              cursor - the mouse cursor as a Cursor"))
