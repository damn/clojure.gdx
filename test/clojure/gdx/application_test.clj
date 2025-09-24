(ns clojure.gdx.application-test
  (:require clojure.application
            clojure.gdx.application))

(defn -main []
  (clojure.gdx.application/start!
   {:listener (reify clojure.application/Listener
                (create [_ context]
                  (println"create!"))
                (dispose [_]
                  (println "dispose!"))
                (pause [_])
                (render [_]
                  (println "render!"))
                (resize [_ width height]
                  (println "resize!"))
                (resume [_]))
    :config {:title "Fooz Baaz"
             :windowed-mode {:width 800
                             :height 600}
             :foreground-fps 60}}))
