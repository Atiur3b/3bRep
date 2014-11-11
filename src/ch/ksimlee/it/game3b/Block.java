package ch.ksimlee.it.game3b;

public class Block extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "spaceship.png";
	
	private static final int zIndex = 100;

	public Block(int x, int y) {
		super(x, y, zIndex,true, FILENAME);
	}

}