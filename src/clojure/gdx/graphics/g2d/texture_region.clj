(ns clojure.gdx.graphics.g2d.texture-region
  (:import (com.badlogic.gdx.graphics Texture)
           (com.badlogic.gdx.graphics.g2d TextureRegion)))

(defn create
  "Defines a rectangular area of a texture. The coordinate system used has its origin in the upper left corner with the x-axis pointing to the right and the y axis pointing downwards.

  Parameters:
  width - The width of the texture region. May be negative to flip the sprite when drawn.
  height - The height of the texture region. May be negative to flip the sprite when drawn."
  ([^Texture texture]
   (TextureRegion. texture))
  ([^Texture texture x y w h]
   (TextureRegion. texture (int x) (int y) (int w) (int h))))

(defn ->create
  "Constructs a region with the same texture as the specified region and sets the coordinates relative to the specified region.

  Parameters:
  width - The width of the texture region. May be negative to flip the sprite when drawn.
  height - The height of the texture region. May be negative to flip the sprite when drawn. "
  [^TextureRegion texture-region x y w h]
  (TextureRegion. texture-region (int x) (int y) (int w) (int h)))

(defn dimensions
  "Returns the region's width and height as vector [w h]."
  [^TextureRegion texture-region]
  [(.getRegionWidth  texture-region)
   (.getRegionHeight texture-region)])
