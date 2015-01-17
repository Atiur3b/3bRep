package ch.ksimlee.it.game3b;

import java.awt.event.KeyEvent;


import ch.ksimlee.it.game3b.Gameover;
import ch.ksimlee.it.game3b.Game;


/**
 * This is a spaceship that will be controlled by the player.
 */
public class Spaceship extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "spaceship1-2.png";
	
	private static final int zIndex = 100;
	
	private int speed = 15;
	private StringObject leben;
	private static final int shotDelay = 7;	
	private int shotTimeout = 0; 
	public static int lifecounter = 5;
	
	public static int lifesave = 50;
	
	public Spaceship(int x, int y) {
		super(x, y, zIndex, true, FILENAME);
		collisionTargets.add(Alien.class);
		collisionTargets.add(hindernis.class);
		collisionTargets.add(HindernisOben.class);
		leben = new StringObject(50, 20, 1000, "Verbleibende Leben "+ lifecounter);
	}

	@Override
	public void update(Game game) {
				
		if (lifesave >0){
			lifesave --;
		}
				
		// Check if we need to move up.
		
		RenderObject collision = null;
		
		collision = move(0, 5, game.getObjectsToRender());
		handleCollision(collision instanceof Alien);
		
		if (collision != null) {
			if (collision instanceof hindernis) {
				game.getObjectsToRemove().add(collision);
				game.getObjectsToRemove().add(this);
				game.getObjectsToAdd().add(new Explosion(collision));
				
			}
			
		}
		if (y >= 0){
		if (game.getInputHandler().isKeyPressed(KeyEvent.VK_UP)) {
			
			collision = move(0, -speed, game.getObjectsToRender());
			handleCollision(collision instanceof Alien);
		}
		}
		if (x >= 0){
		if (game.getInputHandler().isKeyPressed(KeyEvent.VK_LEFT)) {
			
			collision = move(-speed, 0, game.getObjectsToRender());
			handleCollision(collision instanceof Alien);
		}
		}
		if (x <= 700){
		if (game.getInputHandler().isKeyPressed(KeyEvent.VK_RIGHT)) {
			
			collision = move(speed, 0, game.getObjectsToRender());
			handleCollision(collision instanceof Alien);
		}
		}
		
		if (lifecounter == 0){
			Gameover.loosers ++;
			game.getObjectsToRemove().add(this);
			game.getObjectsToAdd().add(new Explosion(this));
		}		
		leben.setContent("Verbleibende Leben "+ lifecounter);
		game.getObjectsToAdd().add(leben);
		
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
	
	public void handleCollision(boolean hitSomething) {
		
		if (hitSomething) {
			if (lifesave == 0){
			lifecounter --;
			lifesave = 50;
			}
		}
			
			
	}
}

