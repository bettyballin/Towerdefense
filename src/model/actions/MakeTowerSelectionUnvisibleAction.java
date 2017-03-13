package model.actions;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import model.entities.TowerTile;

public class MakeTowerSelectionUnvisibleAction implements Action {

	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		TowerTile tile = (TowerTile) event.getOwnerEntity();
		if (tile.isVisible()) {
			tile.removeComponent("ImageRenderComponent");
			tile.setVisible(false);
		}
	}
}
