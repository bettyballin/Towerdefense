package model.factory;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.entity.Entity;
import eea.engine.event.ANDEvent;
import eea.engine.event.Event;
import eea.engine.event.NOTEvent;
import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.event.basicevents.MouseClickedEvent;
import eea.engine.event.basicevents.MouseEnteredEvent;
import eea.engine.interfaces.IEntityFactory;
import model.actions.MakeTowerSelectionUnvisibleAction;
import model.actions.MakeTowerSelectionVisibleAction;
import model.actions.SpawnTowerAction;
import model.entities.TowerTile;
import model.events.MouseNotOverTowerEvent;
import model.events.MouseOnLeftHalfEvent;
import model.events.MouseOnRightHalfEvent;
import model.path.Path;

public class TowerTileFactory implements IEntityFactory {

	private final int[][] pathArray;
	private List<TowerTile> tileList;
	private int nextTile;

	public TowerTileFactory(Path path) {
		path.createTowerTileArray();
		this.tileList = path.getTowerTiles();
		this.pathArray = path.getPathArray();
		this.nextTile = 0;
	}

	public boolean hasEntitiesLeft() {
		return (nextTile < tileList.size());
	}
	
	@Override
	public Entity createEntity() {
		TowerTile towerdot = tileList.get(nextTile++);
		
		Event overTile = new ANDEvent(new MouseEnteredEvent(), new MouseNotOverTowerEvent("notovertower"));
		overTile.addAction(new MakeTowerSelectionVisibleAction());
		towerdot.addComponent(overTile);

		Event notOverTile = new NOTEvent(new MouseEnteredEvent());
		notOverTile.addAction(new MakeTowerSelectionUnvisibleAction());
		towerdot.addComponent(notOverTile);
		
		// if ButtonCLickLeftEvent is true, build a bulletTower
		Event buttonclickleft = new ANDEvent(new MouseOnLeftHalfEvent(), new MouseClickedEvent());
		buttonclickleft.addAction(new SpawnTowerAction("bulletTower"));
		
		// if MouseOnRightHalfEvent is true, build a iceTower
		Event buttonclickright = new ANDEvent(new MouseOnRightHalfEvent(), new MouseClickedEvent());
		buttonclickright.addAction(new SpawnTowerAction("iceTower"));

		towerdot.addComponent(buttonclickright);
		towerdot.addComponent(buttonclickleft);
		
		return towerdot;
	}

	private void printArray() {
		StringBuilder sb = new StringBuilder();
		for (int column = 0; column < 6; column++) {
			for (int row = 0; row < 8; row++) {
				sb.append(pathArray[column][row]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
