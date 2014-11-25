package ch.ksimlee.it.game3b;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Sound {
	
	public static final String SHOT = "shot.mp3";
	
	public static final String EXPLOSION = "explosion.mp3";
	
	private Player player = null;
	
	/**
	 * Create a new Sound (can be played multiple times).
	 * 
	 * @param filename
	 *            The filename of the MP3 file.
	 */
	public Sound(String filename) {
		
		try {
			player = new Player(new FileInputStream(filename));
			
		} catch (FileNotFoundException e) {
			player = null;
			e.printStackTrace();
		} catch (JavaLayerException e) {
			player = null;
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Play this sound.
	 */
	public void play() {
		
		if (player != null) {

			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						player.play();
					} catch (JavaLayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		
		}
	}

}