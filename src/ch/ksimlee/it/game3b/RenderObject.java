package ch.ksimlee.it.game3b;

import java.awt.Graphics;

import ch.ksimlee.it.game3b.Canvas;
import ch.ksimlee.it.game3b.InputHandler;

/**
 * This class can be extended by classes that can render themselves on the
 * {@link Canvas}.
 */
public abstract class RenderObject implements Comparable<RenderObject> {
	
	/** The X coordinate of this render object. */
	protected int x;
	
	/** The Y coordinate of this render object. */
	protected int y;
	
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
	 */
	public RenderObject(int x, int y, int zIndex) {
		this.x = x;
		this.y = y;
		this.zIndex = zIndex;
	}
	
	/**
	 * Update this object based on the current user input.
	 * 
	 * @param currentInput
	 *            The current user input.
	 */
	public void update(InputHandler currentInput) {
		// Default: Do nothing
	}
	
	/**
	 * Move this object.
	 * 
	 * @param dx
	 *            Delta x to move.
	 * @param dy
	 *            Delta y to move.
	 */
	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		return false;
	}

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