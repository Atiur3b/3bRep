package ch.ksimlee.it.game3b;

import ch.ksimlee.it.game3b.Game;

public class Alien extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "birdy.png";
	
	private static final int zIndex = 100;
	
	private int speed = (int) (((int) (Math.random()*5)) + 0.5);

	public Alien(int x, int y) {
		super(x, y, zIndex, true, FILENAME);
		
		collisionTargets.add(Shot.class);
		collisionTargets.add(Spaceship.class);
		collisionTargets.add(Spaceship2.class);
	}
	
	

	@Override
	public void update(Game game) {
		RenderObject collison = move( 0 , speed, game.getObjectsToRender());
		
		if (collison instanceof Spaceship) {
			game.getObjectsToRemove().add(this);
			game.getObjectsToAdd().add(new Explosion(collison));
			Spaceship spaceship = (Spaceship) collison;
			spaceship.handleCollision(true);
		}
		if (collison instanceof Spaceship2) {
			game.getObjectsToRemove().add(this);
			game.getObjectsToAdd().add(new Explosion(collison));
			Spaceship2 spaceship2 = (Spaceship2) collison;
			spaceship2.handleCollision(true);
		}
		
	}

}
