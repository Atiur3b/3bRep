package ch.ksimlee.it.game3b;

import java.awt.event.KeyEvent;

import ch.ksimlee.it.game3b.InputHandler;

/**
 * This is a spaceship that will be controlled by the player.
 */
public class Spaceship extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "spaceship.png";
	
	private static final int zIndex = 100;
	
	private int speed = 10;

	public Spaceship(int x, int y) {
		super(x, y, zIndex, FILENAME);
	}

	@Override
	public void update(InputHandler currentInput) {
		
		// Check if we need to move left.
		move(0, 3);
		if (currentInput.isKeyPressed(KeyEvent.VK_UP) ||
				currentInput.isKeyPressed(KeyEvent.VK_W)) {
			
			move(0, -speed);
		}
		
		// Check if we need to move right.
		
		}
	}

