(ns clojure.gdx.graphics.g2d.bitmap-font
  (:import (com.badlogic.gdx.graphics.g2d BitmapFont)))

(defn line-height [font]
  (BitmapFont/.getLineHeight font))

(defn data [font]
  (BitmapFont/.getData font))

(defn draw [& {:keys [font batch text x y target-width align wrap?]
               :or {target-width 0
                    wrap? false}}]
  (BitmapFont/.draw font
                    batch
                    text
                    (float x)
                    (float y)
                    (float target-width)
                    align
                    wrap?))
