package entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import object.Object_Key;

public class Entity {

	GamePanel gp;
	Font arial_60;
	
    public int worldX, worldY;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, deadImage;
    public String direction = "down";
    public int speed;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    public boolean collisionOn = false;
    public int solidAreaDefaultX, solidAreaDefaultY;
    
	public BufferedImage image = null;
	public String name;
	public boolean collision = false;
	
	public Point worldPos;
	public Rectangle solidArea;
	
    public int ammountKey = 0;
    public int index = 0;
    //char
    public int type = 0;
    public int maxLife;
    public int life;
    public int lvl;
    public int str;
    public int vit;
    public int acc;
    public int eva;
    public int exp;
    public int reqExp;
    public Entity currentWeapon;
    public Entity currentArmor;
    public Entity currentHelmet;
    //public Entity eqKey;
    
    //item
    public int itemStr;
    public int itemVit;
    public int itemAcc;
    public int itemEva;
    
    public boolean dead = false;
    public boolean moved = false;
    public int healTurn = 0;
    

    
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int inventorySize = 20;
    
    public Entity(GamePanel gp) {
    	this.gp = gp;
    	arial_60 = new Font("Arial", Font.PLAIN, 60);
    }
    
    public void setAction() {
    	
    }
    
    public void pickUpObject(int i) {
    	
    	if(i != 999) {
    		
    		String objectName = gp.objectList.get(i).name;
    		
    		switch(objectName) {
    		case "Key":
    			ammountKey++;
    			gp.objectList.remove(i);
    			System.out.println("Key:"+ammountKey);
    			break;
    		case "Door":
    			if(ammountKey > 0) {
    				gp.objectList.remove(i);
    				ammountKey--;
    			}
    			System.out.println("Key:"+ammountKey);
    			break;
    		case "Boots":
    			//utasítás
    			gp.objectList.remove(i);
    			break;
    		case "Chest":
    			gp.gameScreenNumber = "end";
    			break;
    		}
    	}
    }
    
    public void update() {

	
		setAction();
		
		collisionOn = false;
		gp.colChecker.checkTile(this);
		
    	int objIndex = gp.colChecker.checkObject(this, true);
    	pickUpObject(objIndex);
	
    	int entityIndex = gp.colChecker.checkEntity(this, gp.entityList);
    	
    	if(collisionOn == true) {
			gp.eventH.checkPotionEvent(this);
    	}
    	
		if(collisionOn == false) {
			
				switch(direction) {
				case "up":
					worldY -= speed;
					System.out.println("moved to up");
					moved = true;
					break;
				case "down":
					worldY += speed;
					System.out.println("moved to down");
					moved = true;
					break;
				case "left":
					worldX -= speed;
					System.out.println("moved to left");
					moved = true;
					break;
				case "right":
					worldX += speed;
					System.out.println("moved to right");
					moved = true;
					break;
				
			}
		
			spriteCounter++;
			if(spriteCounter >= 1) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
			
			gp.eventH.checkPotionEvent(this);
		}
	
    }
    
    public void loadDeathImage() {
    	try {
    		deadImage = ImageIO.read(getClass().getResourceAsStream("/object/Object_Chest.png"));
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void draw(Graphics2D g2, ImageObserver observer) {
    	

    	BufferedImage image = null;
    	int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
        switch(direction) {
        case "up":
        	if(spriteNum == 1) {
        		image = up1;
        	}
        	if(spriteNum == 2) {
        		image = up2;
        	}
        	break;
        case "down":
        	if(spriteNum == 1) {
        		image = down1;
        	}
        	if(spriteNum == 2) {
        		image = down2;
        	}
        	break;
        case "left":
        	if(spriteNum == 1) {
        		image = left1;
        	}
        	if(spriteNum == 2) {
        		image = left2;
        	}
        	break;
        case "right":
        	if(spriteNum == 1) {
        		image = right1;
        	}
        	if(spriteNum == 2) {
        		image = right2;
        	}
        	break;
        }
		
		if(worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.screenY) {
			
			g2.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, observer);
			if(type==2) {
				drawEntityHP(g2,this,screenX,screenY);
			}
			
		}
    }
    
	
	public void drawEntityHP(Graphics2D g2,Entity entity,int x, int y) {

		
		g2.setColor(Color.black);
		g2.fillRect(x-1, y-16, gp.TILE_SIZE+2, 6);
		
		g2.setColor(Color.red);
		g2.fillRect(x, y-15, (gp.TILE_SIZE/maxLife)*life, 4);
		
	}
	
}
