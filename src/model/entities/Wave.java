package model.entities;

import java.util.ArrayList;
import java.util.List;

import eea.engine.entity.Entity;
import model.factory.EnemyFactory;

public class Wave extends Entity{

	private int waveSize;
	private String enemyType;
	
	public Wave(String entityID, int waveSize) {
		super(entityID);
		this.waveSize = waveSize;
		this.enemyType = entityID;
	}
	
	public List<Enemy> getWave(){
		List<Enemy> enemyList = new ArrayList<Enemy>();
		EnemyFactory factory = new EnemyFactory(enemyType);
		for(int i = 0; i < waveSize; i++){
			Enemy e = (Enemy) factory.createEntity();
			enemyList.add(e);
		}
		return enemyList;
	}
	
	public String getEnemyType(){
		return this.enemyType;
	}
	public void setEnemyType(String enemy){
		this.enemyType = enemy;
	}
	public int getWaveSize(){
		return this.waveSize;
	}
	public void setWaveSize(int waveSize){
		this.waveSize = waveSize;
	}

}
