package edu.spbstu.arcanoid; 
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;

import javax.swing.JPanel;


public class Bat {
	

	public static int standartBatWidth = 160;//100;
	public static int standartBatHeight = 23;//14;
	private Rectangle hitBox;
	private Game instance;
	
	public Bat(Game inst,int x, int y, int width, int height){
		
		hitBox = new Rectangle(x, y, width, height);
		instance=inst;

	}
	
	public void render(Graphics g) {
		
		g.fillRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
	}
	
	
	public void moveOnX(int speed){
		hitBox.x+=speed;
		if (hitBox.x<0)hitBox.x=0;
		if (hitBox.x>instance.getGameDimension().width-hitBox.width) hitBox.x=instance.getGameDimension().width-hitBox.width;
		
	}
	
	public Rectangle getHitBox(){
		return hitBox;
		
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
