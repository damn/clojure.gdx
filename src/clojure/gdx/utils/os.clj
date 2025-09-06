(ns clojure.gdx.utils.os
  (:import (com.badlogic.gdx.utils Os)))

(def value->k {Os/Android :android
               Os/IOS     :ios
               Os/Linux   :linux
               Os/MacOsX  :mac
               Os/Windows :windows})
