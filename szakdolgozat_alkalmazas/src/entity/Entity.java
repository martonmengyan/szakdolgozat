package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Entity {

	GamePanel gp;
	
    public int worldX, worldY;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, deadImage;
    public String direction;
    public int speed;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    public Rectangle solidArea;
    public boolean collisionOn = false;
    public int solidAreaDefaultX, solidAreaDefaultY;
    
    public int ammountKey = 0;
    
    public int maxLife;
    public int life;
    
    public boolean dead = false;
    
    public boolean moved = false;
    public int healTurn = 0;
    
    public Entity(GamePanel gp) {
    	this.gp = gp;
    }
    
    public void setAction() {
    	
    }
    
    public void pickUpObject(int i) {
    	
    	if(i != 999) {
    		
    		String objectName = gp.obj[i].name;
    		
    		switch(objectName) {
    		case "Key":
    			ammountKey++;
    			gp.obj[i] = null;
    			System.out.println("Key:"+ammountKey);
    			break;
    		case "Door":
    			if(ammountKey > 0) {
    				gp.obj[i] = null;
    				ammountKey--;
    			}
    			System.out.println("Key:"+ammountKey);
    			break;
    		case "Boots":
    			//utasítás
    			gp.obj[i] = null;
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
    	
	    	int entityIndex = gp.colChecker.checkEntity(this, gp.entity);
	    	
		
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
		}
    }
    
}
