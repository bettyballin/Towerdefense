package ui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import model.factory.MenuEntryFactory;

import eea.engine.action.Action;
import eea.engine.action.basicactions.ChangeStateAction;
import eea.engine.action.basicactions.ChangeStateInitAction;
import eea.engine.action.basicactions.QuitAction;
import eea.engine.component.render.ImageRenderComponent;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.event.Event;
import eea.engine.event.basicevents.KeyPressedEvent;

public class MainMenuState extends BasicGameState {

	private int stateID = 0;
	private StateBasedEntityManager entityManager;

	private final int distance = 100;
	private final int start_position = 200;

	MainMenuState(int stateID) {
		this.stateID = stateID;
		this.entityManager = StateBasedEntityManager.getInstance();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {

		// Hintergrundbild laden
		// if(!Towerdefense.debug)
		setBackground();

		// Lege eine MenuEntryFactory an
		MenuEntryFactory m;
		int counter = 0;

		// Neues Spiel starten
		Action new_game = new ChangeStateAction(Towerdefense.GAMEPLAYSTATE);
		m = new MenuEntryFactory("Neues Spiel starten", container, new_game, start_position + distance * counter, true);
		Entity startGame = m.createEntity();
		entityManager.addEntity(this.stateID, startGame);
		counter++;

		// Einstellungen
		Action switch_to_settings = new ChangeStateAction(Towerdefense.OPTIONSTATE);
		m = new MenuEntryFactory("Einstellungen", container, switch_to_settings, start_position + distance * counter,
				true);
		entityManager.addEntity(this.stateID, m.createEntity());
		counter++;

		// Beenden
		Action exit = new QuitAction();
		m = new MenuEntryFactory("Beenden", container, exit, start_position + distance * counter, true);
		entityManager.addEntity(this.stateID, m.createEntity());
		counter++;

		// ueber Taste N in den Gameplaystate, neues Spiel starten
		Entity keyListener = new Entity("keyListener");
		Event keyN = new KeyPressedEvent(Input.KEY_N);
		keyN.addAction(new ChangeStateInitAction(Towerdefense.GAMEPLAYSTATE));
		keyListener.addComponent(keyN);

		// ueber Taste E in den Optionstate
		Event keyE = new KeyPressedEvent(Input.KEY_E);
		keyE.addAction(new ChangeStateInitAction(Towerdefense.OPTIONSTATE));
		keyListener.addComponent(keyE);

		// ueber Taste ESC beenden
		Event keyESC = new KeyPressedEvent(Input.KEY_ESCAPE);
		keyESC.addAction(new QuitAction());
		keyListener.addComponent(keyESC);

		entityManager.addEntity(stateID, keyListener);
	}

	private void setBackground() throws SlickException {
		// Hintergrund laden
		Entity background = new Entity("background"); // Entitaet fuer
														// Hintergrund
		background.setPosition(new Vector2f(400, 300)); // Startposition des
														// Hintergrunds
		background.addComponent(new ImageRenderComponent(new Image("/assets/background.png"))); // Bildkomponente

		// Hintergrund-Entitaet an StateBasedEntityManager uebergeben
		StateBasedEntityManager.getInstance().addEntity(stateID, background);
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {

		entityManager.renderEntities(container, game, g);
		g.setFont(new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.BOLD, 32), true));

		int counter = 0;

		g.drawString("TowerDefense", 70, start_position - 100);
		g.setFont(new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.PLAIN, 20), true));
		g.drawString("Neues Spiel - N", 130, start_position + counter * distance - 10);
		counter++;
		g.drawString("Einstellungen - E", 130, start_position + counter * distance - 10);
		counter++;
		g.drawString("Beenden - ESC", 130, start_position + counter * distance - 10);
		counter++;

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
