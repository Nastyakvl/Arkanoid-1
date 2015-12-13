package edu.spbstu.arcanoid;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class Ball {
	public static int standartRadius = 12;
	private Point pos = new Point(0, 0);
	private Dimension vector = new Dimension(0, 0);
	private Point movement;
	private int radius;

	private Game instance;

	public Ball(Game inst, int x, int y, int radius) {
		pos = new Point(x, y);
		this.radius = radius;
		instance = inst;
		movement = new Point(-1, -1);

	}

	public void render(Graphics g) {

		g.fillOval(pos.x, pos.y, radius * 2, radius * 2);
		
	}

	public void moveOnX(int speed) {
		pos.x += speed;
		if (pos.x < Bat.standartBatWidth / 2 - radius)
			pos.x = Bat.standartBatWidth / 2 - radius;
		if (pos.x > instance.getGameDimension().width - radius - Bat.standartBatWidth / 2)
			pos.x = instance.getGameDimension().width - radius - Bat.standartBatWidth / 2;
	}

	public void moveOnXY() {

		// if (pos.x >= instance.getGameDimension().width - 2 * radius) {
		// movement.x=-movement.x;
		// }
		//
		// if (pos.x <= 0) {
		// movement.x=-movement.x;
		// }
		//
		// if (pos.y >= instance.getGameDimension().height - radius) {
		// movement.y=-movement.y;
		// }
		// if (pos.y <= 0) {
		// movement.y=-movement.y;
		// }
		if (isCollideOX()) {
			movement.x = -movement.x;
		}
		if (isCollideOY()) {
			movement.y = -movement.y;
		}
		if (isCollideSideHitBox()){
			movement.x = -movement.x;
			movement.y = -movement.y;
		}

		pos.x += movement.x;
		pos.y += movement.y;
	}

	public boolean isCollideOX() {
		if (pos.x + 2 * radius >= (instance.getGameDimension().width) || pos.x <= 0) {
			return true;
		}
		return false;
	}

	public boolean isCollideOY() {
		if (((pos.y + 1.999*radius) >= instance.getBat().getHitBox().y)
				&& ((pos.x >= instance.getBat().getHitBox().x)
						&& (pos.x <= (instance.getBat().getHitBox().width + instance.getBat().getHitBox().x)))
				|| pos.y <= 0) {
			return true;
		}
		if (pos.y + 2*radius > instance.getGameDimension().height) {
			instance.loseBall();
		}
		return false;
	}
	public boolean isCollideSideHitBox(){
		if ((pos.y + 2*radius > instance.getBat().getHitBox().y) && 
				(pos.y <= (instance.getBat().getHitBox().y + instance.getBat().getHitBox().height)) &&
				((pos.x == instance.getBat().getHitBox().x) || (pos.x == (instance.getBat().getHitBox().x + instance.getBat().getHitBox().width)))){
			return true;
		}
		return false;
	}

}
