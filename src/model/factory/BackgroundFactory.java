package model.factory;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.interfaces.IEntityFactory;
import model.entities.Background;

public class BackgroundFactory implements IEntityFactory {
	
	public BackgroundFactory(){
	}
	
	@Override
	public Entity createEntity() {
		Background background = new Background("background");
		background.setPosition(new Vector2f(400, 300));
		background.setScale(1.3f);
		try {
			background.addComponent(new ImageRenderComponent(new Image("/assets/background.png")));
		} catch (SlickException e) {
			System.out.println("/assets/background.png not found in BackgroundFactory.java");
		}
		return background;
	}

}
