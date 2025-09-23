package com.badlogic.gdx.scenes.scene2d;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.Viewport;

public class StageWithCtx extends Stage  {

  public Object ctx;

	public StageWithCtx (Viewport viewport, Batch batch, Object ctx) {
		super(viewport, batch);
    this.ctx = ctx;
	}

}
