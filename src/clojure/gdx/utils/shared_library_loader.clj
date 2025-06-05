(ns clojure.gdx.utils.shared-library-loader
  (:import (com.badlogic.gdx.utils Architecture
                                   Architecture$Bitness
                                   Os
                                   SharedLibraryLoader)))

(def ^:private bitness-mapping {Architecture$Bitness/_128 :architecture.bitness/_128
                                Architecture$Bitness/_32  :architecture.bitness/_32
                                Architecture$Bitness/_64  :architecture.bitness/_64})

(defn bitness
  "Returns one of: `:architecture.bitness/_128`, `:architecture.bitness/_32` or `:architecture.bitness/_64`."
  []
  (bitness-mapping SharedLibraryLoader/bitness))

(def ^:private architecture-mapping {Architecture/ARM       :architecture/arm
                                     Architecture/LOONGARCH :architecture/loongarch
                                     Architecture/RISCV     :architecture/riscv
                                     Architecture/x86       :architecture/x86})

(defn architecture
  "Returns one of: `:architecture/arm`, `:architecture/loongarch`, `:architecture/riscv` or `:architecture/x86`."
  []
  (architecture-mapping SharedLibraryLoader/architecture))

(def ^:private os-mapping {Os/Android :os/android
                           Os/IOS     :os/ios
                           Os/Linux   :os/linux
                           Os/MacOsX  :os/mac-osx
                           Os/Windows :os-windows})

(defn os
  "Returns one of: `:os/android`, `:os/ios`, `:os/linux`, `:os/mac-osx` or `:os-windows`."
  []
  (os-mapping SharedLibraryLoader/os))
