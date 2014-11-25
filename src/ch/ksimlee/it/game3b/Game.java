package ch.ksimlee.it.game3b;

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
	
	private final Spaceship spaceship;
	private final hindernis hindernis;
	public static int aliencounter = 0;
	private static int life = 0;
	public Game() {
		
		Log.info("Starting a game with " + ACTIONS_PER_SECOND + " actions/second.");
		
		// Create the spaceship.
		spaceship = new Spaceship(200, 200);
		hindernis = new hindernis(300, 200);
		
		// Add the spaceship to the list of renderable objects.
		objectsToRender.add(spaceship);
		/*
		for (int i = 0; i< 600; i += 100){
			objectsToRender.add(new hindernis (700 , i));
		}
		*/
		for (int i = 0; i < 600; i += 100) {
			objectsToRender.add(new Alien(i, 0));
			aliencounter ++;
		}
		
	
		
		Log.info("Game initialized.");
	}

	@Override
	public void run() {
		
		while (true) {
			// This loop goes forever, since we don't want our game
			// logic to stop.
			
			// TODO: Add game mechanics here.
			
			
			
		
			Math.random();
			objectsToRender.add(new hindernis (700 , (int)(Math.random()*600.0f)));
			
			
			
			// Update all game objects.
			for (RenderObject object : objectsToRender) {
				object.update(this);
			}
			
			objectsToRender.removeAll(objectsToRemove);
			objectsToRender.addAll(objectsToAdd);
			
			objectsToAdd.clear();
			objectsToRemove.clear();
			
			
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
	
	public Set<RenderObject> getObjectsToRender() {
		return objectsToRender;
	}

	public InputHandler getInputHandler() {
		return inputHandler;
	}
}