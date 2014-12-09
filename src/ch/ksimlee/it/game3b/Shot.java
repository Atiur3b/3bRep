package ch.ksimlee.it.game3b;

import ch.ksimlee.it.game3b.Game;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import ch.ksimlee.it.game3b.Sound;

public class Shot extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "shot.png";
	private static int points = 0;
	private static final int zIndex = 100;
	private StringObject punkte;
	
	private int speed = 20;

	public Shot(int x, int y) {
		super(x, y, zIndex, true, FILENAME);
	}

	public Shot(Spaceship spaceship) {
		this(0,0);
		
		x  = spaceship.getCenterX() - getWidth() / 2;
		y = spaceship.y - getHeight();
		
		new Sound(Sound.SHOT).play();
		
		collisionTargets.add(Alien.class);
		
		punkte = new StringObject(50, 100, 1000, "Punkte "+ points);
	}

	@Override
	public void update(Game game) {
		
		punkte.setContent("Punkte"+ points);
		game.getObjectsToAdd().add(punkte);
		
		RenderObject collision = move(0, -speed, game.getObjectsToRender());

		if (collision != null) {
			if (collision instanceof Alien) {
				game.getObjectsToRemove().add(collision);
				game.getObjectsToRemove().add(this);
				game.getObjectsToAdd().add(new Explosion(collision));
				points++;
				
				game.increasePoints();
				
			}
			
		}
		
		if (y <= -getHeight()) {
			// The shot is outside of the visible area.
			game.getObjectsToRemove().add(this);
		}
	}
}