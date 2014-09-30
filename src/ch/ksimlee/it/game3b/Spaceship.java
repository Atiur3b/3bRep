package ch.ksimlee.it.game3b;


/**
 * This is a spaceship that will be controlled by the player.
 */
public class Spaceship extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "spaceship.png";
	
	private static final int zIndex = 100;

	public Spaceship(int x, int y) {
		super(x, y, zIndex, FILENAME);
	}

}