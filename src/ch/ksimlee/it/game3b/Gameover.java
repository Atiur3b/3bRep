package ch.ksimlee.it.game3b;
import ch.ksimlee.it.game3b.Game;

public class Gameover extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "gameover.png";
	
	private static final int zIndex = 500;
	
	

	public Gameover(int x, int y) {
		super(x, y, zIndex, true, FILENAME);
	}

	public Gameover(RenderObject collision) {
		this(300,300);
		/*
		x = collision.getCenterX() - getWidth() / 2;
		y = collision.y - getHeight();
		*/
		
	}

	@Override
	public void update(Game game) {
		
		
		if (y <= -getHeight()) {
			// The shot is outside of the visible area.
			game.getObjectsToRemove().add(this);
		}
	}
}