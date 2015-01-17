package ch.ksimlee.it.game3b;

import ch.ksimlee.it.game3b.Game;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import ch.ksimlee.it.game3b.Sound;

public class Shot extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "Shot.png";
	public static int points1 = 0;
	public static int points2 = 0;
	private static final int zIndex = 100;
	public static int wer = 0;
	
	
	
	private int speed = 20;

	public Shot(int x, int y) {
		super(x, y, zIndex, true, FILENAME);
	}

	public Shot(Spaceship spaceship) {
		this(0,0);
		
		x  = spaceship.getCenterX() - getWidth() / 2;
		y = spaceship.y - getHeight();
		
		new Sound(Sound.SHOT).play();
		wer = 0;
		collisionTargets.add(Alien.class);	
	}

	public Shot(Spaceship2 spaceship2) {
		this(5,5);
		
		x  = spaceship2.getCenterX() - getWidth() / 2;
		y = spaceship2.y - getHeight();
		
		new Sound(Sound.SHOT).play();
		wer = 1;
		collisionTargets.add(Alien.class);
	}
	


	@Override
	public void update(Game game) {
		
		game.punkte1.setContent("Punkte Spieler 1: "+ points1);
		game.punkte2.setContent("Punkte Spieler 2: "+ points2);
		
		RenderObject collision = move(0, -speed, game.getObjectsToRender());
if (wer == 0){
		if (collision != null) {
			if (collision instanceof Alien) {
				game.getObjectsToRemove().add(collision);
				game.getObjectsToRemove().add(this);
				game.getObjectsToAdd().add(new Explosion(collision));
				points1++;
				
				game.increasePoints();
				
			}	
		}
}
if (wer == 1){
		if (collision != null) {
			if (collision instanceof Alien) {
				game.getObjectsToRemove().add(collision);
				game.getObjectsToRemove().add(this);
				game.getObjectsToAdd().add(new Explosion(collision));
				points2++;
				
				game.increasePoints();
				
			}
		}
}
		if (y <= -getHeight()) {
			// The shot is outside of the visible area.
			game.getObjectsToRemove().add(this);
		}
	}
}