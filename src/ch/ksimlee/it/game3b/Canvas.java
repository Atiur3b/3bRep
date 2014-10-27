package ch.ksimlee.it.game3b;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import ch.ksimlee.it.game3b.Log;
import ch.ksimlee.it.game3b.RenderObject;

@SuppressWarnings("serial")
public class Canvas extends JPanel {

	// This variable stores the size of the canvas (in pixel).
	private Dimension SIZE = new Dimension(640, 480);

	// This variable stores with how many frames per second (FPS) the canvas
	// should be redrawn.
	private static final int FPS = 30;
	
	/** Should the FPS be shown? */
	private boolean showFPS = true;
	
	/** The actual FPS from the last secocnd. */
	private int lastFPS = 0;
	
	/** The number of frames that have been rendered in this second (wall-clock). */
	private int framesRenderedThisSecond = 0;
	
	/**
	 * The second that is currently considered to be active, i.e., we add frames
	 * to the counter as long as this second does not change.
	 */
	private long activeSecond = -1;
	
	/** The game object. */
	private final Game game;
	
	public Canvas(Game game) {
		setPreferredSize(SIZE);
		
		this.game = game;
		
		Log.info("Initialized canvas.");

		/*
		 * The class timer (from swing) can be used to call a function
		 * periodically. The first argument specifies the frequency in
		 * milliseconds with which the function will be called, and the second
		 * argument an object of type "ActionListener" from which the function
		 * "actionPerformed" will be called with the specified frequency.
		 */
		Timer timer = new Timer((int) (1000.0 / FPS), new ActionListener() {

			// This function is called with the specified frequency.
			@Override
			public void actionPerformed(ActionEvent e) {
				// Repaint the canvas object.
				Canvas.this.repaint();
			}
		});

		// As the timer should immediately start to work, we start it here.
		timer.start();

		Log.info("Drawing timer started.");
	}

	// This function draws this canvas (it draws the JPanel).
	public void paintComponent(Graphics g) {

		Log.debug("Painting the canvas.");

		// Let the swing framework do it's drawing of the JPanel first.
		super.paintComponent(g);

		// The following two commands draw the background:

		// Set the color of the "pen". This color will be used in the following
		// drawing commands.
		g.setColor(Color.white);

		// Draw a rectangle with the size of the canvas. Therefore, this draws a
		// "background".
		g.fillRect(0, 0, SIZE.width, SIZE.height);

		// Get all objects, and sort them according to ther zIndex.
		List<RenderObject> gameObjects = new ArrayList<RenderObject>(game.getObjectsToRender());
		Collections.sort(gameObjects);
		
		// Render all objects.
		for (RenderObject object : gameObjects) {
			object.render(g);
		}
		
		// Calculate the actual FPS.
		updateFps();

		if (showFPS) {
			g.setColor(Color.black);
			g.drawString("FPS: " + lastFPS, SIZE.width - 60, 20);
		}
		
	}
	
	/**
	 * This function counts the actually rendered FPS.
	 */
	private void updateFps() {
		
		// Get the current time in seconds since 1.1.1970.
		long now = System.currentTimeMillis() / 1000;
		
		if (now > activeSecond) {
			// The second switched.
			
			// Store the FPS that we counted.
			lastFPS = framesRenderedThisSecond;
			
			// Reset the counter.
			framesRenderedThisSecond = 0;
			
			// Update the second.
			activeSecond = now;
			
		} else {
			// We are still in the same second (wall-clock).
			
			// Increase the counter by one.
			framesRenderedThisSecond++;
		}
		
	}

}