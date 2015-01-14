package ch.ksimlee.it.game3b;

import java.awt.Color;
import java.awt.Graphics;

public class StringObject extends RenderObject {

	private String content;

	public StringObject(int x, int y, int zIndex, String content) {
		super(x, y, zIndex, false);
		this.content = content;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	public void setContent(String content) {
		this.content = content;}
	
	
	
	public void render(Graphics g) {
		// TODO set color
		g.setColor(Color.WHITE);
		g.drawString(content, x, y);
		

	}

}
