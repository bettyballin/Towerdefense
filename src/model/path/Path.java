package model.path;

import java.util.ArrayList;
import java.util.List;

import eea.engine.entity.Entity;
import model.entities.PathTile;
import model.entities.TowerTile;

public class Path extends Entity{

	private List<PathTile> pathTiles;
	private List<TowerTile> towerTiles;
	private final int[][] pathArray; // array that corresponds to game window
	
	public Path(String name) {
		super(name);
		this.pathTiles = new ArrayList<PathTile>();
		this.towerTiles = new ArrayList<TowerTile>();
		pathArray = new int[6][8];
		for (int column = 0; column < 6; column++) {
			for (int row = 0; row < 8; row++) {
				pathArray[column][row] = 0; // 1 is PathTile, 2 is valid
											// position for tower, 0 else
			}
		}
		createPath();
	}
	
	public List<PathTile> getPathTiles() {
		return pathTiles;
	}

	public int[][] getPathArray() {
		return pathArray;
	}

	public List<TowerTile> getTowerTiles() {
		return towerTiles;
	}

	// creates a random path, starting at the upper or left border of the
	// game window
	// ending at the bottom right corner
	public void createPath() {
		// determine position of first PathTile
		PathTile startTile = new PathTile("pathTile00");
		int row = 0;
		int column = 0;
		Double rand = Math.random() * 100;

		startTile.setPosition(row * 100, column * 100);
		startTile.setDirection1("right");
		pathTiles.add(startTile);
		pathArray[column][row] = 1;

		// determine other pathTiles
		PathTile tile = null;
		int counter = 0;
		while (!(column == 5 && row == 6)) { // while we are not yet at the end
												// position
			rand = Math.random() * 100;
			int direction = rand.intValue() % 2; // 0 is right, 1 is down
			if (direction == 0 && (column > 2 && row < 6 || row < 5)) { // go to
																		// the
																		// right
				row++;
				counter++;
				pathTiles.get(counter - 1).setDirection2("right"); // set
																	// direction
																	// for first
																	// part of
																	// former
																	// tile
				tile = new PathTile("pathTile" + column + row);
				tile.setDirection1("right"); // set direction for second part of
												// former tile
				tile.setPosition(row * 100, column * 100);
				pathArray[column][row] = 1;
				pathTiles.add(tile);
			} else if (column < 5) { // go down
				column++;
				counter++;
				pathTiles.get(counter - 1).setDirection2("down");
				tile = new PathTile("pathTile" + column + row);
				tile.setDirection1("down");
				tile.setPosition(row * 100, column * 100);
				pathArray[column][row] = 1;
				pathTiles.add(tile);
			}
		}
		row = 7;
		column = 5;
		PathTile endTile = new PathTile("pathTile57");
		endTile.setDirection1("right");
		endTile.setDirection2("right");
		endTile.setPosition(row * 100, column * 100);
		pathArray[column][row] = 1;
		pathTiles.add(endTile);
	}

	// fill path array with valid Tower positions
	public void createTowerTileArray() {
		for (int column = 0; column < 6; column++) {
			for (int row = 0; row < 8; row++) {
				if (row < 7) {
					if (pathArray[column][row + 1] == 1 && pathArray[column][row] == 1) {
						if (column > 0 && row < 7)
							pathArray[column - 1][row + 1] = 2; // set valid
																// tower
																// position
																// under these
						if (column < 5)
							pathArray[column + 1][row] = 2;
					}
				}
				if (column < 5) {
					if (pathArray[column + 1][row] == 1 && pathArray[column][row] == 1) {

						if (row < 7)
							pathArray[column][row + 1] = 2; // set valid tower
															// position on the
															// right of these
						if (column < 5 && row > 0)
							pathArray[column + 1][row - 1] = 2;
					}
				}
			}
		}
		for (int column = 0; column < 5; column++) {
			for (int row = 0; row < 7; row++) {
				if (pathArray[column][row] == 2 && pathArray[column][row + 1] == 0 && pathArray[column + 1][row] == 1
						&& pathArray[column + 1][row + 1] == 2) {
					pathArray[column][row + 1] = 2;
				}
				if (pathArray[column][row] == 2 && pathArray[column][row + 1] == 1 && pathArray[column + 1][row] == 0
						&& pathArray[column + 1][row + 1] == 2) {
					pathArray[column + 1][row] = 2;
				}
			}
		}
		getTowerTileList();
	}

	public void getTowerTileList() {
		for (int column = 0; column < 6; column++) {
			for (int row = 0; row < 8; row++) {
				if (pathArray[column][row] == 2) {
					TowerTile towertile = new TowerTile("towerTile" + column + row);
					towertile.setPosition(row * 100, column * 100);
					towerTiles.add(towertile);
				}
			}
		}
	}

	public void printArray() {
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
