package ch.ksimlee.it.game3b;

import java.awt.event.KeyEvent;
import java.util.Set;

import ch.ksimlee.it.game3b.InputHandler;


public class HindernisOben extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "RoehreOben.png";
	
	private static final int zIndex = 100;
	
	private int speed = -5;

	public HindernisOben (int x, int y) {
		super(x, y, zIndex,true, FILENAME);
	}
	
	

	public HindernisOben (hindernis hindernis) {
		this(0,0);
		
		x = hindernis.x;
		y = hindernis.y - 200 - getHeight();
		collisionTargets.add(Spaceship.class);
	}
	
	@Override
	public void update(Game game) {
		
		// Check if we need to move left.
		
		
		RenderObject collision = move(speed, 0, game.getObjectsToRender());
		
		if (collision != null) {
			if (collision instanceof Spaceship) {
				
				game.getObjectsToRemove().add(this);
				game.getObjectsToAdd().add(new Explosion(collision));
				Spaceship spaceship = (Spaceship) collision;
				spaceship.handleCollision(true);
			}
			
			
				
		
		// Check if we need to move right.
		
		}
	}
}

