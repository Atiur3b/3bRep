package ch.ksimlee.it.game3b;
// Hier werden Alle benutzten sachen importiert
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;



public class Game implements Runnable {
	
	private static final int ACTIONS_PER_SECOND = 30;



	/** A list of all objects that can be rendered. */
	private final Set<RenderObject> objectsToRender = new HashSet<RenderObject>();
	
	private final Set<RenderObject> objectsToAdd = new HashSet<RenderObject>();

	public Set<RenderObject> getObjectsToAdd() {
		return objectsToAdd;
	}

	public Set<RenderObject> getObjectsToRemove() {
		return objectsToRemove;
	}

	private final Set<RenderObject> objectsToRemove = new HashSet<RenderObject>();

	
	/** The handler that should receive the user input. */
	private final InputHandler inputHandler = new InputHandler();
	
	private BG bg;
	private Spaceship spaceship;
	boolean PlayerTwo = true;
	private static int players = 2;
	private Spaceship2 spaceship2;
	private hindernis hindernis;
	private HindernisOben hindernisoben;
	public static int points = 0;
	public static int rekord = 0;
	public static int aliencounter = 0;
	private static int hindernisCounter = 100;
	public StringObject REKORD;
	public StringObject RESET;
	public StringObject P2;
	public StringObject CREDITS;
	public StringObject punkte1;
	public StringObject punkte2;
	
	public Game() {
		
		Log.info("Starting a game with " + ACTIONS_PER_SECOND + "action");
		initialize();
	    }
		
	private void initialize() {
		// Create 
			spaceship = new Spaceship(200, 200);
			bg = new BG (0, 0);
			spaceship2 = new Spaceship2(300, 200);
			hindernis = new hindernis(300, 200);
			hindernisoben = new HindernisOben (100, 100);
		// Text
			punkte1 = new StringObject(50, 50, 1000, "Punkte Spieler1:  "+ points);
			punkte2 = new StringObject(600, 50, 1000, "Punkte Spieler2: "+ points);
			RESET = new StringObject(350, 695, 1000, "R : RESET");
			REKORD = new StringObject(450, 695, 1000, "Rekord : " + rekord);
			P2 = new StringObject(50, 695, 1000, " #2 : Player 2 Ausschalten");
			CREDITS = new StringObject(560, 695, 1000, "Spiel von Niklaus Burri und Ati Arain");
		
			// Add  to the list of renderable objects.
			objectsToRender.add(bg);
			objectsToRender.add(spaceship);
			objectsToRender.add(spaceship2);
		
		// Aliens werden automtisch gespawned*
			for (int i = 0; i < 600; i += 100) {
				objectsToRender.add(new Alien(i, 0));
				aliencounter ++;
			}
		// Hier werden Alle Textanzeigen Aktiv
			getObjectsToAdd().add(punkte1);
			getObjectsToAdd().add(punkte2);
			getObjectsToAdd().add(RESET);
			getObjectsToAdd().add(REKORD);
			getObjectsToAdd().add(CREDITS);
			getObjectsToAdd().add(P2);
			
			
			
		
			Log.info("Game initialized.");
	}
	
	@Override
	public void run() {
		
		
		while (true) {
		// This loop goes forever, since we don't want our game logic to stop
			
			if (PlayerTwo == false){
				objectsToRemove.add(spaceship2);
				objectsToRemove.add(punkte2);
			}
			
		// Gameover wenn alle Spieler Tod sind
			if (Gameover.loosers >= players){
				getObjectsToAdd().add(new Gameover(180, 250));
				Gameover.loosers = 0;
				}
			if (PlayerTwo == false){
				players = 1;
			}
			
		// Neustart wir bei R Taste eingeleitet (Funktion Unten)
			if (inputHandler.isKeyPressed(KeyEvent.VK_R)){
				reset();
			}
			
		// Switch bei Drücken der "2" Taste damit 2 Player INaktiv wird
			if (inputHandler.isKeyPressed(KeyEvent.VK_2)){
				PlayerTwo = false;
			}
			
			final int y = 300 + (int)(Math.random()*300.0f);
			hindernisCounter --;
		// Spawn von neuen Aliens damit kein Mangel
			if (hindernisCounter <= 0) {
				hindernis h = new hindernis (800 , y);
				objectsToRender.add(h);
				objectsToRender.add(new HindernisOben (h));
				hindernisCounter = 100;
				for (int i = 0; i < 4; i ++){
				objectsToRender.add(new Alien((int) (Math.random()*800) , 100));
				}
			}
			
			
		
			
			// Update all game objects.
			for (RenderObject object : objectsToRender) {
				object.update(this);
			}
			
			objectsToRender.removeAll(objectsToRemove);
			objectsToRender.addAll(objectsToAdd);
			
			objectsToAdd.clear();
			objectsToRemove.clear();
			
			//sets a record that is not cleared after reset()
			if (Shot.points1 > Shot.points2){
				rekord = Shot.points1;
			}else{ rekord = Shot.points2;}
			
			REKORD.setContent("Rekord: "+ rekord);
			
			int alienCounter = 0;
			
			for (RenderObject object : objectsToRender) {
				if (object instanceof Alien) {
					alienCounter++;
				}
			}
			
			if (alienCounter < 5){
				Math.random();
				for (int i = 0; i < (int) (Math.random()*5); i += 5){
				objectsToRender.add(new Alien((int) (Math.random()*700) , 100));
				}
			}
			// Update the input state.
			inputHandler.updatedReleasedKeys();
			
			// Delay the next action (iteration of the loop).
			try {
				Thread.sleep((long) (1000.0f/ACTIONS_PER_SECOND));
				
			} catch (InterruptedException e) {
				// We were interrupted, exit the game loop.
				
				Log.error("Caught interruption exception, exiting game loop.");
				e.printStackTrace();
				break;
			}
			
			}
		
	}
	
	public void reset(){
		objectsToRender.clear();
		points = 0;
		Shot.points1 = 0;
		Shot.points2 = 0;
		Spaceship.lifecounter = 5;
		Spaceship2.lifecounter2 = 5;
		PlayerTwo = true;
		
		initialize();
	}	
	
	public Set<RenderObject> getObjectsToRender() {
		return objectsToRender;
	}

	public InputHandler getInputHandler() {
		return inputHandler;
	}

	public void increasePoints() {
	
		
	}
}