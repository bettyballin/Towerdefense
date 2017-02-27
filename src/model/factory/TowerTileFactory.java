package model.factory;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.event.NOTEvent;
import eea.engine.interfaces.IEntityFactory;
import model.actions.MakeUnvisibleAction;
import model.actions.MakeVisibleAction;
import model.actions.SpawnTowerAction;
import model.entities.PathTile;
import model.entities.TowerTile;
import model.events.MapClickEvent;
import model.events.MouseOverTowerTileEvent;

public class TowerTileFactory implements IEntityFactory{

	private final int[][] mapArray;
	private List<TowerTile> tileList;
	private int nextTile;

	public TowerTileFactory(int[][] mapArray) {
		this.mapArray = mapArray;
		fillMapArray();
		this.tileList = new ArrayList<TowerTile>();
		getTowerTileList();
		this.nextTile = 0;
		//printMapArray();
	}
	public boolean hasEntitiesLeft() {
		return (nextTile < tileList.size());
	}
	
	public void getTowerTileList(){
		for (int column = 0; column < 6; column++) {
			for (int row = 0; row < 8; row++) {
				if(mapArray[column][row] == 2){
					TowerTile towerdot = new TowerTile("towertile");
					towerdot.setPosition(row*100,column*100);
					tileList.add(towerdot);
				}
			}
		}
	}
	
	public int[][] getMapArray(){
		return mapArray;
	}
	
	@Override
	public Entity createEntity() {
		TowerTile towerdot = tileList.get(nextTile++);
		try{
			towerdot.addComponent(new ImageRenderComponent(new Image("assets/towerdot.png")));
		}catch (SlickException e) {
			System.out.println("/assets/towerdot.png not found in PathTileFactory.java");
		}

		MouseOverTowerTileEvent overTile = new MouseOverTowerTileEvent("mouseovertile");
		overTile.addAction(new MakeVisibleAction());
		towerdot.addComponent(overTile);
		
		NOTEvent notOverTile = new NOTEvent(new MouseOverTowerTileEvent("mouseNotOvertile"));
		notOverTile.addAction(new MakeUnvisibleAction());
		towerdot.addComponent(notOverTile);

		MapClickEvent mapClick = new MapClickEvent();
		mapClick.addAction(new SpawnTowerAction("bulletTower"));
		towerdot.addComponent(mapClick);		

		return towerdot;
	}

	// fill Path array with valid Tower positions
	public void fillMapArray(){
		for (int column = 0; column < 6; column++) {
			for (int row = 0; row < 8; row++) {
				if(row < 6){
					if(mapArray[column][row+1] == 1 && mapArray[column][row] == 1){	// →→
						if(column > 0 && row < 7) mapArray[column-1][row+1] = 2;	// set valid tower position under these
						if(column < 5) mapArray[column+1][row] = 2;
					}
				}
				if(column < 5){
					if(mapArray[column+1][row] == 1 && mapArray[column][row] == 1){	// ↓
																					// ↓
						if(row < 7) mapArray[column][row+1] = 2;					// set valid tower position on the right of these
						if(column < 5 && row > 0) mapArray[column+1][row-1] = 2;
					}
				}
			}
		}
		for (int column = 0; column < 5; column++) {
			for (int row = 0; row < 7; row++) {
				if(mapArray[column][row] == 2 && mapArray[column][row+1] == 0 && mapArray[column+1][row] == 1 && mapArray[column+1][row+1] == 2){
					mapArray[column][row+1] = 2;
				}
				if(mapArray[column][row] == 2 && mapArray[column][row+1] == 1 && mapArray[column+1][row] == 0 && mapArray[column+1][row+1] == 2){
					mapArray[column+1][row] = 2;
				}
			}
		}
		mapArray[0][6] = 0; mapArray[0][7] = 0;
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
