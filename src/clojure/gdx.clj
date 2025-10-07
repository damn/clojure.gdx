(ns clojure.gdx
  (:import (com.badlogic.gdx Gdx)))

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
