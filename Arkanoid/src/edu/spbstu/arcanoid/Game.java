package edu.spbstu.arcanoid;

import java.awt.*;

import javax.swing.JPanel;

public class Game extends JPanel {


	private static final long serialVersionUID = 377834890210055936L;
	private Dimension gameField = new Dimension(800, 500);
	private static Bat bat;
	private static Ball ball;
	
	public Game(){
		bat = new Bat((gameField.width - Bat.standartBatWidth)/2, (gameField.height - Bat.standartBatHeight), 
				Bat.standartBatWidth, Bat.standartBatHeight);
		ball=new Ball((gameField.width-ball.standartRadius)/2, (gameField.height - Bat.standartBatHeight-ball.standartRadius*2),ball.standartRadius);
		
		
	}
	
	public void setBat(Bat bat){
		this.bat = bat;
	}
	
	public Bat getBat(){
		return this.bat;
	}
	
	public void setSize(Dimension size){
		super.setSize(size);
		gameField = new Dimension(size.width-200, size.height-200);
	}
	
	public void paint (Graphics g){
		super.paint(g);
		
		g.translate((getWidth()-gameField.width)/2, (getHeight()-gameField.height)/2);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, gameField.width, gameField.height);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, gameField.width, gameField.height);
		
		g.setColor(Color.GRAY);
		bat.render(g);
		g.setColor(Color.BLACK );
		ball.render(g);
	}
}
