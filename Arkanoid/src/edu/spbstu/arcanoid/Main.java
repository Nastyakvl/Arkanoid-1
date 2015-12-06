package edu.spbstu.arcanoid;
import javax.swing.*;

public class Main {
	
	public static JFrame frame;
	public static Game game;

	

	public static void main(String[] args) {
		
		frame = new JFrame("Arkanoid");
		frame.setSize(1000,700);
		//frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		game = new Game();
		game.setSize(frame.getSize());
		frame.add(game);
		
//		bat = new Bat();
//		frame.add(bat);
//		frame.repaint();
		
		frame.setVisible(true);
	}

}
