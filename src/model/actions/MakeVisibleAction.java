package model.actions;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

import model.entities.TowerTile;
import ui.Towerdefense;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

public class MakeVisibleAction implements Action {

	private ImageRenderComponent image;

	public MakeVisibleAction() {
		try {
			image = new ImageRenderComponent(new Image("assets/towerdot2.png"));
		} catch (SlickException e) {
			System.out.println("/assets/towerdot2.png not found in MakeVisibleAction.java");
		}
	}

	private boolean isValidTowerTilePosition(TowerTile tile, Vector2f position) {
		return (position.x >= tile.getPosition().x - 30 && position.x <= tile.getPosition().x + 30
				&& position.y >= tile.getPosition().y - 30 && position.y <= tile.getPosition().y + 30);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		Vector2f mousePosition = new Vector2f(gc.getInput().getMouseX(), gc.getInput().getMouseY());
		List<Entity> entitylist = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		for (Entity e : entitylist) {
			if (e.getID() == "towertile") {
				if (isValidTowerTilePosition((TowerTile) e, mousePosition)) {
					for (Entity entity : entitylist) {
						if (entity.getID() == "towerbutl") {
							try {
								//set left Button position and add a Image, visibility is now true 
								e.removeComponent("ImageRenderComponent");
								entity.addComponent(new ImageRenderComponent(new Image("assets/bulletTower.png")));
								entity.setScale((float) 0.5);

								entity.setPosition(new Vector2f(e.getPosition().getX() - 20, e.getPosition().getY()));

								entity.setVisible(true);
							} catch (SlickException en) {
								System.out.println("/assets/bulletTower.png not found in MakeVisibleAction.java");
							}
						}
						if (entity.getID() == "towerbutr") {
							try {
								//set right Button Position and add a Image,visibility is now true 
								e.removeComponent("ImageRenderComponent");
								entity.addComponent(new ImageRenderComponent(new Image("assets/iceTower.png")));
								entity.setScale((float) 0.5);

								entity.setPosition(new Vector2f(e.getPosition().getX() + 25, e.getPosition().getY()));

								entity.setVisible(true);
							} catch (SlickException en) {
								System.out.println("/assets/iceTower.png not found in MakeVisibleAction.java");
							}
						}

					}
				}
			}
		}
	}

}
