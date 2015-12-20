package edu.spbstu.arcanoid; 
import java.awt.Graphics;
import java.awt.Rectangle;



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
	
	public boolean collidesWith(Rectangle obj){
		return hitBox.intersects(obj);
	}
	
	public int getX(){
		return hitBox.x;
	}
	
	public int getY(){
		return hitBox.y;
	}
	
}
