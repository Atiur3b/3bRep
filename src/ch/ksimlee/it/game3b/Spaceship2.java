package ch.ksimlee.it.game3b;

import java.awt.event.KeyEvent;
import java.util.Set;

import ch.ksimlee.it.game3b.Gameover;
import ch.ksimlee.it.game3b.Game;
import ch.ksimlee.it.game3b.InputHandler;

/**
 * This is a spaceship that will be controlled by the player.
 */
public class Spaceship2 extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "spaceship.png";
	
	private static final int zIndex = 100;
	
	private int speed = 10;
	private StringObject leben;
	private static final int shotDelay = 7;	
	private int shotTimeout = 0; 
	public static int lifecounter = 5;
	
	public static int lifesave = 50;
	
	public Spaceship2(int x, int y) {
		super(x, y, zIndex, true, FILENAME);
		collisionTargets.add(Alien.class);
		collisionTargets.add(hindernis.class);
		collisionTargets.add(HindernisOben.class);
		leben = new StringObject(50, 50, 1000, "Verbleibende Leben "+ lifecounter);
	}

	@Override
	public void update(Game game) {
		
		leben.setContent("Verbleibende Leben "+ lifecounter);
		game.getObjectsToAdd().add(leben);
		
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
		
		if (game.getInputHandler().isKeyPressed(KeyEvent.VK_UP) ||
				game.getInputHandler().isKeyPressed(KeyEvent.VK_W)) {
			
			collision = move(0, -speed, game.getObjectsToRender());
			handleCollision(collision instanceof Alien);
		}
		
		if (game.getInputHandler().isKeyPressed(KeyEvent.VK_LEFT) ||
				game.getInputHandler().isKeyPressed(KeyEvent.VK_S)) {
			
			collision = move(-speed, 0, game.getObjectsToRender());
			handleCollision(collision instanceof Alien);
		}
		
		if (game.getInputHandler().isKeyPressed(KeyEvent.VK_RIGHT) ||
				game.getInputHandler().isKeyPressed(KeyEvent.VK_D)) {
			
			collision = move(speed, 0, game.getObjectsToRender());
			handleCollision(collision instanceof Alien);
			
		}
		
		if (lifecounter == 0){
			game.getObjectsToAdd().add(new Gameover(collision));
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
	
	public void handleCollision(boolean hitSomething) {
		
		if (hitSomething) {
			if (lifesave == 0){
			lifecounter --;
			lifesave = 50;
			}
		}
			
			
	}
}

