package ch.ksimlee.it.game3b;



public class hindernis extends ImageObject {
	
	/** Path to the image on the filesystem. */
	private static final String FILENAME = "roehre.png";
	
	private static final int zIndex = 100;
	
	public static int speed = -5;
	private int levelup = 0;
	public hindernis(int x, int y) {
		super(x, y, zIndex,true, FILENAME);
	}
	
	

	public hindernis(hindernis hindernis) {
		this(0,0);
		
		x = hindernis.getCenterX() - getWidth() / 2;
		y = hindernis.y - getHeight();
		collisionTargets.add(Spaceship.class);
		collisionTargets.add(Spaceship2.class);
	}
	
	@Override
	public void update(Game game) {
		
		// Speeds up the Tubes within 250 Steps.
		levelup ++;
		if (levelup >= 250){
			speed--;
			levelup = 0;
		}
		RenderObject collision = move(speed, 0, game.getObjectsToRender());
		
		if (collision != null) {
			if (collision instanceof Spaceship) {
				
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

