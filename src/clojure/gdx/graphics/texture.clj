(ns clojure.gdx.graphics.texture
  "A Texture wraps a standard OpenGL ES texture.

  A Texture can be managed. If the OpenGL context is lost all managed textures get invalidated. This happens when a user switches to another application or receives an incoming call. Managed textures get reloaded automatically.

  A Texture has to be bound via the `GLTexture.bind()` method in order for it to be applied to geometry. The texture will be bound to the currently active texture unit specified via `GL20.glActiveTexture(int)`.

  You can draw Pixmaps to a texture at any time. The changes will be automatically uploaded to texture memory. This is of course not extremely fast so use it with care. It also only works with unmanaged textures.

  A Texture must be disposed when it is no longer used"
  (:import (com.badlogic.gdx.graphics Texture
                                      Texture$TextureFilter
                                      Pixmap)))

; TODO file-handle ...

(defn create [^Pixmap pixmap]
  (Texture. pixmap))

(def filter-linear Texture$TextureFilter/Linear)
