package ui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

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
import eea.engine.component.Component;
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
    
    MainMenuState( int stateID ) {
       this.stateID = stateID;
       this.entityManager = StateBasedEntityManager.getInstance();
    }

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
		// Hintergrundbild laden
		//if(!Tanks.debug) 
		setBackground();
		
    	// Lege eine MenuEntryFactory an
		MenuEntryFactory m;
		int counter = 0;
		
		// Neues Spiel starten
		Action new_game = new ChangeStateInitAction(Towerdefense.GAMEPLAYSTATE);
		m = new MenuEntryFactory("Neues Spiel starten", container, new_game, start_position+distance*counter,true);
		entityManager.addEntity(this.stateID, m.createEntity());
		counter++;
		
		// Spielstand laden
		Action load_game = new Action() {
			@Override
			public void update(GameContainer gc, StateBasedGame sb, int delta,
					Component event) {
				
				JFileChooser fc = new JFileChooser("saves/");
				fc.setFileFilter( new FileFilter() {
					@Override public boolean accept( File f ) {
						return f.isDirectory() || f.getName().toLowerCase().endsWith( ".towerdefense" );
					}
					@Override public String getDescription() {
						return "Towerdefense-Spielstaende";
					}
				});
				int state = fc.showOpenDialog( null );
			    
			    if( state == JFileChooser.APPROVE_OPTION ) {
			    	//File file = fc.getSelectedFile();
			    	//Path.getInstance().load(file.getPath());
			    	sb.enterState(Towerdefense.GAMEPLAYSTATE);
					try {
						sb.init(gc);
					} catch (SlickException e) {
						e.printStackTrace();
					}
			    }
				
			}			
		};
		m = new MenuEntryFactory("Spielstand laden", container, load_game, start_position+distance*counter,true);
		entityManager.addEntity(this.stateID, m.createEntity());
		counter++;
		
		// Einstellungen
		Action switch_to_settings = new ChangeStateAction(Towerdefense.OPTIONSTATE);
		m = new MenuEntryFactory("Einstellungen", container, switch_to_settings, start_position+distance*counter,true);
		entityManager.addEntity(this.stateID, m.createEntity());
		counter++;
		
		// Beenden
		Action exit = new QuitAction();
		m = new MenuEntryFactory("Beenden", container, exit, start_position+distance*counter,true);
		entityManager.addEntity(this.stateID, m.createEntity());
		counter++;
		
		
		Entity new_Game = new Entity("newGame");
		Event keyEvent = new KeyPressedEvent(Input.KEY_N);
    	keyEvent.addAction(new ChangeStateInitAction(Towerdefense.GAMEPLAYSTATE));
    	
    	new_Game.addComponent(keyEvent);
    	entityManager.addEntity(stateID, new_Game);
	}
	
	private void setBackground() throws SlickException {
		// Hintergrund laden
		Entity background = new Entity("background");	// Entitaet fuer Hintergrund
		background.setPosition(new Vector2f(400,300));	// Startposition des Hintergrunds
		background.addComponent(new ImageRenderComponent(new Image("/assets/menu.png"))); // Bildkomponente

		// Hintergrund-Entitaet an StateBasedEntityManager uebergeben
		StateBasedEntityManager.getInstance().addEntity(stateID, background);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		entityManager.renderEntities(container, game, g);
		
		g.setFont(new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.BOLD,32), true));

		int counter = 0;
		
		g.drawString("Hauptmenü", 70, start_position-100);
		g.setFont(new TrueTypeFont(new java.awt.Font("Verdana", java.awt.Font.BOLD,16), true));

		g.drawString("Neues Spiel", 130, start_position+counter*distance-10); counter++;
		g.drawString("Spielstand laden", 130, start_position+counter*distance-10); counter++;
		g.drawString("Einstellungen", 130, start_position+counter*distance-10); counter++;
		g.drawString("Beenden", 130, start_position+counter*distance-10); counter++;
		
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		entityManager.updateEntities(container, game, delta);
	}

	@Override
	public int getID() {
		return stateID;
	}


}
