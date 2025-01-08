(ns clojure.gdx.files
  (:import (com.badlogic.gdx Files)))

(defn internal-file
  "Path relative to the asset directory on Android and to the application's root directory on the desktop. On the desktop, if the file is not found, then the classpath is checked. This enables files to be found when using JWS or applets. Internal files are always readonly."
  [context file]
  (Files/.internal (::files context) file))
