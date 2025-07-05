(ns clojure.gdx.utils.shared-library-loader
  (:import (com.badlogic.gdx.utils SharedLibraryLoader
                                   Os)))

(let [mapping {Os/Android :android
               Os/IOS     :ios
               Os/Linux   :linux
               Os/MacOsX  :mac
               Os/Windows :windows}]
  (defn operating-system []
    (get mapping SharedLibraryLoader/os)))
