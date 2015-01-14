package ch.ksimlee.it.game3b;




public class HindernisOben extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "RoehreOben.png";
	
	private static final int zIndex = 100;
	
	public HindernisOben (int x, int y) {
		super(x, y, zIndex,true, FILENAME);
	}
	
	

	public HindernisOben (hindernis hindernis) {
		this(0,0);
		
		x = hindernis.x;
		y = hindernis.y - 200 - getHeight();
		collisionTargets.add(Spaceship.class);
		collisionTargets.add(Spaceship2.class);
	}
	
	@Override
	public void update(Game game) {
		
		// Check if we need to move left.
		
		// Speed refers to speed in hindernis
		RenderObject collision = move(hindernis.speed, 0, game.getObjectsToRender());
		
		if (collision != null) {
			if (collision instanceof Spaceship) {
			//Explodes after spaceshiphit
				game.getObjectsToRemove().add(this);
				game.getObjectsToAdd().add(new Explosion(collision));
				Spaceship spaceship = (Spaceship) collision;
				spaceship.handleCollision(true);
			}
			
			if (collision instanceof Spaceship2) {
				
				game.getObjectsToRemove().add(this);
				game.getObjectsToAdd().add(new Explosion(collision));
				Spaceship2 spaceship2 = (Spaceship2) collision;
				spaceship2.handleCollision(true);
			}
			
			
				
		
		// Check if we need to move right.
		
		}
	}
}

