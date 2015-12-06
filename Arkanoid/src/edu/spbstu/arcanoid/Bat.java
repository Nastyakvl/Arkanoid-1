package edu.spbstu.arcanoid; 
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JPanel;


public class Bat {
	
//	private static final int X = 0;
//	private static final int Y = 0;
//	private static final int WIDTH = 20;
//	private static final int HEIGHT = 5;
	public static int standartBatWidth = 160;//100;
	public static int standartBatHeight = 23;//14;
	private Rectangle hitBox;//= new Rectangle(0, 0, 20, 5);
	
	public Bat(int x, int y, int width, int height){
		
		hitBox = new Rectangle(x, y, width, height);

	}
	
	public void render(Graphics g) {
		
		g.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
	}
//	public void paintComponent (Graphics g){
//		//g.create(X, Y, WIDTH, HEIGHT);
//		g.setColor(Color.BLUE);
//		g.fillRect(X, Y, WIDTH, HEIGHT);
//	}
	
//	public void draw () {
//		Graphics g = c.getGraphics();
//		paint(g);
//	}

//	private Graphics create(int x2, int y2, int width2, int height2) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	
}
