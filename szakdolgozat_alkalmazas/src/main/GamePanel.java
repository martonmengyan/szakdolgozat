package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

import block.BlockManager;
import entity.*;
import object.SuperObject;

public class GamePanel extends JPanel implements Runnable {


	private static final long serialVersionUID = 1L;
	//SCREEN
	final int originalTileSize = 16; //16x16
	final int scale = 3;
	
	public final int TILE_SIZE = originalTileSize * scale; //scaled Tile
	public final int ROWS = 12;
	public final int COLUMNS = 16;
	public final int screenWidth = TILE_SIZE * COLUMNS; //768
	public final int screenHeight = TILE_SIZE * ROWS; //576
	
	//WORLD
	public final int WORLDROWS = 50;
	public final int WORLDCOLUMNS = 50;

	KeyHandler keyH = new KeyHandler(this);
	BlockManager tileM = new BlockManager(this);
	public CollisionChecker colChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	Thread gameThread;
	
	public Player player = new Player(this,keyH);
	public Entity entity = new Entity();
	public SuperObject obj[] = new SuperObject[10];
	
	public String gameScreenNumber;
	

	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.addKeyListener(keyH);
		this.setFocusable(true);
		this.setBackground(Color.black);
		gameScreenNumber = "title";		
		
	}
	
	public void setupObjects() {
		aSetter.setObject();
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/60;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while(gameThread != null) {

			 update();
			 
			 repaint();
			 
			 try {
				 double remTime = nextDrawTime - System.nanoTime();
				 remTime = remTime/1000000;
				 
				 if(remTime < 0) {
					 remTime = 0;
				 }
				 
				 Thread.sleep((long) remTime);
				 
				 nextDrawTime += drawInterval;
			 }catch(InterruptedException e) {
				 e.printStackTrace();
			 }
		}
		
	}
	
	public void update() {
		
		if(gameScreenNumber == "normal") {
			if(player.life>0) {
				
				player.update();
				}
		}
		if(gameScreenNumber == "pause") {
			//not updating the player coordinate
		}
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		switch(gameScreenNumber) {
		case "title":
			ui.draw(g2);
			break;
		case "normal":
			tileM.draw(g2,this);
			
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					obj[i].draw(g2, this, this);
				}
			}
			
			if(player.life>0) {
				player.draw(g2, this);
			}

			
			ui.draw(g2);
			break;
		case "end":
			ui.draw(g2);
			break;
		case "pause":
			ui.draw(g2);
			break;
		}
		
	}
	
	
}
