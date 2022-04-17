package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;

import block.BlockManager;
import entity.*;

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

	public double drawInterval = 1000000000/3;
	
	KeyHandler keyH = new KeyHandler(this);
	BlockManager tileM = new BlockManager(this);
	public CollisionChecker colChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI(this);
	public EventHandler eventH = new EventHandler(this);
	Thread gameThread;
	public int entityIndex = 0;
	public Player player = new Player(this,keyH);
	public ArrayList<Entity> entityList = new ArrayList<>();
	public ArrayList<Entity> objectList = new ArrayList<>();
	
	public String gameScreenNumber;
	public boolean statIsVisible=false;
	public boolean inventoryIsVisible=false;
	public boolean isPaused = false;
	

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
	
	public void setupEntities() {
		aSetter.SetEntity();
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {
		
		
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
			
			player.update();

			for(int i=0; i<entityList.size();i++) {
				if(!isPaused && entityList.size() != 0) {

					if(entityList.get(i).life<=0) {
						//item drop at dead
						for(int j=0; j<entityList.get(i).inventoryList.size(); j++) {
							//only drop the key from inventory
							if(entityList.get(i).inventoryList.get(j).name=="Key") {
								objectList.add(entityList.get(i).inventoryList.get(j));
								objectList.get(objectList.size()-1).worldX = entityList.get(i).worldX;
								objectList.get(objectList.size()-1).worldY = entityList.get(i).worldY;
							}
						}
						entityList.remove(i);
						entityIndex = 0;
					}else entityList.get(i).update();	
				}
				if(entityList.size() == 0) {
					gameScreenNumber = "end";
				}
			}
		}
		
	}		

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		switch(gameScreenNumber) {
			case "title":
				ui.draw(g2, entityList);
				break;
			case "normal":
				tileM.draw(g2,this);
				for(int i=0; i<objectList.size(); i++) {
					objectList.get(i).draw(g2,this);
				}
				for(int i=0; i<entityList.size(); i++) {
					entityList.get(i).draw(g2,this);
				}
				for(int j=0; j<entityList.size(); j++) {
					entityList.get(j).drawEntityHP(g2);
				}
		
				//player for test
		
				player.draw(g2, this);
		
				ui.draw(g2, entityList);

				break;
			case "end":
				ui.draw(g2, entityList);
				break;
		}
		
	}
	
	
}
