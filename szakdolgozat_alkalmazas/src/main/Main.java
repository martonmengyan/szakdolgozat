package main;

import javax.swing.*;

public class Main {
	
	 private static void initWindow() {

	        JFrame window = new JFrame("Szimuláció");

	        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        GamePanel gamePanel = new GamePanel();

	        window.add(gamePanel);

	        window.setResizable(false);
	        window.pack();
	        window.setLocationRelativeTo(null);
	        window.setVisible(true);
	        
	        gamePanel.setupObjects();
			gamePanel.startGameThread();

	    }

	    public static void main(String[] args) {
	    	
	        SwingUtilities.invokeLater(new Runnable() {
	            public void run() {
	                initWindow();
	            }
	        });
	    }
}
