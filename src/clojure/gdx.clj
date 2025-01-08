; okay - we have those classes - those we finish -
(ns clojure.gdx
  (:require [clojure.gdx.interop :refer [k->input-button k->input-key k->viewport-field]])
  (:import (clojure.lang IFn ILookup)
           (com.badlogic.gdx Gdx Files Graphics Input)
           (com.badlogic.gdx.assets AssetManager)
           (com.badlogic.gdx.audio Sound)
           (com.badlogic.gdx.files FileHandle)
           (com.badlogic.gdx.graphics Color Colors OrthographicCamera Pixmap Pixmap$Format Texture)
           (com.badlogic.gdx.graphics.g2d Batch SpriteBatch TextureRegion)
           (com.badlogic.gdx.math Vector2 Circle Intersector Rectangle)
           (com.badlogic.gdx.utils Disposable ScreenUtils)
           (com.badlogic.gdx.utils.viewport FitViewport Viewport)))

; this remains in `clojure.gdx` and it is the _only_ thing which remains there
; to signify its importance also !
; and app/audio/files/etc is separate
; => so we will have a gl API too ?
(defn context []
  {::app      Gdx/app
   ::audio    Gdx/audio
   ::files    Gdx/files
   ::gl       Gdx/gl
   ::gl20     Gdx/gl20
   ::gl30     Gdx/gl30
   ::gl31     Gdx/gl31
   ::gl32     Gdx/gl32
   ::graphics Gdx/graphics
   ::input    Gdx/input
   ::net      Gdx/net})
