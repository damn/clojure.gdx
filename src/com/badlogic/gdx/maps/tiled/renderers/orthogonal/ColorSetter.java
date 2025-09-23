package com.badlogic.gdx.maps.tiled.renderers.orthogonal;

import com.badlogic.gdx.graphics.Color;

public interface ColorSetter {

  public float apply(Color color, float x, float y);
}
