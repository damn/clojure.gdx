(ns clojure.gdx.graphics.colors
  (:import (com.badlogic.gdx.graphics Colors)))

; maybe in the docs I can mention which stuff is mutable
; and which functions have side effects
; maybe with color or icons
; I don't want to put exclamation mark everywhere ?
; but tahts how set! works ...
(defn put
  "A general purpose class containing named colors that can be changed at will. For example, the markup language defined by the BitmapFontCache class uses this class to retrieve colors and the user can define his own colors.

  Convenience method to add a color with its name. The invocation of this method is equivalent to the expression Colors.getColors().put(name, color)

  Parameters:
  name - the name of the color
  color - the color
  Returns:
  the previous color associated with name, or null if there was no mapping for name ."
  [name-str color]
  (Colors/put name-str color))
