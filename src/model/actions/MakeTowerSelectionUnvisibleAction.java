package model.actions;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import model.entities.TowerTile;

public class MakeTowerSelectionUnvisibleAction implements Action {

	/*
	 * make tower selection buttons unvisible when a tower is built over the
	 * towertile
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
		TowerTile tile = (TowerTile) event.getOwnerEntity();
		tile.removeComponent("ImageRenderComponent");
		tile.setButtonVisibility(false);
		tile.setVisible(false);
	}
}
