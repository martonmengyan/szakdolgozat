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
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import object.Object_Key;

public class Entity {

	GamePanel gp;
	Font arial_60;
	
    public int worldX, worldY;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, deadImage;
    public String direction = "down";
    public String testDirection = "down";
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
	
    public int index = 0;
    
    public boolean isEquipped = false;
    
    //0 or 1
    public int faction;
    
    public int ID;
    
    //char
    public int type;
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
    public Entity key;
    
    public int savedBlockNum[][];
    
    public String typeName;
    
    public int attack;
    
    //item
    public int itemStr;
    public int itemVit;
    public int itemAcc;
    public int itemEva;
    
    public boolean dead = false;
    public boolean moved = false;
    public int healTurn = 0;
    
    public ArrayList<Entity> inventoryList = new ArrayList<>();
    public final int inventorySize = 4;
    
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
    			if(inventoryList.size()!=inventorySize) {
    				inventoryList.add(gp.objectList.get(i));
    				gp.objectList.remove(i);
    			}    			
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
    
    public void openDoor(int i) {
    	
    	if(i != 999) {
    		
    		String objectName = gp.objectList.get(i).name;
    		
    		switch(objectName) {
    		case "Door":
    			gp.objectList.remove(i);
    			break;
    		}
    	}
    }
    
    
    public boolean hasKey() {
    	boolean hasKey = false;
    	for(int i=0; i<inventoryList.size(); i++) {
    		if(inventoryList.get(i).typeName == "Key") {
    			hasKey = true;   
    			inventoryList.remove(i);
    		}
    	}
		return hasKey;
    }
    
    public void update() {
		
    	collisionOn = false;
    	
		boolean wantToMove = true;
		boolean wantToEquip = false;
		boolean wantToAttack = false;
		boolean wantToPickUp = false;
		boolean wantToOpenDoor = false;
		
    	int objIndex;
    	int curObjIndex;
    	int entityIndex;
    	
    	curObjIndex = gp.colChecker.checkCurrentBlock(this, true);
		objIndex = gp.colChecker.checkObject(this, true);
    	entityIndex = gp.colChecker.checkEntity(this, gp.entityList);
    	
    	//check conditions before DO
    	
    	if(curObjIndex != 999) {
    		wantToPickUp = true;
    	}
    	if(objIndex != 999) {
    		if(gp.objectList.get(objIndex) != null && gp.objectList.get(objIndex).name=="Door" && hasKey()==true)
    		wantToOpenDoor = true;
    	}
		if(isEnemyClose(gp.entityList).size()>0) {
			wantToAttack = true;
		}
		
		
		//DO
		
		if(wantToAttack) {
			attackAction(gp.entityList,isEnemyClose(gp.entityList));

		}else if(wantToEquip) {
			if(itemIsBetterThanCurrent(inventoryList.get(inventoryList.size())));
			
		}else if(wantToPickUp) {
			pickUpObject(curObjIndex);

		}else if(wantToOpenDoor) {
			openDoor(objIndex);
		}else if(wantToMove) {
			setAction();
			collisionOn = false;
			gp.colChecker.checkTile(this);
			
	    	curObjIndex = gp.colChecker.checkCurrentBlock(this, true);
			objIndex = gp.colChecker.checkObject(this, true);
	    	entityIndex = gp.colChecker.checkEntity(this, gp.entityList);
			entityMove();

		}
		
		gp.eventH.checkPotionEvent(this);
		
	
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
    
	
	public void drawEntityHP(Graphics2D g2) {
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		g2.setColor(Color.black);
		g2.fillRect(screenX-1, screenY-16, gp.TILE_SIZE+2, 6);
		
		g2.setColor(Color.red);
		g2.fillRect(screenX, screenY-15, (gp.TILE_SIZE/maxLife)*life, 4);
		
	}
	
	//BOOLEANS
	
	//check if new item is better than equipped
	public boolean itemIsBetterThanCurrent(Entity pickedupItem) {
		boolean better = false;
		
		int strDif;
		int vitDif;
		int evaDif;
		int accDif;
		int statDif = 0;
		String pickedupItemTypeName = pickedupItem.typeName;
		
		//find the same type equipped item for comparison
		for(int i=0; i<inventoryList.size();i++) {
			if(inventoryList.get(i).isEquipped && inventoryList.get(i).typeName == pickedupItemTypeName) {
				strDif = inventoryList.get(i).str - pickedupItem.str;
				vitDif = inventoryList.get(i).vit - pickedupItem.vit;
				evaDif = inventoryList.get(i).eva - pickedupItem.eva;
				accDif = inventoryList.get(i).acc - pickedupItem.acc;
				
				statDif = strDif + vitDif + evaDif + accDif;		
			}
		}
		
		//if pickedupItem has bigger numbers in statistics, statDif will be less than 0, which means pickedupItem is better.
		if(statDif<0) {
			better = true;;
		}
		return better;
	}
	
	//Hit or Miss
	public boolean isEvaded(Entity attacker, Entity defender) {
			
			//A támadó acc, és a védekező eva statisztikájától függően kiszámolásra kerül, hogy betalál-e
			boolean isEvaded = false;
			return isEvaded;
		}
	
	//gives back an ArrayList of close enemies
	public ArrayList<Integer> isEnemyClose(ArrayList<Entity> entityList) {

		ArrayList<Integer> closeEnemyArray = new ArrayList<>();
		int x=0;
		int y=0;
		
		//check x+gp.TILE_SIZE, x-gp.TILE_SIZE, y+gp.TILE_SIZE, y-gp.TILE_SIZE
		
		//check above
		x = worldX + gp.TILE_SIZE;
		y = worldY;
		
		for(int i=0;i<entityList.size();i++) {
			if(entityList.get(i).worldX == x && entityList.get(i).worldY == y) {
				closeEnemyArray.add(i);
			}
		}
		
		//check under
		x = worldX - gp.TILE_SIZE;
		y = worldY;
		
		for(int i=0;i<entityList.size();i++) {
			if(entityList.get(i).worldX == x && entityList.get(i).worldY == y) {
				closeEnemyArray.add(i);
			}
		}
		
		//check right
		x = worldX;
		y = worldY + gp.TILE_SIZE;
		
		for(int i=0;i<entityList.size();i++) {
			if(entityList.get(i).worldX == x && entityList.get(i).worldY == y) {
				closeEnemyArray.add(i);
			}
		}
		
		//check left
		x = worldX;
		y = worldY - gp.TILE_SIZE;
		
		for(int i=0;i<entityList.size();i++) {
			if(entityList.get(i).worldX == x && entityList.get(i).worldY == y) {
				closeEnemyArray.add(i);
			}
		}
		
		return closeEnemyArray;
	}
	
	//Attack decision
	public void attackAction(ArrayList<Entity> entityList, ArrayList<Integer> enemyIndex) {
		int damageNumber = 1;		
		int targetIndex;
		
		if(enemyIndex.size()>0) {
			
			targetIndex = enemyIndex.get(0);
			
			if(enemyIndex.size()>1) {
				//what if there is more than 1 enemy, which one should be attacked?
				for(int i=0; i<enemyIndex.size();i++) {
					if(entityList.get(targetIndex).life>entityList.get(enemyIndex.get(i)).life) {
						targetIndex = i;
					}
				}
			}
			
			//check if attack is evaded before attack calculation
			if(!isEvaded(this,entityList.get(targetIndex))) {
				//damageNumber = getAttackNumber(this,target);
				entityList.get(targetIndex).life-=damageNumber;
			}
		}
	}
	
	public void damage(int targetIndex) {
		
	}
	
	//equip new item if itemIsBetterThanCurrent() is true
	public void equipBetterItem(Entity pickedupItem) {
			
			String pickedupItemTypeName = pickedupItem.typeName;
			
			//find the same type equipped item for switch
			for(int i=0; i<inventoryList.size();i++) {
				if(inventoryList.get(i).isEquipped && inventoryList.get(i).typeName == pickedupItemTypeName) {
					if(itemIsBetterThanCurrent(inventoryList.get(i))) {
							inventoryList.get(i).isEquipped = false;
							pickedupItem.isEquipped = true;
					}
				}
			}
		}
	
	//Attack damage number calculation if it hits
	public int getAttackNumber(Entity attacker, Entity defender) {
		
		//str statisztikától függően kiszámolásra kerül a sebzés számláló
		int damageNumber = 0;
		return damageNumber;
	}

	//Entity move
	public void entityMove() {
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
		}
	}
	

	
}