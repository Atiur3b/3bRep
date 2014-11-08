package ch.ksimlee.it.game3b;

import ch.ksimlee.it.game3b.Game;

public class Alien extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "birdy.png";
	
	private static final int zIndex = 100;
	
	private int speed = 3;

	public Alien(int x, int y) {
		super(x, y, zIndex, true, FILENAME);
	}

	@Override
	public void update(Game game) {
		move( 0 , speed, game.getObjectsToRender());
	}

}