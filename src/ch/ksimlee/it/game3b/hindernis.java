package ch.ksimlee.it.game3b;

import java.awt.event.KeyEvent;
import java.util.Set;

import ch.ksimlee.it.game3b.InputHandler;


public class hindernis extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "birdy.png";
	
	private static final int zIndex = 100;
	
	private int speed = -1;

	public hindernis(int x, int y) {
		super(x, y, zIndex,true, FILENAME);
	}

	public hindernis(hindernis hindernis) {
		this(0,0);
		
		x = hindernis.getCenterX() - getWidth() / 2;
		y = hindernis.y - getHeight();
	}
	
	@Override
	public void update(Game game) {
		
		// Check if we need to move left.
		move( speed , 0, game.getObjectsToRender());
		
		
		RenderObject collision = move(0, 0, game.getObjectsToRender());
		
		if (collision != null) {
			if (collision instanceof Spaceship) {
				game.getObjectsToRemove().add(collision);
				game.getObjectsToRemove().add(this);
				game.getObjectsToAdd().add(new Explosion(collision));
			}
			
		
		
		// Check if we need to move right.
		
		}
	}
}

