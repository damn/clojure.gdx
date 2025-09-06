(ns clojure.gdx.scenes.scene2d
  (:require [clojure.gdx.scenes.scene2d.group :as group]
            [clojure.gdx.scenes.scene2d.stage :as stage] )
  (:import (clojure.lang ILookup)
           (clojure.gdx.scenes.scene2d Stage)))

(defn stage [viewport batch]
  (proxy [Stage ILookup] [viewport batch (atom nil)]
    (valAt [id]
      (group/find-actor-with-id (stage/root this) id))))
