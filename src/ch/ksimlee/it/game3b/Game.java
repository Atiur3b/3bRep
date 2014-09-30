package ch.ksimlee.it.game3b;


import java.util.SortedSet;
import java.util.concurrent.ConcurrentSkipListSet;

import ch.ksimlee.it.game3b.Log;
import ch.ksimlee.it.game3b.RenderObject;
import ch.ksimlee.it.game3b.Spaceship;

public class Game implements Runnable {
	
	private static final int ACTIONS_PER_SECOND = 10;
	
	/** A list of all objects that can be rendered. */
	private final SortedSet<RenderObject> objectsToRender = new ConcurrentSkipListSet<RenderObject>();
	
	private final Spaceship spaceship;
	private int 
	
	public Game() {
		
		Log.info("Starting a game with " + ACTIONS_PER_SECOND + " actions/second.");
		
		// Create the spaceship.
		spaceship = new Spaceship(200, 200);
		
		// Add the spaceship to the list of renderable objects.
		objectsToRender.add(spaceship);
		
		Log.info("Game initialized.");
	}

	@Override
	public void run() {
		
		while (true) {
			// This loop goes forever, since we don't want our game
			// logic to stop.
			
			// TODO: Add game mechanics here.
			
			// XXX: Example
			
			spaceship.setX(spaceship.getX() + 5);
			if (spaceship.getX() ==  500) {
				while (spaceship.getX() < 100)
					spaceship.setX(spaceship.getX() - 5);
			}
			
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
	
	public SortedSet<RenderObject> getObjectsToRender() {
		return objectsToRender;
	}
	}

