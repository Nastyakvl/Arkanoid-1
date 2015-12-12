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
	
	public void moveOnXY(int dx, int dy){
		int i=0;
//		while (i!=15) {
//			
//			i+=1;
			//if (isCollideOX()) {
		if (pos.x >= instance.getGameDimension().width-2*radius )
				dx=-dx;
		if (pos.x <= 0 ) dx=-dx;
			//}
			//if (isCollideOY()){
		if (pos.y >= instance.getGameDimension().height-radius )
				dy=-dy;
		if (pos.y <=0 )
			dy=-dy;
		
			
			//}
			pos.x += dx;
			pos.y += dy;
	//	}
	}
//	public boolean isCollideOX(){
//		if (pos.x >= instance.getGameDimension().width-radius || pos.x <= 0){
//			return true;
//		}
//		return false;
//	}
//	public boolean isCollideOY(){
//		if (pos.y >= (instance.getBat().getHitBox().y-radius) && 
//				(pos.x >= instance.getBat().getHitBox().x &&
//				pos.x < instance.getBat().getHitBox().width )
//				|| pos.y <= 0){
//			return true;
//		}
//		return false;
//	}
	}
	
	
