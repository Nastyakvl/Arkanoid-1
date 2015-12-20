package edu.spbstu.arcanoid;
import javax.swing.*;

public class Main {
	
	public static JFrame frame;
	public static Game game;

	

	public static void main(String[] args) {
		
		frame = new JFrame("Arkanoid");
		frame.setSize(1000,700);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		game = new Game(frame,8,3);
		game.setSize(frame.getSize());
		frame.add(game);
		
		frame.setVisible(true);
	}

}
