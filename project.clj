(def libgdx-version "1.13.5")

(defproject clojure.gdx "0.1"
  :repositories [["jitpack" "https://jitpack.io"]] ; for shapedrawer
  :dependencies [
                 [org.clojure/clojure "1.12.0"]
                 [clojure.gdl "0.1"]
                 [com.badlogicgames.gdx/gdx                   ~libgdx-version]
                 [com.badlogicgames.gdx/gdx-backend-lwjgl3    ~libgdx-version]
                 [com.badlogicgames.gdx/gdx-freetype          ~libgdx-version]
                 [com.badlogicgames.gdx/gdx-freetype-platform ~libgdx-version :classifier "natives-desktop"]
                 [com.badlogicgames.gdx/gdx-platform          ~libgdx-version :classifier "natives-desktop"]
                 [com.kotcrab.vis/vis-ui "1.5.2"]
                 [space.earlygrey/shapedrawer "2.5.0"]
                 ]
  :java-source-paths ["src"]
  :plugins [[lein-hiera "2.0.0"]
            [lein-codox "0.10.8"]]
  :codox {:source-uri "https://github.com/damn/clojure.gdx/blob/main/{filepath}#L{line}"
          :metadata {:doc/format :markdown}}
  :global-vars {*warn-on-reflection* true})
