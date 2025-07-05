(ns clojure.gdx.graphics.color
  (:import (com.badlogic.gdx.graphics Color)))

(let [mapping {:black       Color/BLACK
               :blue        Color/BLUE
               :brown       Color/BROWN
               :chartreuse  Color/CHARTREUSE
               :clear       Color/CLEAR
               :clear-white Color/CLEAR_WHITE
               :coral       Color/CORAL
               :cyan        Color/CYAN
               :dark-gray   Color/DARK_GRAY
               :firebrick   Color/FIREBRICK
               :forest      Color/FOREST
               :gold        Color/GOLD
               :goldenrod   Color/GOLDENROD
               :gray        Color/GRAY
               :green       Color/GREEN
               :light-gray  Color/LIGHT_GRAY
               :lime        Color/LIME
               :magenta     Color/MAGENTA
               :maroon      Color/MAROON
               :navy        Color/NAVY
               :olive       Color/OLIVE
               :orange      Color/ORANGE
               :pink        Color/PINK
               :purple      Color/PURPLE
               :red         Color/RED
               :royal       Color/ROYAL
               :salmon      Color/SALMON
               :scarlet     Color/SCARLET
               :sky         Color/SKY
               :slate       Color/SLATE
               :tan         Color/TAN
               :teal        Color/TEAL
               :violet      Color/VIOLET
               :white       Color/WHITE
               :yellow      Color/YELLOW}]
  (defn- k->value [k]
    (when-not (contains? mapping k)
      (throw (IllegalArgumentException. (str "Unknown Key: " k ". \nOptions are:\n" (sort (keys mapping))))))
    (k mapping)))

(defn ->obj ^Color [c]
  (cond (keyword? c) (k->value c)
        (vector?  c) (let [[r g b a] c]
                       (Color. r g b a))
        :else (throw (ex-info "Cannot understand color" c))))

(defn float-bits [[r g b a]]
  (Color/toFloatBits (float r)
                     (float g)
                     (float b)
                     (float a)))
