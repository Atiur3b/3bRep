package ch.ksimlee.it.game3b;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import com.sun.org.apache.bcel.internal.generic.CPInstruction;

import ch.ksimlee.it.game3b.Canvas;
import ch.ksimlee.it.game3b.InputHandler;
import ch.ksimlee.it.game3b.Log;


/**
 * This class can be extended by classes that can render themselves on the
 * {@link Canvas}.
 */
public abstract class RenderObject implements Comparable<RenderObject> {
	
	/** Should the bounding boxes of objects be drawn? */
	public static boolean SHOW_BOUNDING_BOX = true;
	
	/** The X coordinate of this render object. */
	protected int x;
	
	/** The Y coordinate of this render object. */
	protected int y;
	/** Can other objects collide with this object? */
	protected boolean hasCollision = false;
	
	/**
	 * The zIndex is responsible for how much in front the object is drawn. The
	 * higher this index is, the more on top of the image will the object be
	 * drawn.
	 */
	protected int zIndex;
	
	/**
	 * Create a new render object with coordinates.
	 * 
	 * @param x
	 *            The initial X coordinate.
	 * @param y
	 *            The initial Y coordinate.
	 * @param zIndex
	 *            The initial zIndex of the object.
	 * @param collision
	 *            Can other objects collide with this object?
	 *           
	 */
	public RenderObject(int x, int y, int zIndex, boolean collision) {
		this.x = x;
		this.y = y;
		this.zIndex = zIndex;
		this.hasCollision = collision;
	}
	
	/**
	 * Update this object based on the current user input.
	 * 
	 * @param currentInput
	 *            The current user input.
	 * @param allObjects
	 *            All objects that currently exist.
	 * 
+	 * @return True, iff there was a collision.
+	 */
	public boolean move(int dx, int dy, Set<RenderObject> allObjects) {
    // Did we encounter a collision during the movement?
		boolean collision = false;
		
		if (hasCollision) {
			// We need to check for collision.
			
			// Create a set for all possible collision targets.
			Set<RenderObject> collisionTargets = new HashSet<RenderObject>();
			
			// Add all _other_ objects that have collision.
			for (RenderObject object : allObjects) {
				
				if (object != this && object.hasCollision) {
					collisionTargets.add(object);
				}
			}
			
			// Simple algorithm:
			
			// 1. Calculate the number of movement steps.
			int steps = Math.max(Math.abs(dx), Math.abs(dy));
			
			// Calculate the speed in the two dimensions.
			// This can be fractions, thus we need to use floats.
			float speedX = ((float) dx) / steps;
			float speedY = ((float) dy) / steps;
			
			// As we are working with floats for the speed, we need
			// to use floats for the position too.
			float positionX = this.x;
			float positionY = this.y;
			
			// 2. Move "step by step" into the desired direction.
			for (int step = 0; step < steps; step++) {
				
				// Perform the next step.
				positionX += speedX;
				positionY += speedY;
				
				// Update the position of this object.
				this.x = Math.round(positionX);
				this.y = Math.round(positionY);
				
				// Check if there is a collision now.
				for (RenderObject object : collisionTargets) {
					
					if (overlapsWithObject(object)) {
						// There is a collision!
						collision = true;
						
						// Exit the loop of checking for collisions directly.
						break;
					}
				}
				
				if (collision) {
					// There was a collision!
					
					// Move one step back, to the last position, because
					// there was no collision there.
					positionX -= speedX;
					positionY -= speedY;
					
					// Set the positions to this last position.
					this.x = Math.round(positionX);
					this.y = Math.round(positionY);
					
					// Exit the moving loop, since we have a collision and we+					// cannot move further.
					break;
				}
				
			}
			
			
		} else {
			// This object has no collision, just update the coordinates.

			this.x += dx;
			this.y += dy;
		}
		
		if (collision) {
			Log.info("There was a collision!");
		}
		
		return collision;
	}	
	/**
	 * Check if the bounding box of this rectangle overlaps with the bounding
	 * box of another object.
	 * 
	 * @param other
	 *            The other object.
	 * @return True, iff the objects overlap.
	 */
	public void update(InputHandler currentInput, Set<RenderObject> allObjects) {
		// Default: Do nothing
	}
	
	/**
	 * Move this object.
	 * 
	 * @param dx
	 *            Delta x to move.
	 * @param dy
	 *            Delta y to move.
     * @param allObjects
	 *            All objects that currently exist.
	 */
	private boolean overlapsWithObject(RenderObject other) {
				return (x < other.x + other.getWidth() &&
				        x + getWidth() > other.x &&
				        y < other.y + other.getHeight() &&
				        y + getHeight()  > other.y);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		return false;
	}

	public abstract int getWidth();
	public abstract int getHeight();
	
	
		/**
		* Internal function to render objects.
		* 
		* @param g
		*            The graphics context to render on.
		*/
		public final void renderInternal(Graphics g) {

			// Perform the actual rendering.
			render(g);

			// Perform the debug rendering.

			if (SHOW_BOUNDING_BOX) {

				// Store the current color.
				Color color = g.getColor();
				
				g.setColor(Color.magenta);
				g.drawRect(x, y, getWidth(), getHeight());

				// Restore the color.
				g.setColor(color);
	}
		}

	/**
	
	/**
	 * Render this object on the Canvas' graphic area.
	 * 
	 * @param g
	 *            The graphics object to render itself on.
	 */
	public abstract void render(Graphics g);
	
	@Override
	public int compareTo(RenderObject o) {
		return zIndex - o.zIndex;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getzIndex() {
		return zIndex;
	}

	public void setzIndex(int zIndex) {
		this.zIndex = zIndex;
	}

}