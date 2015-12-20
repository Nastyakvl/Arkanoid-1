package edu.spbstu.arcanoid;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Ball {
	public static int standartRadius = 12;
	private Point pos = new Point(0, 0);
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

		if (isCollideOX()) {
			movement.x = -movement.x;
		}
		if (isCollideOY()) {
			movement.y = -movement.y;
		}
		Bat b = instance.getBat();
//		if (((pos.x + 2 * radius) >= b.getX())) {
//			movement.y = -movement.y;
//			movement.x = -movement.x;
//		}
		if (isCollideBlock()) {
			movement.y = -movement.y;
		}

		boolean won = true;
		for (Platform[] pls : instance.getPlatforms()) {
			for (Platform p2 : pls) {
				if (!p2.isDestroy())
					won = false;
			}

		}
		if (won){
			instance.playerWon();
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
		if (instance.getBat().collidesWith(new Rectangle(pos.x, pos.y, radius * 2, radius * 2)) || pos.y <= 0) {
			return true;
		}
		if (pos.y + 2 * radius > instance.getGameDimension().height) {
			instance.loseBall();
		}
		return false;
	}

	public boolean isCollideBlock() {
		for (Platform[] pls : instance.getPlatforms()) {
			for (Platform p : pls) {
				if (p.collidesWith(new Rectangle(pos.x, pos.y, radius * 2, radius * 2))) {
					p.destroy();
					instance.addScore(p.getValue());
					return true;
				}
			}
		}
		return false;
	}

}
