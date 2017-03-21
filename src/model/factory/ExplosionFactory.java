package model.factory;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import model.entities.Explosion;
import model.entities.Tower;
import eea.engine.component.render.AnimationRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.interfaces.IEntityFactory;

public class ExplosionFactory implements IEntityFactory {

	private final float posX;
	private final float posY;
	private final float width;
	private final float height;
	private Image[] image;

	/**
	 * Constructs an explosion factory
	 * 
	 * @param tower
	 *            for creating a red or a blue explosion
	 * @param posX
	 *            x position of explosion
	 * @param posY
	 *            y position of explosion
	 * @param width
	 *            width of explosion
	 * @param height
	 *            height of explosion
	 */
	public ExplosionFactory(Tower tower, float posX, float posY, float width, float height) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
		String img = "expl";
		if (tower.getID() == "iceTower")
			img = "ice";
		try {
			image = new Image[] { new Image("assets/" + img + "01.png"), new Image("assets/" + img + "02.png"),
					new Image("assets/" + img + "03.png"), new Image("assets/" + img + "04.png"),
					new Image("assets/" + img + "05.png"), new Image("assets/" + img + "06.png"),
					new Image("assets/" + img + "07.png"), new Image("assets/" + img + "08.png"),
					new Image("assets/" + img + "09.png"), new Image("assets/" + img + "10.png"),
					new Image("assets/" + img + "11.png"), new Image("assets/" + img + "12.png"),
					new Image("assets/" + img + "13.png"), new Image("assets/" + img + "14.png"),
					new Image("assets/" + img + "15.png"), new Image("assets/" + img + "16.png") };
		} catch (SlickException e) {
			System.err.println("assets/expl...png or ice...png not found in ExplosionFactory");
		}
	}

	@Override
	public Entity createEntity() {
		// create explosion with given parameters and construct an animation
		Entity explosion = new Explosion("explosion", width, height, 0.005f);
		explosion.setPosition(new Vector2f(posX - 50, posY - 50));
		AnimationRenderComponent anim = new AnimationRenderComponent(image, 0.01f, width, height, false);
		explosion.addComponent(anim);

		return explosion;
	}
}
