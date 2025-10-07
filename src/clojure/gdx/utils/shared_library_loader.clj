(ns clojure.gdx.utils.shared-library-loader
  (:import (com.badlogic.gdx.utils SharedLibraryLoader
                                   Os)))

(def ^:private os->keyword
  {Os/Android :android
   Os/IOS     :ios
   Os/Linux   :linux
   Os/MacOsX  :mac
   Os/Windows :windows})

(defn os []
  (os->keyword SharedLibraryLoader/os))
