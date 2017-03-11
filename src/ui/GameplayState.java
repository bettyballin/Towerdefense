package ui;

import model.entities.Enemy;
import model.entities.Life;
import model.entities.Money;
import model.entities.Timer;
import model.entities.TowerTile;
import model.events.LifeOverEvent;
import model.events.TimeEvent;
import model.factory.BackgroundFactory;
import model.factory.PathTileFactory;
import model.factory.TowerFactory;
import model.factory.TowerTileFactory;
import model.factory.WaveFactory;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.component.Component;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;
import eea.engine.event.basicevents.KeyPressedEvent;

public class GameplayState extends BasicGameState {

	private int stateID;
	private StateBasedEntityManager entityManager;
	private Integer spiderWaveSize;
	private Integer waspWaveSize;
	
	GameplayState(int stateID) {
		this.stateID = stateID;
		this.entityManager = StateBasedEntityManager.getInstance();
		this.spiderWaveSize = 3;
		this.waspWaveSize = 0;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		BackgroundFactory b = new BackgroundFactory();
		entityManager.addEntity(this.stateID, b.createEntity());

		Entity quit_Game = new Entity("quitgame");
		Event keyEvent = new KeyPressedEvent(Input.KEY_ESCAPE);
		keyEvent.addAction(new ChangeStateInitAction(Towerdefense.MAINMENUSTATE));
		quit_Game.addComponent(keyEvent);
		entityManager.addEntity(stateID, quit_Game);

		Entity pause_Game = new Entity("pausegame");
		Event pauseEvent = new KeyPressedEvent(Input.KEY_P);
		pauseEvent.addAction(new Action() {
			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
				if (gc.isPaused())
					gc.resume();
				else
					gc.pause();
			}
		});
		pause_Game.addComponent(pauseEvent);
		entityManager.addEntity(this.stateID, pause_Game);

		PathTileFactory m = new PathTileFactory("path");
		while (m.hasEntitiesLeft()) {
			entityManager.addEntity(this.stateID, m.createEntity());
		}

		TowerTileFactory t = new TowerTileFactory(m.getMapArray());
		while (t.hasEntitiesLeft()) {
			entityManager.addEntity(this.stateID, t.createEntity());
		}
		
		TowerFactory f = new TowerFactory("homeTower", new Vector2f(750, 530));
		entityManager.addEntity(this.stateID, f.createEntity());

		Timer timer = new Timer("timer");		
		TimeEvent countdown = new TimeEvent(1000, true);
		countdown.addAction(new Action() {
			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
				((Timer) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE, "timer")).tick();
			}
		});
		timer.addComponent(countdown);
		entityManager.addEntity(this.stateID, timer);
		
		Entity wave = new Entity("wave");
		TimeEvent spiderWaveTimer = new TimeEvent(7000, true);
		spiderWaveTimer.addAction(new Action() {
			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
				Timer timer = (Timer) StateBasedEntityManager.getInstance().getEntity(Towerdefense.GAMEPLAYSTATE,
						"timer");
				WaveFactory w = new WaveFactory("spider", spiderWaveSize, 500);
				entityManager.addEntity(Towerdefense.GAMEPLAYSTATE, w.createEntity());
				timer.setTimer(7000);
			}
		});
		Event spiderWaveClock = new TimeEvent(20000, true);
		spiderWaveClock.addAction(new Action() {
			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
				Long increase = Math.round(Math.log(spiderWaveSize.floatValue()));
				spiderWaveSize += increase.intValue();
			}
		});
		TimeEvent waspWaveTimer = new TimeEvent(7000, true);
		waspWaveTimer.addAction(new Action() {
			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
				WaveFactory w = new WaveFactory("wasp", waspWaveSize, 700);
				entityManager.addEntity(Towerdefense.GAMEPLAYSTATE, w.createEntity());
			}
		});
		Event waspWaveClock = new TimeEvent(25000, true);
		waspWaveClock.addAction(new Action() {
			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
				if(waspWaveSize > 1) {
					Long increase = Math.round(Math.log(waspWaveSize.floatValue()));
					waspWaveSize += increase.intValue();
				}
				else waspWaveSize++;
			}
		});
		wave.addComponent(spiderWaveTimer);
		wave.addComponent(spiderWaveClock);
		wave.addComponent(waspWaveTimer);
		wave.addComponent(waspWaveClock);
		entityManager.addEntity(this.stateID, wave);

		Money money = new Money("money");
		entityManager.addEntity(this.stateID, money);

		Life life = new Life("life");

		entityManager.addEntity(this.stateID, life);

		Entity gameLost = new Entity("gamelost");
		Event death = new LifeOverEvent("lifeover");
		death.addAction(new Action() {
			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta, Component event) {
				JFrame frame = new JFrame("");
				JOptionPane.showMessageDialog(frame, "Schade.", "Spiel verloren", 1);
				sb.enterState(Towerdefense.MAINMENUSTATE);
			}
		});
		gameLost.addComponent(death);
		entityManager.addEntity(this.stateID, gameLost);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
		entityManager.renderEntities(container, game, g);
		g.setFont(new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.BOLD, 20), true));
		g.setColor(Color.white);
		int amount = ((Money) entityManager.getEntity(this.stateID, "money")).getAmount();
		g.drawString("Money: " + amount, 600, 0);
		g.drawString(
				"Next wave in " + ((Timer) entityManager.getEntity(this.stateID, "timer")).getCurrentTimer() / 1000,
				600, 30);
		g.drawString("Life left: " + ((Life) entityManager.getEntity(this.stateID, "life")).getLife(), 600, 60);

		for (Entity entity : entityManager.getEntitiesByState(Towerdefense.GAMEPLAYSTATE)) {
			if (Enemy.class.isInstance(entity)) {
				g.setColor(Color.black);
				g.drawRect(entity.getPosition().x, entity.getPosition().y - 30, ((Enemy) entity).getMaxLife() * 3, 5);
				g.setColor(Color.green);
				if(((Enemy) entity).getIceHit()) g.setColor(Color.blue);
				g.fillRect(entity.getPosition().x, entity.getPosition().y - 30, ((Enemy) entity).getLife() * 3, 5);
			}
			if (entity.getID() == "towertile" && ((TowerTile)entity).isVisible()) {
				g.setFont(new TrueTypeFont(new java.awt.Font("Verdana",java.awt.Font.PLAIN, 15), true));
				g.setColor(Color.white);
				g.drawString(""+Towerdefense.bulletTower[0], entity.getPosition().x - 30, entity.getPosition().y + 20);
				g.drawString(""+Towerdefense.iceTower[0], entity.getPosition().x + 15, entity.getPosition().y + 20);
			}
		}
		g.setColor(Color.white);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		entityManager.updateEntities(container, game, delta);
	}

	@Override
	public int getID() {
		return stateID;
	}
}
