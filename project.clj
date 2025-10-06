(defproject clojure.gdx "1.0-alpha"
  :dependencies [[org.clojure/clojure "1.12.0"]]
  :java-source-paths ["src"]
  :plugins [[lein-hiera "2.0.0"]
            [lein-codox "0.10.8"]]
  :codox {:source-uri "https://github.com/damn/clojure.gdx/blob/main/{filepath}#L{line}"
          :metadata {:doc/format :markdown}}
  :global-vars {*warn-on-reflection* true})
