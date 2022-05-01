package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.*;

public class Main {
	
	 private static void initWindow() {

	        JFrame window = new JFrame("Pela");

	        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        GamePanel gamePanel = new GamePanel();

	        window.add(gamePanel);

	        window.setResizable(false);
	        window.pack();
	        window.setLocationRelativeTo(null);
	        window.setVisible(true);
	        
	        gamePanel.setupObjects();
	        gamePanel.setupEntities();
			gamePanel.startGameThread();

	    }

	    public static void main(String[] args) throws FileNotFoundException {
	    	PrintStream o = new PrintStream(new File("test.txt"));
			System.setOut(o);
			
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                initWindow();
	            }
	        });
	    }
}
