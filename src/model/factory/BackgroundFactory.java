package model.factory;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.interfaces.IEntityFactory;

public class BackgroundFactory implements IEntityFactory {

	@Override
	public Entity createEntity() {
		// create a new entity and place it in the center of the game window
		// which has the size 800x600
		Entity background = new Entity("background");
		background.setPosition(new Vector2f(400, 300));
		background.setScale(1.3f);
		try {
			background.addComponent(new ImageRenderComponent(new Image("/assets/background.png")));
		} catch (SlickException e) {
			System.err.println("/assets/background.png not found in BackgroundFactory.java");
		}
		return background;
	}

}
