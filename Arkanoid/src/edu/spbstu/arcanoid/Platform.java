package edu.spbstu.arcanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Platform {
	private boolean isDestroyed = false;
	private Rectangle Box;
	private int value = 0;

	public Platform(int x, int y, int width, int height) {
		Box = new Rectangle(x, y, width, height);
		this.value = 100;

	}

	public boolean collidesWith(Rectangle obj) {
		return (isDestroyed) ? false : Box.intersects(obj);
	}

	public void render(Graphics g) {
		if (!isDestroyed) {
			g.fillRect(Box.x, Box.y, Box.width, Box.height);
			g.setColor(Color.BLACK);
			g.drawRect(Box.x, Box.y, Box.width, Box.height);
		}
	}

	public void destroy() {
		isDestroyed = true;
	}
	
	public int getValue(){
		return this.value;
	}
}
