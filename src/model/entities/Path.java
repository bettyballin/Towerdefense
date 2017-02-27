package model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;
import eea.engine.event.basicevents.CollisionEvent;
import model.events.EnemyDetectionEvent;
import model.factory.ExplosionFactory;
import ui.GameplayState;
import ui.Towerdefense;

public class Path extends Entity{
	
	private List<PathTile> route;
	private final int[][] mapArray; // array that corresponds to game window
	
	public Path(String entityID) {
		super(entityID);
		this.route = new ArrayList<PathTile>();
		mapArray = new int[6][8];
		for (int column = 0; column < 6; column++) {
			for (int row = 0; row < 8; row++) {
				mapArray[column][row] = 0;	// 1 is PathTile, 2 is valid position for tower, 0 else
			}
		}
		createMap();
	}
	public List<PathTile> getRoute(){
		return route;
	}
	public int[][] getMapArray(){
		return mapArray;
	}
	
	// creates a random Path, starting at the upper or left border of the game window
	// ending at the bottom right corner
	public void createMap() {
		// determine position of first PathTile
		PathTile startTile = new PathTile("startTile");
		int row = 0;
		int column = 0;
		Double rand = Math.random() * 100;

		startTile.setPosition(row*100, column*100);
		startTile.setDirection1("right");
		route.add(startTile);
		mapArray[column][row] = 1;

		// determine other MapTiles
		PathTile tile = null;
		int counter = 0;
		while( !(column == 5 && row == 6) ){ // while we are not yet at the end position
			tile = new PathTile("tile");
			rand = Math.random() * 100;
			int direction = rand.intValue() % 2; // 0 is right, 1 is down
			if(direction == 0 && (column > 2 && row < 6 || row < 5)){ // go to the right
				row++; counter++;
				route.get(counter-1).setDirection2("right"); // set direction for first part of former tile
				tile.setDirection1("right");				 // set direction for second part of former tile
				tile.setPosition(row*100, column*100);
				mapArray[column][row] = 1;
				route.add(tile);
			}
			else if (column < 5){	// go down
				column++; counter++;
				route.get(counter-1).setDirection2("down");
				tile.setDirection1("down");
				tile.setPosition(row*100, column*100);
				mapArray[column][row] = 1;
				route.add(tile);
			}
		}
		row = 7;
		column = 5;
		PathTile endTile = new PathTile("endTile");
		endTile.setDirection1("right");
		endTile.setDirection2("right");
		endTile.setPosition(row*100, column*100);
		mapArray[column][row] = 1;
		route.add(endTile);
	}
	
	private void printMapArray() {
		StringBuilder sb = new StringBuilder();
		for (int column = 0; column < 6; column++) {
			for (int row = 0; row < 8; row++) {
				sb.append(mapArray[column][row]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
