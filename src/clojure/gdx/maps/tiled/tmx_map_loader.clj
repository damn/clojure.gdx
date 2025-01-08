(ns clojure.gdx.maps.tiled.tmx-map-loader
  (:refer-clojure :exclude [load])
  (:import (com.badlogic.gdx.maps.tiled TmxMapLoader)))

(defn load
  "Loads the TiledMap from the given file. The file is resolved via the FileHandleResolver set in the constructor of this class. By default it will resolve to an internal file. The map will be loaded for a y-up coordinate system.

  Parameters:
  file-name - the filename

  Returns:
  the TiledMap "
  [file-name]
  (.load (TmxMapLoader.) file-name))
