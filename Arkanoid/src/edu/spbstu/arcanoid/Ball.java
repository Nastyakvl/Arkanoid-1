package edu.spbstu.arcanoid;
import java.awt.Graphics;
import java.awt.Point;

public class Ball {
	public static int standartRadius=12;
	private Point pos = new Point(0,0);
	private int radius;
	private Game instance;
	
	public Ball(Game inst,int x, int y, int radius){
		pos=new Point(x,y);
		this.radius=radius;
		instance=inst;
		
	}
	
	public void render(Graphics g){
		
		g.fillOval(pos.x, pos.y, radius*2, radius*2);
	}
	
	public void moveOnX(int speed){
		pos.x+=speed;
		if(pos.x<Bat.standartBatWidth/2-radius)pos.x=Bat.standartBatWidth/2-radius;
		if(pos.x>instance.getGameDimension().width-radius-Bat.standartBatWidth/2)pos.x=instance.getGameDimension().width-radius-Bat.standartBatWidth/2;
	}
	

}
