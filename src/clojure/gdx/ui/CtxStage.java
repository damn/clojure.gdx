package clojure.gdx.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CtxStage extends Stage  {

  public Object ctx;

	public CtxStage (Viewport viewport, Batch batch, Object ctx) {
		super(viewport, batch);
    this.ctx = ctx;
	}

}
