package model.factory;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import model.entities.Explosion;
import model.entities.Shoot;
import model.entities.Tower;
import eea.engine.component.RenderComponent;
import eea.engine.component.render.AnimationRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.interfaces.IEntityFactory;

public class ExplosionFactory implements IEntityFactory{

	private final Tower tower;
	private final float posX;
	private final float posY;
	private final int width;
	private final int height;
	
	public ExplosionFactory(Tower tower, float posX, float posY,int width,int height){
		this.tower = tower;
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
	}

	@Override
	public Entity createEntity() {
		Entity explosion = new Explosion("explosion", width, height, 0.005f);
		explosion.setPosition(new Vector2f(posX-50,posY-50));
		String img = "expl";
		if(tower.getID() == "iceTower") img = "ice";
		try {
			RenderComponent anim = new AnimationRenderComponent(new Image[]{
				new Image("assets/"+img+"01.png"),
				new Image("assets/"+img+"02.png"),
				new Image("assets/"+img+"03.png"),
				new Image("assets/"+img+"04.png"),
				new Image("assets/"+img+"05.png"),
				new Image("assets/"+img+"06.png"),
				new Image("assets/"+img+"07.png"),
				new Image("assets/"+img+"08.png"),
				new Image("assets/"+img+"09.png"),
				new Image("assets/"+img+"10.png"),
				new Image("assets/"+img+"11.png"),
				new Image("assets/"+img+"12.png"),
				new Image("assets/"+img+"13.png"),
				new Image("assets/"+img+"14.png"),
				new Image("assets/"+img+"15.png"),
				new Image("assets/"+img+"16.png")
			}, 0.01f, width, height, false);
				explosion.addComponent(anim);
		}catch (SlickException e) {
			System.out.println("ExplosionFactory: Explosion images not found in /assets");
			e.printStackTrace();
		}
		return explosion;
	}
}
