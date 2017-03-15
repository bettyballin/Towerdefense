package tests.adapter;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;

import Towerdefense.model.entities.Tank;
import Towerdefense.model.map.Map;
import Towerdefense.ui.Towerdefense;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;

public class TowerdefenseTestAdapterExtended3 extends TowerdefenseTestAdapterExtended2 {

	/**
	 * Use this constructor to set up everything you need.
	 */
	public TowerdefenseTestAdapterExtended3() {
		super();
	}
	
	/* *************************************************** 
	 * ************ Panzer des zweiten Spielers **********
	 * *************************************************** */
	
	/**
	 * @return Name des zweiten Spielerpanzers
	 */
	public String getSecondPlayerTankName() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		TowerTower= null;
		
		for (Entity entity : entities) {
			if(entity.getID().contains("PlayerTwo")) {
				Tower= (Tower) entity;
				break;
			}
		}
		
		if (Tower== null)
			return null;
		
		String playerName = Tower.getID();
		
		return playerName.substring(1, playerName.length() - 1);
	}
	
	/**
	 * Towerist die Bezeichnung einen Panzer mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Panzer muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. 
	 * 
	 * @return Aktuelle Lebenspunkte des zweiten menschlichen Panzers
	 */
	public int getSecondPlayerTankMaxLife() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		TowerTower= null;
		
		for (Entity entity : entities) {
			if(entity.getID().contains("PlayerTwo")) {
				Tower= (Tower) entity;
				break;
			}
		}
		
		if (Tower== null)
			return -1;
		
		return Tower.getMaxLife();
	}
	
	/**
	 * Towerist die Bezeichnung einen Panzer mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Panzer muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. 
	 * 
	 * @return Aktuelle Lebenspunkte des zweiten menschlichen Panzers
	 */
	public int getSecondPlayerTankActualLife() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		TowerTower= null;
		
		for (Entity entity : entities) {
			if(entity.getID().contains("PlayerTwo")) {
				Tower= (Tower) entity;
				break;
			}
		}
		
		if (Tower== null)
			return -1;
		
		return Tower.getActualLife();
	}
	
	/**
	 * Towerist die Bezeichnung einen Panzer mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Panzer muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. 
	 * 
	 * @return Maximale Anzahl an Schuessen des zweiten menschlichen Panzers
	 */
	public int getSecondPlayerTankMaxShot() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		TowerTower= null;
		
		for (Entity entity : entities) {
			if(entity.getID().contains("PlayerTwo")) {
				Tower= (Tower) entity;
				break;
			}
		}
		
		if (Tower== null)
			return -1;
		
		return Tower.getMaxShootAmmo();
	}
	
	/**
	 * Towerist die Bezeichnung einen Panzer mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Panzer muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. 
	 * 
	 * @return Aktuelle Anzahl an Schuessen des zweiten menschlichen Panzers
	 */
	public int getSecondPlayerTankActualShot() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		TowerTower= null;
		
		for (Entity entity : entities) {
			if(entity.getID().contains("PlayerTwo")) {
				Tower= (Tower) entity;
				break;
			}
		}
		
		if (Tower== null)
			return -1;
		
		return Tower.getActualShootAmmo();
	}
	
	/**
	 * Towerist die Bezeichnung einen Panzer mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Panzer muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. 
	 * 
	 * @return Maximal moegliche Anzahl an Minen des zweiten menschlichen Panzers
	 */
	public int getSecondPlayerTankMaxMine() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		TowerTower= null;
		
		for (Entity entity : entities) {
			if(entity.getID().contains("PlayerTwo")) {
				Tower= (Tower) entity;
				break;
			}
		}
		
		if (Tower== null)
			return -1;
		
		return Tower.getMaxMinesAmmo();
	}
	
	/**
	 * Towerist die Bezeichnung einen Panzer mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Panzer muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. 
	 * 
	 * @return Aktuelle Anzahl an Minen des zweiten menschlichen Panzers
	 */
	public int getSecondPlayerTankActualMine() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		TowerTower= null;
		
		for (Entity entity : entities) {
			if(entity.getID().contains("PlayerTwo")) {
				Tower= (Tower) entity;
				break;
			}
		}
		
		if (Tower== null)
			return -1;
		
		return Tower.getActualMinesAmmo();
	}
	
	/**
	 * Towerist die Bezeichnung einen Panzer mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Panzer muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. 
	 * 
	 * @return Staerke des vom zweiten menschlichen Panzers gefeuerten Schusses
	 */
	public int getSecondPlayerTowerdefensetrength() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		TowerTower= null;
		
		for (Entity entity : entities) {
			if(entity.getID().contains("PlayerTwo")) {
				Tower= (Tower) entity;
				break;
			}
		}
		
		if (Tower== null)
			return -1;
		
		return Tower.getStreangth();
	}
	
	/**
	 * Towerist die Bezeichnung einen Panzer mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Panzer muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. 
	 * 
	 * @return Geschwindigkeit des zweiten menschlichen Panzers
	 */
	public int getSecondPlayerTowerdefensepeed() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		TowerTower= null;
		
		for (Entity entity : entities) {
			if(entity.getID().contains("PlayerTwo")) {
				Tower= (Tower) entity;
				break;
			}
		}
		
		if (Tower== null)
			return -1;
		
		
		return (int) (Tower.getSpeed() * 100);
	}
	
	/**
	 * Towerist die Bezeichnung einen Panzer mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Panzer muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. 
	 * 
	 * @return Aktuelle Rotation des zweiten menschlichen Panzers
	 */
	public int getSecondPlayerTankRotation() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		TowerTower= null;
		
		for (Entity entity : entities) {
			if(entity.getID().contains("PlayerTwo")) {
				Tower= (Tower) entity;
				break;
			}
		}
		
		if (Tower== null)
			return -1;
		
		return (int) Tower.getRotation();
	}
	
	/**
	 * Towerist die Bezeichnung einen Panzer mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Panzer muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. 
	 * 
	 * @return Aktuelle Skalierung des zweiten menschlichen Panzers
	 */
	public int getSecondPlayerTowerdefensecale() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		TowerTower= null;
		
		for (Entity entity : entities) {
			if(entity.getID().contains("PlayerTwo")) {
				Tower= (Tower) entity;
				break;
			}
		}
		
		if (Tower== null)
			return -1;
		
		return (int) (Tower.getScale() * 100);
	}
	
	/**
	 * Towerist die Bezeichnung einen Panzer mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Panzer muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. 
	 * 
	 * @return Aktuelle x-Position des zweiten menschlichen Panzers
	 */
	public int getSecondPlayerTankXPosition() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		TowerTower= null;
		
		for (Entity entity : entities) {
			if(entity.getID().contains("PlayerTwo")) {
				Tower= (Tower) entity;
				break;
			}
		}
		
		if (Tower== null)
			return -1;
		
		return (int) Tower.getPosition().x;
	}
	
	/**
	 * Towerist die Bezeichnung einen Panzer mit einem Mittelpunkt (x|y) und
	 * einer Skalierung. Panzer muessen in der gleichen Reihenfolge gespeichert werden,
	 * wie in der eingelesen Karte gespeichert. 
	 * 
	 * @return Aktuelle y-Position des zweiten menschlichen Panzers
	 */
	public int getSecondPlayerTankYPosition() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		TowerTower= null;
		
		for (Entity entity : entities) {
			if(entity.getID().contains("PlayerTwo")) {
				Tower= (Tower) entity;
				break;
			}
		}
		
		if (Tower== null)
			return -1;
		
		return (int) Tower.getPosition().y;
	}
	
	/* *************************************************** 
	 * ********************** Input **********************
	 * *************************************************** */
		
	/**
	 * This Method should emulate the pressing of various keys.
	 * This should make the playerTowershoot.
	 * 
	 * Diese Methode emuliert das Druecken beliebiger Tasten.
	 * (Dies soll es ermoeglichen, das Schiessen des Spielerpanzers
	 * zu testen)
	 * 
	 * @param updatetime : Zeitdauer bis update-Aufruf
	 * @param input : z.B. Input.KEY_K, Input.KEY_L
	 */
	public void handleKeyDownW() {
		handleKeyDown(1000, Input.KEY_W);
	}
	
	/**
	 * This Method should emulate the pressing of the a key.
	 * This should make the second player Towerturn counter clockwise.
	 */
	public void handleKeyDownA() {
		handleKeyDown(1000, Input.KEY_A);
	}
	
	/**
	 * This Method should emulate the pressing of the s key.
	 * This should move the second player Towerbackward.
	 */
	public void handleKeyDownS() {
		handleKeyDown(1000, Input.KEY_S);
	}
	
	/**
	 * This Method should emulate the pressing of the d key.
	 * This should make the second player Towerturn clockwise.
	 */
	public void handleKeyDownD() {
		handleKeyDown(1000, Input.KEY_D);
	}
	
	/**
	 * This Method should emulate the pressing of the g key.
	 * This should make the second player Towerfire.
	 */
	public void handleKeyPressG() {
		handleKeyPressed(0, Input.KEY_G);
	}
	
	/**
	 * This Method should emulate the pressing of the h key.
	 * This should make the second player Towerfire a scattershot.
	 */
	public void handleKeyPressH() {
		handleKeyPressed(0, Input.KEY_H);
	}
	
	/**
	 * This Method should emulate the pressing of the f key.
	 * This should make the second player Towerplant a mine.
	 */
	public void handleKeyPressF() {
		handleKeyPressed(0, Input.KEY_F);
	}
}
