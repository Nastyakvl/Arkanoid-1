package edu.spbstu.arcanoid;
import java.awt.Graphics;
import java.awt.Point;

public class Ball {
	public static int standartRadius=12;
	private Point pos = new Point(0,0);
	private int radius;
	
	public Ball(int x, int y, int radius){
		pos=new Point(x,y);
		this.radius=radius;
		
	}
	
	public void render(Graphics g){
		
		g.fillOval(pos.x, pos.y, radius*2, radius*2);
	}
	

}
