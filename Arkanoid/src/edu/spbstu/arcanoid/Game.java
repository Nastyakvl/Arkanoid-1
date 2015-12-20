package edu.spbstu.arcanoid;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class Game extends JPanel {

	private static final long serialVersionUID = 377834890210055936L;
	private Dimension sideBar = new Dimension (100, 500);
	private Dimension gameField = new Dimension(800, 500);
	private static Bat bat;
	private static Ball ball;
	private Platform[][] platforms;
	private int score = 0;
	private static boolean isRunning = false;
	private static boolean isPaused = false;

	public Game(Frame container, int platformOnX, int platformOnY) {
		container.addKeyListener(new KeyCatch());
	
		platforms= new Platform[platformOnX][platformOnY];
		for (int x=0; x!=platforms.length; x++)
			for (int y=0; y!=platforms[0].length; y++){
				int pWidth=(gameField.width)/platforms.length;
				int pHeight=(gameField.height/3)/platforms[0].length;
				platforms[x][y]=new Platform(x*pWidth+5, y*pHeight+3,pWidth-10,pHeight-25);
			}
		
		bat = new Bat(this, (gameField.width - Bat.standartBatWidth) / 2, (gameField.height - Bat.standartBatHeight),
				Bat.standartBatWidth, Bat.standartBatHeight);
		ball = new Ball(this, (gameField.width - Ball.standartRadius * 2) / 2,
				(gameField.height - Bat.standartBatHeight - Ball.standartRadius * 2), Ball.standartRadius);
		score = 0;

	}

	public Dimension getGameDimension() {
		return gameField;
	}

	public void setBat(Bat bat) {
		Game.bat = bat;
	}

	public Bat getBat() {
		return Game.bat;
	}

	public void setBall(Ball ball) {
		Game.ball = ball;
	}
	
	public Platform[][] getPlatforms(){
		return this.platforms;
	}

	public void setSize(Dimension size) {
		super.setSize(size);
		gameField = new Dimension(size.width - 200, size.height - 200);
	}

	public void start() {
		isPaused = false;
		if (!isRunning) {
			gameThread.start();
		}
	}

	public void pause() {
		isPaused = true;
	}

	public void stop() {
		isRunning = false;
	}

	public void loseBall() {
		pause();
		Bat tempBat = new Bat(this, (gameField.width - Bat.standartBatWidth) / 2,
				(gameField.height - Bat.standartBatHeight), Bat.standartBatWidth, Bat.standartBatHeight);
		this.setBat(tempBat);
		Ball tempBall = new Ball(this, (gameField.width - Ball.standartRadius * 2) / 2,
				(gameField.height - Bat.standartBatHeight - Ball.standartRadius * 2), Ball.standartRadius);
		this.setBall(tempBall);

	}
	
	public void addScore(int score){
	
			this.score += score;
	}

	private Thread gameThread = new Thread(new Runnable() {
		public void run() {
			isRunning = true;
			while (isRunning) {
				repaint();
				if (!isPaused) {
					ball.moveOnXY();
					try {
						Thread.sleep(4);
					} catch (Exception e) {
					}
				}
			}
		}
	});

	public void paint(Graphics g) {
		super.paint(g);

		g.translate((getWidth() - gameField.width) / 2, (getHeight() - gameField.height) / 2);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, gameField.width, gameField.height);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, gameField.width, gameField.height);

		g.setColor(Color.GRAY);
		bat.render(g);
		g.setColor(Color.BLACK);
		ball.render(g);
		
		
		for (Platform[] pls: platforms){
			for (Platform p: pls){
				g.setColor(new Color(71,84,175));
				p.render(g);
			}
		}
		
		//g.translate((getWidth() + gameField.width) / 2, (getHeight() + gameField.height) / 2);
		g.setColor(Color.YELLOW);
		g.fillRect(gameField.width, 0, sideBar.width, sideBar.height);
		g.setColor(Color.BLACK);
		g.drawRect(gameField.width, 0, sideBar.width, sideBar.height);
		
		g.setFont(new Font("Arial",Font.BOLD, 15));
		
		g.drawString("Score: "+score, gameField.width + 15, 20);
		g.drawString("Lives ", gameField.width + 15, 60);
			
	}

	private class KeyCatch extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			if (!isRunning || isPaused) {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					bat.moveOnX(10);
					ball.moveOnX(10);
				}
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					bat.moveOnX(-10);
					ball.moveOnX(-10);
				}
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					start();
				}
				repaint();
			} else {
				if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					bat.moveOnX(40);
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					bat.moveOnX(-40);
				repaint();
				
			}
		}
	}
}
