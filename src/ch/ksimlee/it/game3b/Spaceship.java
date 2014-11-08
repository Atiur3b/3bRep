package ch.ksimlee.it.game3b;

import java.awt.event.KeyEvent;
import java.util.Set;
import ch.ksimlee.it.game3b.Game;
import ch.ksimlee.it.game3b.InputHandler;

/**
 * This is a spaceship that will be controlled by the player.
 */
public class Spaceship extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "spaceship.png";
	
	private static final int zIndex = 100;
	
	private int speed = 10;
	
	private static final int shotDelay = 5;	
	private int shotTimeout = 0; 

	public Spaceship(int x, int y) {
		super(x, y, zIndex, true, FILENAME);
	}

	@Override
	public void update(Game game) {
				
		// Check if we need to move up.
		
		move(0, 3, game.getObjectsToRender()); {
			Game.collision = true;
		}
		
		if (game.getInputHandler().isKeyPressed(KeyEvent.VK_UP) ||
				game.getInputHandler().isKeyPressed(KeyEvent.VK_W)) {
			
			move(0, -speed, game.getObjectsToRender());
			Game.collision =true;
		}
		
		if (game.getInputHandler().isKeyPressed(KeyEvent.VK_LEFT) ||
				game.getInputHandler().isKeyPressed(KeyEvent.VK_S)) {
			
			move(-speed, 0, game.getObjectsToRender());
			Game.collision =true;
		}
		
		if (game.getInputHandler().isKeyPressed(KeyEvent.VK_RIGHT) ||
				game.getInputHandler().isKeyPressed(KeyEvent.VK_D)) {
			
			move(speed, 0, game.getObjectsToRender());
			Game.collision =true;
		}
		
		// Check if we need to shoot.
				if (game.getInputHandler().isKeyPressed(KeyEvent.VK_SPACE) &&
						shotTimeout == 0) {
				
			game.getObjectsToAdd().add(new Shot(this));
		
			shotTimeout = shotDelay;
			
		
		
		}
				if (shotTimeout > 0) {
						shotTimeout--;
	}
}
}

