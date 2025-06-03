(ns clojure.gdx.freetype
  (:require [clojure.gdx :as gdx]
            [clojure.graphics.g2d.bitmap-font :as bitmap-font]
            [clojure.string :as str])
  (:import (com.badlogic.gdx.graphics Texture$TextureFilter)
           (com.badlogic.gdx.graphics.g2d BitmapFont)
           (com.badlogic.gdx.graphics.g2d.freetype FreeTypeFontGenerator
                                                   FreeTypeFontGenerator$FreeTypeFontParameter)
           (com.badlogic.gdx.utils Disposable)))

; Tets: draw font on world-units /ui-units, dispose works & ??

(defn- k->texture-filter [k]
  (case k
    :texture-filter/linear Texture$TextureFilter/Linear))

(defn- font-params [{:keys [size
                            min-filter
                            mag-filter]}]
  (let [params (FreeTypeFontGenerator$FreeTypeFontParameter.)]
    (set! (.size params) size)
    ; .color and this:
    ;(set! (.borderWidth parameter) 1)
    ;(set! (.borderColor parameter) red)
    (set! (.minFilter params) (k->texture-filter min-filter))
    (set! (.magFilter params) (k->texture-filter mag-filter))
    params))

(defn- generate* [file-handle params]
  (let [generator (FreeTypeFontGenerator. (:java-object file-handle))
        font (.generateFont generator (font-params params))]
    (.dispose generator)
    font))

(defn- text-height [^BitmapFont font text]
  (-> text
      (str/split #"\n")
      count
      (* (.getLineHeight font))))

(defn- set-scale! [^BitmapFont font scale]
  (.setScale (.getData font) (float scale)))

(defn- scale-x [^BitmapFont font]
  (.scaleX (.getData font)))

(defn generate [file-handle params]
  (let [^BitmapFont font (generate* file-handle params)
        {:keys [scale
                enable-markup?
                use-integer-positions?]} params]
    (.setScale (.getData font) (float scale))
    (set! (.markupEnabled (.getData font)) enable-markup?)
    (.setUseIntegerPositions font use-integer-positions?)
    (reify
      Disposable
      (dispose [_]
        (.dispose font))

      bitmap-font/BitmapFont
      (draw! [_ batch {:keys [scale x y text h-align up?]}]
        (let [old-scale (float (scale-x font))
              target-width (float 0)
              wrap? false]
          (set-scale! font (* old-scale (float scale)))
          (.draw font
                 (:sprite-batch/java-object batch)
                 text
                 (float x)
                 (float (+ y (if up? (text-height font text) 0)))
                 target-width
                 (gdx/k->align (or h-align :center))
                 wrap?)
          (set-scale! font old-scale))))))
