package edu.spbstu.arcanoid;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class Game extends JPanel {

	private static final long serialVersionUID = 377834890210055936L;
	private Dimension gameField = new Dimension(800, 500);
	private static Bat bat;
	private static Ball ball;
	private Platform[][] platforms;
	private boolean isRunning = false;
	private boolean isPaused = false;
	public boolean won=false; 
	private boolean lost=false;
	private int ballCount;
	
	public Game(Frame container, int platformOnX, int platformOnY) {
		container.addKeyListener(new KeyCatch());
	
		platforms= new Platform[platformOnX][platformOnY];
		for (int x=0; x!=platforms.length; x++)
			for (int y=0; y!=platforms[0].length; y++){
				int pWidth=(gameField.width)/platformOnX;
				int pHeight=(gameField.height/3)/platformOnY;
				platforms[x][y]=new Platform(x*pWidth+5, y*pHeight+3,pWidth-10,pHeight-25);
			}
		
		bat = new Bat(this, (gameField.width - Bat.standartBatWidth) / 2, (gameField.height - Bat.standartBatHeight),
				Bat.standartBatWidth, Bat.standartBatHeight);
		ball = new Ball(this, (gameField.width - Ball.standartRadius * 2) / 2,
				(gameField.height - Bat.standartBatHeight - Ball.standartRadius * 2), Ball.standartRadius);

		ballCount=3;
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
		ballCount--;
		if (ballCount<=0)lost=true;
		Bat tempBat = new Bat(this, (gameField.width - Bat.standartBatWidth) / 2,
				(gameField.height - Bat.standartBatHeight), Bat.standartBatWidth, Bat.standartBatHeight);
		this.setBat(tempBat);
		Ball tempBall = new Ball(this, (gameField.width - Ball.standartRadius * 2) / 2,
				(gameField.height - Bat.standartBatHeight - Ball.standartRadius * 2), Ball.standartRadius);
		this.setBall(tempBall);

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
		
		if (won){
			g.setFont(new Font("Arial",Font.BOLD, 30));
			g.drawString("You won!", gameField.width/2-40, gameField.height/2);
			stop();
		}
		if (lost){
			g.setFont(new Font("Arial",Font.BOLD, 30));
			g.drawString("You lost!", gameField.width/2-40, gameField.height/2);
			stop();
		}
		
			
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
					bat.moveOnX(10);
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					bat.moveOnX(-10);
				repaint();
				
			}
		}
	}

	public void playerWon() {
		won=true;
	}
}
