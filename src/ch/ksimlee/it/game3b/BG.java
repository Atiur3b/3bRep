package ch.ksimlee.it.game3b;

import ch.ksimlee.it.game3b.Game;
import ch.ksimlee.it.game3b.Sound;

public class BG extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "BG.jpg";
	
	private static final int zIndex = 0;
	
	public BG(int x, int y) {
		super(x, y, zIndex, false, FILENAME);
	}
	
	public BG(RenderObject object) {
		this(0,0);
		
		x = object.getCenterX() - getHeight()/2;
		y = object.getCenterY() - getWidth()/2;
		new Sound(Sound.EXPLOSION).play();
	}

	@Override
	public void update(Game game) {
	}
}

