package ch.ksimlee.it.game3b;

import java.util.HashSet;
import java.util.Set;

import ch.ksimlee.it.game3b.Log;
import ch.ksimlee.it.game3b.RenderObject;
import ch.ksimlee.it.game3b.Spaceship;
import ch.ksimlee.it.game3b.hindernis;

public class Game implements Runnable {
	
	private static final int ACTIONS_PER_SECOND = 30;
	
	/** A list of all objects that can be rendered. */
	private final Set<RenderObject> objectsToRender = new HashSet<RenderObject>();
	
	/** The handler that should receive the user input. */
	private final InputHandler inputHandler = new InputHandler();
	
	private final Spaceship spaceship;
	private final hindernis hindernis;
	private final Spaceship gameo;
	public static boolean collision = false;
	private static int life = 3;
	private static int saver = 300;
	
	public Game() {
		
		Log.info("Starting a game with " + ACTIONS_PER_SECOND + " actions/second.");
		
		// Create the spaceship.
		spaceship = new Spaceship(100, 300);
		gameo = new Spaceship(400,400);
		hindernis = new hindernis(500 , 300);
		// Add the spaceship to the list of renderable objects.
		objectsToRender.add(spaceship);
		objectsToRender.add(hindernis);
		
		
		Log.info("Game initialized.");
	}

	@Override
	public void run() {
		
		while (true) {
			// This loop goes forever, since we don't want our game
			// logic to stop.
			
			// TODO: Add game mechanics here.
			
			// XXX: Example
			spaceship.update(inputHandler, objectsToRender);
			hindernis.update(inputHandler, objectsToRender);
			saver --;
			
			if (saver < 1)
				if (collision)
					life = life -1;
			if (life == 0)
				objectsToRender.add(gameo);
				
					
			
			
			// Update all game objects.
			for (RenderObject object : objectsToRender) {
				object.update(inputHandler, objectsToRender);
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