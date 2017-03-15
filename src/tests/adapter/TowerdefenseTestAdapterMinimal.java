package tests.adapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import ui.Towerdefense;
import eea.engine.entity.Entity;
import eea.engine.entity.StateBasedEntityManager;
import eea.engine.test.TestAppGameContainer;
import model.entities.Tower;

/**
 * This is the test adapter for the minimal stage of completion. You <b>must</b> implement the method stubs and match
 * them to your concrete implementation. Please read all the Javadoc of a method before implementing it. <br>
 * <strong>Important:</strong> this class should not contain any real game logic, you should rather only match the
 * method stubs to your game. <br>
 * Example: in {@link #isCorrectMap()} you may return the value <i>Towerdefense.isCorrectMap()</i>, if you have a variable
 * <i>Towerdefense</i> and a map has before been loaded via {@link #loadMapFromFile(File)}. What you mustn't do is to
 * implement the actual logic of the method in this class. <br>
 * <br>
 * If you have implemented the minimal stage of completion, you should be able to implement all method stubs. The public
 * and private JUnit tests for the minimal stage of completion will be run on this test adapter. The other test adapters
 * will inherit from this class, because they need the basic methods (like loading a map), too. <br>
 * <br>
 * The methods of all test adapters need to function without any kind of user interaction.</br>
 * 
 * <i>Note:</i> All other test adapters will inherit from this class.
 * 
 * @see TowerdefenseTestAdapterExtended1
 * @see TowerdefenseTestAdapterExtended2
 * @see TowerdefenseTestAdapterExtended3
 */
public class TowerdefenseTestAdapterMinimal {

	Towerdefense Towerdefense; 						// erbt von StateBasedGame
	TestAppGameContainer app;			// spezielle Variante des AppGameContainer,
										// welche keine UI erzeugt (nur fÃ¼r Tests!)
	boolean syntaxException;			// gibt es Syntax-Fehler
	boolean semanticException;			// gibt es Semantik-Fehler
	
	/**
	 * Verwenden Sie diesen Konstruktor zur Initialisierung von allem,
	 * was sie benoetigen
	 * 
	 * Use this constructor to set up everything you need.
	 */
	public TowerdefenseTestAdapterMinimal() {
		super();
		Towerdefense = null;
		syntaxException = true;
		semanticException = true;
	}
	
	/* *************************************************** 
	 * ********* initialize, run, stop the game **********
	 * *************************************************** */
	
	public StateBasedGame getStateBasedGame() {
		return Towerdefense;
	}
	
	/**
	 * Diese Methode initialisiert das Spiel im Debug-Modus, d.h. es wird
	 * ein AppGameContainer gestartet, der keine Fenster erzeugt und aktualisiert.
	 * 
	 * Sie mï¿½ssen diese Methode erweitern
	 */
	public void initializeGame() {
		
		// Setze den library Pfad abhaengig vom Betriebssystem
    	if(System.getProperty("os.name").toLowerCase().contains("windows")) {
			System.setProperty("org.lwjgl.librarypath",System.getProperty("user.dir") + "/lib/lwjgl-2.8.3/native/windows");
		} 
		else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
			System.setProperty("org.lwjgl.librarypath",System.getProperty("user.dir") + "/lib/lwjgl-2.8.3/native/macosx");
		}
		else {
			System.setProperty("org.lwjgl.librarypath",System.getProperty("user.dir") + "/lib/lwjgl-2.8.3/native/" +System.getProperty("os.name").toLowerCase());
		}
    	// Initialisiere das Spiel Towerdefense im Debug-Modus (ohne UI-Ausgabe)
		Towerdefense = new Towerdefense();
		
		try {
			app = new TestAppGameContainer(Towerdefense);
			app.start(0);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Stoppe das im Hintergrund laufende Spiel
	 */
	public void stopGame() {
		if (app != null) {
			app.exit();
			app.destroy();
		}
		StateBasedEntityManager.getInstance().clearAllStates();
		Towerdefense = null;
	}
	
	/**
	 * Run the game for a specified duration.
	 * Laesst das Spiel fuer eine angegebene Zeit laufen
	 * 
	 * @param ms duration of runtime of the game
	 */
	public void runGame(int ms) {
		if (Towerdefense != null && app != null) {
			try {
				app.updateGame(ms);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/* *************************************************** 
	 * ******************* Tupel Tower ********************
	 * *************************************************** */
	
	/**
	 * @param position : Tower
	 * @return Name des Towers mit der uebergebenen Position
	 */
	public String getTowername(int position) {
		
		if(Towerdefense==null)
			System.err.println("The game Towerdefense has not been initialized, Towerdefense is null!");
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		
		List<Tower> tower = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(Tower.class.isInstance(entity)) {
				tower.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > tower.size() - 1)
			return null;
		
		String towerName = tower.get(position).getID();
		
		return towerName;
	}
	
	/**
	 * @param name : Towername
	 * @return Stärke des Towers mit der übergebenen Towernummer
	 */
	public int getTowerStrength(String name) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		
		Tower tower = null;
		
		for (Entity entity : entities) {
			if(entity.getID() == name) {
				tower = (Tower) entity;
			}
		}
		
		if (tower == null)
			return -1;
		
		return tower.getStrength();
	}
	
	/**
	 * @param position : Spielernummer
	 * @return Aktuelle Lebenspunkte des Panzers mit der uebergebenen Spielernummer
	 */
	public int getTankActualLife(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> Towerdefense = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tank")) {
				Towerdefense.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > Towerdefense.size() - 1)
			return -1;
		
		return Towerdefense.get(position).getActualLife();
	}
	
	/* *************************************************** 
	 * ******************* Tupel Shot ********************
	 * *************************************************** */
	
	/**
	 * @param position : Spielernummer
	 * @return Maximale Schussanzahl des Panzers mit der uebergebenen Spielernummer
	 */
	public int getTankMaxShot(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> Towerdefense = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tank")) {
				Towerdefense.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > Towerdefense.size() - 1)
			return -1;
		
		return Towerdefense.get(position).getMaxShootAmmo();
	}
	
	/**
	 * @param position : Spielernummer
	 * @return Aktuelle Schussanzahl des Panzers mit der uebergebenen Spielernummer
	 */
	public int getTankActualShot(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> Towerdefense = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tank")) {
				Towerdefense.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > Towerdefense.size() - 1)
			return -1;
		
		return Towerdefense.get(position).getActualShootAmmo();
	}
	
	/* *************************************************** 
	 * ******************* Tupel Mine ********************
	 * *************************************************** */
	
	/**
	 * @param position : Spielernummer
	 * @return Maximale Minenanzahl des Panzers mit der uebergebenen Spielernummer
	 */
	public int getTankMaxMine(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> Towerdefense = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tank")) {
				Towerdefense.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > Towerdefense.size() - 1)
			return -1;
		
		return Towerdefense.get(position).getMaxMinesAmmo();
	}
	
	/**
	 * @param position : Spielernummer
	 * @return Aktuelle Minenanzahl des Panzers mit der uebergebenen Spielernummer
	 */
	public int getTankActualMine(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> Towerdefense = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tank")) {
				Towerdefense.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > Towerdefense.size() - 1)
			return -1;
		
		return Towerdefense.get(position).getActualMinesAmmo();
	}
	
	/**
	 * @param position : Spielernummer
	 * @return Staerke des Schusses des Panzers mit der uebergebenen Spielernummer
	 */
	public int getTowerdefensetrength(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> Towerdefense = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tank")) {
				Towerdefense.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > Towerdefense.size() - 1)
			return -1;
		
		return Towerdefense.get(position).getStreangth();
	}
	
	/**
	 * @param position : Spielernummer
	 * @return Geschwindigkeit des Panzers mit der uebergebenen Spielernummer
	 */
	public int getTowerdefensepeed(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> Towerdefense = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tank")) {
				Towerdefense.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > Towerdefense.size() - 1)
			return -1;
		return (int) (Towerdefense.get(position).getSpeed() * 100);
	}
	
	/**
	 * @param position : Spielernummer
	 * @return Aktuelle Rotation in Grad des Panzers mit der uebergebenen Spielernummer
	 */
	public int getTankRotation(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> Towerdefense = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tank")) {
				Towerdefense.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > Towerdefense.size() - 1)
			return -1;
		
		return (int) Towerdefense.get(position).getRotation();
	}
	
	/**
	 * @param position : Spielernummer
	 * @return Aktuelle Skalierung des Panzers mit der uebergebenen Spielernummer
	 */
	public int getTowerdefensecale(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> Towerdefense = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tank")) {
				Towerdefense.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > Towerdefense.size() - 1)
			return -1;
		return (int) (Towerdefense.get(position).getScale() * 100);
	}
	
	/**
	 * @param position : Spielernummer
	 * @return Aktuelle X-Koordinate des Panzers mit der uebergebenen Spielernummer
	 */
	public int getTankXPosition(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> Towerdefense = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tank")) {
				Towerdefense.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > Towerdefense.size() - 1)
			return -1;
		
		return (int) Towerdefense.get(position).getPosition().x;
	}
	
	/**
	 * @param position : Spielernummer
	 * @return Aktuelle Y-Koordinate des Panzers mit der uebergebenen Spielernummer
	 */
	public int getTankYPosition(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Tower> Towerdefense = new ArrayList<Tower>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tank")) {
				Towerdefense.add((Tower) entity);
			}
		}
		
		if (position < 0 || position > Towerdefense.size() - 1)
			return -1;
		
		return (int) Towerdefense.get(position).getPosition().y;
	}
	
	/**
	 * @return Anzahl an Panzern im laufenden Spiel
	 */
	public int getTankCount() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		int count = 0;
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Tank")) {
				
				count++;
			}
		}
		return count;
	}
	
	/* *************************************************** 
	 * ****************** Tupel Border *******************
	 * *************************************************** */

	/**
	 * Border ist die Bezeichnung eine unbesiegbare Wand mit einem Mittelpunkt (x|y) und
	 * einer Hoehe und Breite
	 * @param Bordernummer
	 * @return Aktuelle x-Koordinate der Border mit der uebergebenen Bordernummer
	 */
	public int getBorderXPosition(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Border> borders = new ArrayList<Border>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Border")) {
				borders.add((Border) entity);
			}
		}
		
		if (position < 0 || position > borders.size() - 1)
			return -1;
		
		return (int) borders.get(position).getPosition().x;
	}
	
	/**
	 * Border ist die Bezeichnung eine unbesiegbare Wand mit einem Mittelpunkt (x|y) und
	 * einer Hoehe und Breite
	 * @param Bordernummer
	 * @return Aktuelle y-Koordinate der Border mit der uebergebenen Bordernummer
	 */
	public int getBorderYPosition(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Border> borders = new ArrayList<Border>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Border")) {
				borders.add((Border) entity);
			}
		}
		
		if (position < 0 || position > borders.size() - 1)
			return -1;
		
		return (int) borders.get(position).getPosition().y;
	}
	
	/**
	 * Border ist die Bezeichnung eine unbesiegbare Wand mit einem Mittelpunkt (x|y) und
	 * einer Hoehe und Breite
	 * @param Bordernummer
	 * @return Aktuelle Hoehe der Border mit der uebergebenen Bordernummer
	 */
	public int getBorderXSize(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Border> borders = new ArrayList<Border>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Border")) {
				borders.add((Border) entity);
			}
		}
		
		if (position < 0 || position > borders.size() - 1)
			return -1;
		
		return (int) borders.get(position).getSize().x;
	}
	
	/**
	 * Border ist die Bezeichnung eine unbesiegbare Wand mit einem Mittelpunkt (x|y) und
	 * einer Hoehe und Breite
	 * @param Bordernummer
	 * @return Aktuelle Breite der Border mit der uebergebenen Bordernummer
	 */
	public int getBorderYSize(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Border> borders = new ArrayList<Border>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Border")) {
				borders.add((Border) entity);
			}
		}
		
		if (position < 0 || position > borders.size() - 1)
			return -1;
		
		return (int) borders.get(position).getSize().y;
	}
	
	/**
	 * @return Anzahl an Borders im laufenden Spiel
	 */
	public int getBorderCount() {
		
		List<Entity> entities;
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		int count = 0;
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Border")) {
				count++;
			}
		}
		return count;
	}
	
	/* *************************************************** 
	 * ******************* Tupel Wall ********************
	 * *************************************************** */

	/**
	 * Wall ist die Bezeichnung eine zerstoerbare Wand mit einem Mittelpunkt (x|y) und
	 * einer definierten Skalierung
	 * @param Wall-Nummer
	 * @return Maximale Lebenspunkte der Wall mit der uebergebenen Wall-Nummer
	 */
	public int getWallMaxLife(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Wall> walls = new ArrayList<Wall>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Wall")) {
				walls.add((Wall) entity);
			}
		}
		
		if (position < 0 || position > walls.size() - 1)
			return -1;
		
		return walls.get(position).getMaxLife();
	}
	
	/**
	 * Wall ist die Bezeichnung eine zerstoerbare Wand mit einem Mittelpunkt (x|y) und
	 * einer definierten Skalierung
	 * @param Wall-Nummer
	 * @return Aktuelle Lebenspunkte der Wall mit der uebergebenen Wall-Nummer
	 */
	public int getWallActualLife(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Wall> walls = new ArrayList<Wall>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Wall")) {
				walls.add((Wall) entity);
			}
		}
		
		if (position < 0 || position > walls.size() - 1)
			return -1;
		
		return walls.get(position).getActualLife();
	}
	
	/**
	 * Wall ist die Bezeichnung eine zerstoerbare Wand mit einem Mittelpunkt (x|y) und
	 * einer definierten Skalierung
	 * @param Wall-Nummer
	 * @return Aktuelle Rotation in Grad der Wall mit der uebergebenen Wall-Nummer
	 */
	public int getWallRotation(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Wall> walls = new ArrayList<Wall>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Wall")) {
				walls.add((Wall) entity);
			}
		}
		
		if (position < 0 || position > walls.size() - 1)
			return -1;
		
		return (int) walls.get(position).getRotation();
	}
	
	/**
	 * Wall ist die Bezeichnung eine zerstoerbare Wand mit einem Mittelpunkt (x|y) und
	 * einer definierten Skalierung
	 * @param Wall-Nummer
	 * @return Aktuelle Skalierung der Wall mit der uebergebenen Wall-Nummer
	 */
	public int getWallScale(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Wall> walls = new ArrayList<Wall>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Wall")) {
				walls.add((Wall) entity);
			}
		}
		
		if (position < 0 || position > walls.size() - 1)
			return -1;
		
		return (int) (walls.get(position).getScale() * 100);
	}
	
	/**
	 * Wall ist die Bezeichnung eine zerstoerbare Wand mit einem Mittelpunkt (x|y) und
	 * einer definierten Skalierung
	 * @param Wall-Nummer
	 * @return Aktuelle x-Koordinate der Wall mit der uebergebenen Wall-Nummer
	 */
	public int getWallXPosition(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Wall> walls = new ArrayList<Wall>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Wall")) {
				walls.add((Wall) entity);
			}
		}
		
		if (position < 0 || position > walls.size() - 1)
			return -1;
		
		return (int) walls.get(position).getPosition().x;
	}
	
	/**
	 * Wall ist die Bezeichnung eine zerstoerbare Wand mit einem Mittelpunkt (x|y) und
	 * einer definierten Skalierung
	 * @param Wall-Nummer
	 * @return Aktuelle y-Koordinate der Wall mit der uebergebenen Wall-Nummer
	 */
	public int getWallYPosition(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();

		
		List<Wall> walls = new ArrayList<Wall>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Wall")) {
				walls.add((Wall) entity);
			}
		}
		
		if (position < 0 || position > walls.size() - 1)
			return -1;
		
		return (int) walls.get(position).getPosition().y;
	}
	
	/**
	 * @return Anzahl an Walls im aktuellen Spiel
	 */
	public int getWallCount() {
		List<Entity> entities;
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		

		int count = 0;
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Wall")) {
				count++;
			}
		}
		return count;
	}
	
	/* *************************************************** 
	 * ******************** Tupel Shot ********************
	 * *************************************************** */
	
	/**
	 * Shot ist die Bezeichnung eine Schuss mit einem Mittelpunkt (x|y) und
	 * einer definierten Skalierung
	 * @param Schuss-Nummer
	 * @return StÃ¤rke des Schusses mit der uebergebenen Schuss-Nummer
	 */
	public int getShotStrength(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		List<Shoot> shots = new ArrayList<Shoot>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Shot")) {
				shots.add((Shoot) entity);
			}
		}
		
		if (position < 0 || position > shots.size() - 1)
			return -1;
		
		return shots.get(position).getStreangth();
	}
	
	/**
	 * Shot ist die Bezeichnung eine Schuss mit einem Mittelpunkt (x|y) und
	 * einer definierten Skalierung
	 * @param Schuss-Nummer
	 * @return Aktuelle Rotation in Grad des Schusses mit der uebergebenen Schuss-Nummer
	 */
	public int getShotRotation(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		List<Shoot> shots = new ArrayList<Shoot>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Shot")) {
				shots.add((Shoot) entity);
			}
		}
		
		if (position < 0 || position > shots.size() - 1)
			return -1;
		
		return (int) shots.get(position).getRotation();
	}
	
	/**
	 * Shot ist die Bezeichnung eine Schuss mit einem Mittelpunkt (x|y) und
	 * einer definierten Skalierung
	 * @param Schuss-Nummer
	 * @return Aktuelle Skalierung des Schusses mit der uebergebenen Schuss-Nummer
	 */
	public int getShotScale(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		List<Shoot> shots = new ArrayList<Shoot>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Shot")) {
				shots.add((Shoot) entity);
			}
		}
		
		if (position < 0 || position > shots.size() - 1)
			return -1;
		
		return (int) (shots.get(position).getScale() *100);
	}
	
	/**
	 * Shot ist die Bezeichnung eine Schuss mit einem Mittelpunkt (x|y) und
	 * einer definierten Skalierung
	 * @param Schuss-Nummer
	 * @return Aktuelle x-Koordinate des Schusses mit der uebergebenen Schuss-Nummer
	 */
	public int getShotXPosition(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		List<Shoot> shots = new ArrayList<Shoot>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Shot")) {
				shots.add((Shoot) entity);
			}
		}
		
		if (position < 0 || position > shots.size() - 1)
			return -1;
		
		return (int) shots.get(position).getPosition().x;
	}
	
	/**
	 * Shot ist die Bezeichnung eine Schuss mit einem Mittelpunkt (x|y) und
	 * einer definierten Skalierung
	 * @param Schuss-Nummer
	 * @return Aktuelle y-Koordinate des Schusses mit der uebergebenen Schuss-Nummer
	 */
	public int getShotYPosition(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		List<Shoot> shots = new ArrayList<Shoot>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Shot")) {
				shots.add((Shoot) entity);
			}
		}
		
		if (position < 0 || position > shots.size() - 1)
			return -1;
		
		return (int) shots.get(position).getPosition().y;
	}
	
	/**
	 * @return Anzahl an Schuessen im laufenden Spiel
	 */
	public int getShotCount() {
		
		List<Entity> entities;
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		int count = 0;
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Shot")) {
				count++;
			}
		}
		return count;
	}
	
	/* *************************************************** 
	 * **************** Tupel Explosion ******************
	 * *************************************************** */
	
	/**
	 * Explosion ist die Bezeichnung eine Explosion mit einem Mittelpunkt (x|y) und
	 * einer definierten Hoehe und Breite
	 * @param Explosion-Nummer
	 * @return Aktuelle Breite der Explosion mit der uebergebenen Explosion-Nummer
	 */
	public int getExplosionWidth(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		List<Explosion> explosions = new ArrayList<Explosion>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Explosion")) {
				explosions.add((Explosion) entity);
			}
		}
		
		if (position < 0 || position > explosions.size() - 1)
			return -1;
		
		return (int) explosions.get(position).getWidth();
	}
	
	/**
	 * Explosion ist die Bezeichnung eine Explosion mit einem Mittelpunkt (x|y) und
	 * einer definierten Hoehe und Breite
	 * @param Explosion-Nummer
	 * @return Aktuelle Hoehe der Explosion mit der uebergebenen Explosion-Nummer
	 */	
	public int getExplosionHeight(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		List<Explosion> explosions = new ArrayList<Explosion>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Explosion")) {
				explosions.add((Explosion) entity);
			}
		}
		
		if (position < 0 || position > explosions.size() - 1)
			return -1;
		
		return (int) explosions.get(position).getHeight();
	}
	
	/**
	 * Explosion ist die Bezeichnung eine Explosion mit einem Mittelpunkt (x|y) und
	 * einer definierten Hoehe und Breite und einer Explosionsgeschwindigkeit
	 * @param Explosion-Nummer
	 * @return Geschwindigkeit der Explosion mit der uebergebenen Explosion-Nummer
	 */
	public int getExplosionSpeed(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		List<Explosion> explosions = new ArrayList<Explosion>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Explosion")) {
				explosions.add((Explosion) entity);
			}
		}
		
		if (position < 0 || position > explosions.size() - 1)
			return -1;
		
		return (int) (explosions.get(position).getSpeed() * 100);
	}
	
	/**
	 * Explosion ist die Bezeichnung eine Explosion mit einem Mittelpunkt (x|y) und
	 * einer definierten Hoehe und Breite und einer Explosionsgeschwindigkeit
	 * @param Explosion-Nummer
	 * @return Aktuelle x-Koordinate der Explosion mit der uebergebenen Explosion-Nummer
	 */
	public int getExplosionXPosition(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		List<Explosion> explosions = new ArrayList<Explosion>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Explosion")) {
				explosions.add((Explosion) entity);
			}
		}
		
		if (position < 0 || position > explosions.size() - 1)
			return -1;
		
		return (int) explosions.get(position).getPosition().x;
	}
	
	/**
	 * Explosion ist die Bezeichnung eine Explosion mit einem Mittelpunkt (x|y) und
	 * einer definierten Hoehe und Breite und einer Explosionsgeschwindigkeit
	 * @param Explosion-Nummer
	 * @return Aktuelle y-Koordinate der Explosion mit der uebergebenen Explosion-Nummer
	 */
	public int getExplosionYPosition(int position) {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		List<Explosion> explosions = new ArrayList<Explosion>();
		
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Explosion")) {
				explosions.add((Explosion) entity);
			}
		}
		
		if (position < 0 || position > explosions.size() - 1)
			return -1;
		
		return (int) explosions.get(position).getPosition().y;
	}
	
	/**
	 * @return Anzahl an Explosionen im laufenden Spiel
	 */
	public int getExplosionCount() {
		
		List<Entity> entities = new ArrayList<Entity>();
		
		if (Towerdefense != null) 
			entities = StateBasedEntityManager.getInstance().getEntitiesByState(Towerdefense.GAMEPLAYSTATE);
		else
			entities = Map.getInstance().getEntities();
		
		int count = 0;
		for (Entity entity : entities) {
			if(entity.toString().startsWith("Explosion")) {
				count++;
			}
		}
		return count;
	}
	
	/* *************************************************** 
	 * ********************** Input **********************
	 * *************************************************** */
	
	/**
	 * This Method should emulate the key down event.
	 * This should make the playerTowershoot.
	 * 
	 * Diese Methode emuliert das Druecken beliebiger Tasten.
	 * (Dies soll es ermoeglichen, das Schiessen des Spielerpanzers
	 * zu testen)
	 * 
	 * @param updatetime : Zeitdauer bis update-Aufruf
	 * @param input : z.B. Input.KEY_K, Input.KEY_L
	 */
	public void handleKeyDown(int updatetime, Integer input) {
		if (Towerdefense != null && app != null) {
			app.getTestInput().setKeyDown(input);
			try {
				app.updateGame(updatetime);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This Method should emulate the key pressed event.
	 * This should make the playerTowershoot.
	 * 
	 * Diese Methode emuliert das Druecken beliebiger Tasten.
	 * (Dies soll es ermoeglichen, das Schiessen des Spielerpanzers
	 * zu testen)
	 * 
	 * @param updatetime : Zeitdauer bis update-Aufruf
	 * @param input : z.B. Input.KEY_K, Input.KEY_L
	 */
	public void handleKeyPressed(int updatetime, Integer input) {
		if (Towerdefense != null && app != null) {
			app.getTestInput().setKeyPressed(input);
			try {
				app.updateGame(updatetime);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This Method should emulate the pressing of the up arrow key.
	 * (This should make the playerTowerdrive forward.)
	 * 
	 * Diese Methode emuliert das Druecken der 'nach oben'-Taste.
	 * (Dies soll es ermoeglichen, waehrend der Tests Gegenstaende wie
	 * den Panzer zu bewegen)
	 */
	public void handleKeyDownUpArrow() {
		handleKeyDown(1000, Input.KEY_UP);
	}
	
	/**
	 * This Method should emulate the pressing of the down arrow key.
	 * This should make the playerTowerdrive backward.
	 * 
	 * Diese Methode emuliert das Druecken der 'nach unten'-Taste.
	 * (Dies soll es ermoeglichen, waehrend der Tests Gegenstaende wie
	 * den Panzer zu bewegen, in diesem Fall eine Rueckwaertsbewegung)
	 */
	public void handleKeyDownDownArrow() {
		handleKeyDown(1000, Input.KEY_DOWN);
	}
	
	/**
	 * This Method should emulate the pressing of the right arrow key.
	 * This should make the playerTowerturn clockwise.
	 * 
	 * Diese Methode emuliert das Druecken der 'nach rechts'-Taste.
	 * (Dies soll es ermoeglichen, waehrend der Tests Gegenstaende wie
	 * den Panzer zu bewegen, hier eine Drehung nach rechts)
	 */
	public void handleKeyDownRightArrow() {
		handleKeyDown(1000, Input.KEY_RIGHT);
	}
	
	/**
	 * This Method should emulate the pressing of the left arrow key.
	 * This should make the playerTowerturn counter clockwise.
	 * 
	 * Diese Methode emuliert das Druecken der 'nach links'-Taste.
	 * (Dies soll es ermoeglichen, waehrend der Tests Gegenstaende wie
	 * den Panzer zu bewegen, hier eine Drehung nach links)
	 */
	public void handleKeyDownLeftArrow() {
		handleKeyDown(1000, Input.KEY_LEFT);
	}
	
	/**
	 * This Method should emulate the pressing of the k key.
	 * This should make the playerTowershoot.
	 * 
	 * Diese Methode emuliert das Druecken der 'k'-Taste.
	 * (Dies soll es ermoeglichen, das Schiessen des Spielerpanzers
	 * zu testen)
	 */
	public void handleKeyPressK() {
		handleKeyPressed(0, Input.KEY_K);
	}
	
	/**
	 * This Method should emulate the pressing of the n key.
	 * This should start a new game.
	 * 
	 * Diese Methode emuliert das Druecken der 'k'-Taste.
	 * (Dies soll es ermoeglichen, das Spiel neu zu starten)
	 */
	public void handleKeyPressN() {
		handleKeyPressed(0, Input.KEY_N);
	}
}
