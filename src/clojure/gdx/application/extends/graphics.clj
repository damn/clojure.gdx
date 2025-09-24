(ns clojure.gdx.application.extends.graphics
  (:require clojure.graphics
            clojure.graphics.pixmap
            clojure.graphics.texture
            clojure.graphics.texture-region)
  (:import (com.badlogic.gdx Graphics)
           (com.badlogic.gdx.files FileHandle)
           (com.badlogic.gdx.graphics GL20
                                      Pixmap
                                      Pixmap$Format
                                      Texture)
           (com.badlogic.gdx.graphics.g2d TextureRegion)))

(extend-type Graphics
  clojure.graphics/Graphics
  (delta-time [this]
    (.getDeltaTime this))
  (frames-per-second [this]
    (.getFramesPerSecond this))
  (set-cursor! [this cursor]
    (.setCursor this cursor))
  (cursor [this pixmap hotspot-x hotspot-y]
    (.newCursor this pixmap hotspot-x hotspot-y))
  (clear!
    ([this [r g b a]]
     (clojure.graphics/clear! this r g b a))
    ([this r g b a]
     (let [clear-depth? false
           apply-antialiasing? false
           gl20 (.getGL20 this)]
       (GL20/.glClearColor gl20 r g b a)
       (let [mask (cond-> GL20/GL_COLOR_BUFFER_BIT
                    clear-depth? (bit-or GL20/GL_DEPTH_BUFFER_BIT)
                    (and apply-antialiasing? (.coverageSampling (.getBufferFormat this)))
                    (bit-or GL20/GL_COVERAGE_BUFFER_BIT_NV))]
         (GL20/.glClear gl20 mask)))))
  (texture [this file-handle]
    (Texture. ^FileHandle file-handle))
  (pixmap
    ([this ^FileHandle file-handle]
     (Pixmap. file-handle))
    ([this width height pixmap-format]
     (Pixmap. (int width)
              (int height)
              (case pixmap-format
                :pixmap.format/RGBA8888 Pixmap$Format/RGBA8888)))))

(extend-type Texture
  clojure.graphics.texture/Texture
  (region
    ([this]
     (TextureRegion. this))
    ([this [x y w h]]
     (TextureRegion. this
                     (int x)
                     (int y)
                     (int w)
                     (int h)))
    ([this x y w h]
     (TextureRegion. this
                     (int x)
                     (int y)
                     (int w)
                     (int h)))))

(extend-type TextureRegion
  clojure.graphics.texture-region/TextureRegion
  (dimensions [this]
    [(.getRegionWidth  this)
     (.getRegionHeight this)]))

(extend-type Pixmap
  clojure.graphics.pixmap/Pixmap
  (set-color! [this [r g b a]]
    (.setColor this r g b a))

  (draw-pixel! [this x y]
    (.drawPixel this x y))

  (texture [this]
    (Texture. this)))
