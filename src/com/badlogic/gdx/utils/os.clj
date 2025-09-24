(ns com.badlogic.gdx.utils.os
  (:import (com.badlogic.gdx.utils Os)))

(def value->keyword
  {Os/Android :android
   Os/IOS     :ios
   Os/Linux   :linux
   Os/MacOsX  :mac
   Os/Windows :windows})
