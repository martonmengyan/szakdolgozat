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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

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
    public int HPturn = 0;
    public boolean collisionOn = false;
    public int solidAreaDefaultX, solidAreaDefaultY;
    
	public BufferedImage image = null;
	public String name;
	public boolean collision = false;
	
	public Map<Point,Integer> hashMap = new HashMap<Point,Integer>();
	public ArrayList<Point> worldPos;

	public ArrayList<Point> possibleBlocks;
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
    public final int inventorySize = 8;
    
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
    		case "Entity_Armor_01":
    			if(inventoryList.size()!=inventorySize) {
    				inventoryList.add(gp.objectList.get(i));
    				gp.objectList.remove(i);
    				Random random = new Random();
    				inventoryList.get(inventoryList.size()-1).str=random.nextInt(4 + 0) - 0;
    				inventoryList.get(inventoryList.size()-1).vit=random.nextInt(4 + 0) - 0;
    				inventoryList.get(inventoryList.size()-1).eva=random.nextInt(4 + 0) - 0;
    				inventoryList.get(inventoryList.size()-1).acc=random.nextInt(4 + 0) - 0;
    			}    			
    			break;
    		case "Entity_Helmet_01":
    			if(inventoryList.size()!=inventorySize) {
    				inventoryList.add(gp.objectList.get(i));
    				gp.objectList.remove(i);
    				Random random = new Random();
    				inventoryList.get(inventoryList.size()-1).str=random.nextInt(4 + 0) - 0;
    				inventoryList.get(inventoryList.size()-1).vit=random.nextInt(4 + 0) - 0;
    				inventoryList.get(inventoryList.size()-1).eva=random.nextInt(4 + 0) - 0;
    				inventoryList.get(inventoryList.size()-1).acc=random.nextInt(4 + 0) - 0;
    			}    			
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
    			for(int j=0;j<inventoryList.size();j++) {
    				if(inventoryList.get(j).typeName == "Key") {
    					inventoryList.remove(j);
    				}
    			}
    			break;
    		}
    	}
    }
    
    
    public boolean hasKey() {
    	boolean hasKey = false;
    	for(int i=0; i<inventoryList.size(); i++) {
    		if(inventoryList.get(i).typeName == "Key") {
    			hasKey = true;   
    			
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
		boolean wantToMoveToItem = false;
		
    	int objIndex;
    	int curObjIndex;
    	
    	saveBlocks();
    	curObjIndex = gp.colChecker.checkCurrentBlock(this, true);
		objIndex = gp.colChecker.checkObject(this, true);
    	
    	//check conditions before DO
    	
    	if(objIndex != 999) {
    		if(gp.objectList.get(objIndex) != null && gp.objectList.get(objIndex).name=="Door" && hasKey()==true) {
    			wantToOpenDoor = true;
    		}
    		
    	}
		
    	if(curObjIndex != 999) {
    		if(gp.objectList.get(curObjIndex) != null) {
    			if(gp.objectList.get(curObjIndex).typeName=="Chest" || gp.objectList.get(curObjIndex).typeName=="Key" || gp.objectList.get(curObjIndex).typeName=="Armor" || gp.objectList.get(curObjIndex).typeName=="Helmet" ) {
        			wantToPickUp = true;
    			}

    		}
    		
    	}

		if(isEnemyClose(gp.entityList).size()>0) {
			wantToAttack = true;
		}
		
		if(isItemClose(gp.objectList).size()>0) {
			wantToMoveToItem = true;
		}
		if(inventoryList.get(inventoryList.size()-1).name != "Key") {
			if(itemIsBetterThanCurrent(inventoryList.get(inventoryList.size()-1))) {
				wantToEquip = true;
			}
		}
		//DO
		
		if(wantToAttack) {
			attackAction(gp.entityList,isEnemyClose(gp.entityList));

		}else if(wantToEquip) {			
			equipBetterItem(inventoryList.get(inventoryList.size()-1));	
			
		}else if(wantToPickUp) {
			pickUpObject(curObjIndex);

		}else if(wantToOpenDoor) {
			openDoor(objIndex);


			
		}else if(wantToMoveToItem) {
			moveToKeyBlock(gp.objectList,isItemClose(gp.objectList));
			entityMove();
		
		}else if(wantToMove) {

			setAction();
			
			ArrayList<Point> detectableBlocks = detectArray(gp.entityList,gp.objectList);

			if(!checkIfNewEnemy(detectableBlocks,gp.entityList)) {
				if(!checkIfNewItem(detectableBlocks,gp.objectList)) {
					possibleBlocks();
				}
				
			}
			

			entityMove();

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
			if(entityList.get(i).worldX == x && entityList.get(i).worldY == y && entityList.get(i).faction!=faction) {
				closeEnemyArray.add(i);
			}
		}
		
		//check under
		x = worldX - gp.TILE_SIZE;
		y = worldY;
		
		for(int i=0;i<entityList.size();i++) {
			if(entityList.get(i).worldX == x && entityList.get(i).worldY == y && entityList.get(i).faction!=faction) {
				closeEnemyArray.add(i);
			}
		}
		
		//check right
		x = worldX;
		y = worldY + gp.TILE_SIZE;
		
		for(int i=0;i<entityList.size();i++) {
			if(entityList.get(i).worldX == x && entityList.get(i).worldY == y && entityList.get(i).faction!=faction) {
				closeEnemyArray.add(i);
			}
		}
		
		//check left
		x = worldX;
		y = worldY - gp.TILE_SIZE;
		
		for(int i=0;i<entityList.size();i++) {
			if(entityList.get(i).worldX == x && entityList.get(i).worldY == y && entityList.get(i).faction!=faction) {
				closeEnemyArray.add(i);
			}
		}
		
		return closeEnemyArray;
	}
	
	public ArrayList<Integer> isItemClose(ArrayList<Entity> objectList) {

		ArrayList<Integer> closeKeyArray = new ArrayList<>();
		int x=0;
		int y=0;
		
		//check x+gp.TILE_SIZE, x-gp.TILE_SIZE, y+gp.TILE_SIZE, y-gp.TILE_SIZE
		
		//check above
		x = worldX + gp.TILE_SIZE;
		y = worldY;
		
		for(int i=0;i<objectList.size();i++) {
			if(objectList.get(i).worldX == x && objectList.get(i).worldY == y) {
				if(objectList.get(i).typeName == "Chest" || objectList.get(i).typeName == "Key" || objectList.get(i).typeName == "Armor" || objectList.get(i).typeName == "Helmet") {
					closeKeyArray.add(i);
				}

			}
		}
		
		//check under
		x = worldX - gp.TILE_SIZE;
		y = worldY;
		
		for(int i=0;i<objectList.size();i++) {
			if(objectList.get(i).worldX == x && objectList.get(i).worldY == y) {
				if(objectList.get(i).typeName == "Chest" || objectList.get(i).typeName == "Key" || objectList.get(i).typeName == "Armor" || objectList.get(i).typeName == "Helmet") {
					closeKeyArray.add(i);
				}
			}
		}
		
		//check right
		x = worldX;
		y = worldY + gp.TILE_SIZE;
		
		for(int i=0;i<objectList.size();i++) {
			if(objectList.get(i).worldX == x && objectList.get(i).worldY == y) {
				if(objectList.get(i).typeName == "Chest" || objectList.get(i).typeName == "Key" || objectList.get(i).typeName == "Armor" || objectList.get(i).typeName == "Helmet") {
					closeKeyArray.add(i);
				}
			}
		}
		
		//check left
		x = worldX;
		y = worldY - gp.TILE_SIZE;
		
		for(int i=0;i<objectList.size();i++) {
			if(objectList.get(i).worldX == x && objectList.get(i).worldY == y) {
				if(objectList.get(i).typeName == "Chest" || objectList.get(i).typeName == "Key" || objectList.get(i).typeName == "Armor" || objectList.get(i).typeName == "Helmet") {
					closeKeyArray.add(i);
				}
			}
		}
		
		return closeKeyArray;
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
			
			//rotate to enemy coordinates
			direction = directionFromCoordinates(new Point(entityList.get(targetIndex).worldX/48,entityList.get(targetIndex).worldY/48));
						
			//check if attack is evaded before attack calculation
			if(!isEvaded(this,entityList.get(targetIndex))) {
				//damageNumber = getAttackNumber(this,target);
				entityList.get(targetIndex).life-=damageNumber;
			}
			
			System.out.println("Attacked: " + entityList.get(targetIndex).worldX/48 + "," + entityList.get(targetIndex).worldY/48 + "!");
		}
	}
	
	public void moveToKeyBlock(ArrayList<Entity> objectList, ArrayList<Integer> keyIndex) {
	
		Random random = new Random();
		int targetIndex;
		
		if(keyIndex.size()>0) {
			
			targetIndex = keyIndex.get(0);
			
			if(keyIndex.size()>1) {
				
				//what if there is more than 1 key to pick up?
				targetIndex = random.nextInt(keyIndex.size()-0)+0;
				//what if there is a chest? choose the chest!
				for(int i=0; i<keyIndex.size();i++) {
					if(objectList.get(keyIndex.get(i)).name == "Chest") {
						targetIndex = keyIndex.get(i);
						break;
					}
					
				}

			}
			
			//rotate to enemy coordinates
			direction = directionFromCoordinates(new Point(objectList.get(targetIndex).worldX/48,objectList.get(targetIndex).worldY/48));
			System.out.println("Moved to key: " + direction);
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
						inventoryList.get(i).isEquipped = false;
						str -= inventoryList.get(i).str;
						vit -= inventoryList.get(i).vit; 
						eva -= inventoryList.get(i).eva; 
						acc -= inventoryList.get(i).acc; 
						pickedupItem.isEquipped = true;
						str += pickedupItem.str;
						vit += pickedupItem.vit; 
						eva += pickedupItem.eva; 
						acc += pickedupItem.acc; 
						System.out.println("Equipped better item!");
						break;
				}
			}
		}
	
	//Attack damage number calculation if it hits
	public int getAttackNumber(Entity attacker, Entity defender) {
		
		//based on statistics
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
			
			hashMap.put(new Point(worldX/48,worldY/48), hashMap.get(new Point(worldX/48,worldY/48))==null?1: hashMap.get(new Point(worldX/48,worldY/48)) +1);
		
			/*
			//hashMap kiiratása
			 
			for(Point p:hashMap.keySet()) {
				System.out.println(p.x + "," + p.y + "," + hashMap.get(p));
			}
			*/
			
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
	
	public void saveBlocks() {
		
		worldPos = new ArrayList<>();
				
		switch(direction) {
		case "up":
			//y+1
			worldPos.add(new Point(worldX/48,worldY/48 - gp.TILE_SIZE/48 * 1));
			//y-1
			worldPos.add(new Point(worldX/48,worldY/48 + gp.TILE_SIZE/48 * +1));
			//x-1
			worldPos.add(new Point(worldX/48 + gp.TILE_SIZE/48 * -1,worldY/48));
			//x+1
			worldPos.add(new Point(worldX/48 + gp.TILE_SIZE/48 * +1,worldY/48));
			break;
		case "down":
			//y-1
			worldPos.add(new Point(worldX/48,worldY/48 + gp.TILE_SIZE/48 * +1));
			//y+1
			worldPos.add(new Point(worldX/48,worldY/48 + gp.TILE_SIZE/48 * -1));
			//x-1
			worldPos.add(new Point(worldX/48 + gp.TILE_SIZE/48 * -1,worldY/48));
			//x+1
			worldPos.add(new Point(worldX/48 + gp.TILE_SIZE/48 * +1,worldY/48));
			break;
		case "right":
			//x+1
			worldPos.add(new Point(worldX/48 + gp.TILE_SIZE/48 * 1, worldY/48));	
			//y-1
			worldPos.add(new Point(worldX/48,worldY/48 + gp.TILE_SIZE/48 * +1));
			//y+1
			worldPos.add(new Point(worldX/48,worldY/48 + gp.TILE_SIZE/48 * -1));
			//x-1
			worldPos.add(new Point(worldX/48 + gp.TILE_SIZE/48 * -1,worldY/48));
			break;
		case "left":
			//x-1
			worldPos.add(new Point(worldX/48 - gp.TILE_SIZE/48 * 1, worldY/48));
			//y-1
			worldPos.add(new Point(worldX/48,worldY/48 + gp.TILE_SIZE/48 * +1));
			//y+1
			worldPos.add(new Point(worldX/48,worldY/48 + gp.TILE_SIZE/48 * -1));
			//x+1
			worldPos.add(new Point(worldX/48 + gp.TILE_SIZE/48 * +1,worldY/48));
			break;
		}

		//remove if not acceptable
		
		for(int i=worldPos.size()-1;i>=0;i--) {
			if(gp.tileM.mapTileNum[worldPos.get(i).x][worldPos.get(i).y] != 2) {
				worldPos.remove(i);
			}
		}
		
		for(int i=worldPos.size()-1;i>=0;i--) {
			for(int j=0; j<gp.objectList.size();j++) {
				if(worldPos.get(i).x == gp.objectList.get(j).worldX/48 && worldPos.get(i).y == gp.objectList.get(j).worldY/48 && gp.objectList.get(j).collision == true) {
					worldPos.remove(i);
					break;
				}
				
			}
		}
		
		for(int i=worldPos.size()-1;i>=0;i--) {
			for(int j=0; j<gp.entityList.size();j++) {
				if(worldPos.get(i).x == gp.entityList.get(j).worldX/48 && worldPos.get(i).y == gp.entityList.get(j).worldY/48) {
					worldPos.remove(i);
					break;
				}
				
			}
		}

		System.out.println("---------------------------------");
		System.out.println("Lehetseges lepesek:");
		
		for(int i=0;i<worldPos.size();i++) {
			System.out.println( i+1 + ": " + worldPos.get(i).x +","+ worldPos.get(i).y);
		}
		
		
	}

	public void possibleBlocks() {
		var map = hashMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors.toMap(
			    Map.Entry::getKey, 
			    Map.Entry::getValue, 
			    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		
		int minHash = 0;
		Random random = new Random(); 
		boolean isHashFound = false;
		ArrayList<Point> nullHashBlocks = new ArrayList<>();
		
		for(int i=0;i<worldPos.size();i++) {
			if(hashMap.get(worldPos.get(i)) == null) {
				nullHashBlocks.add(worldPos.get(i));
			} 
		}
		
		if(nullHashBlocks.size()>0) {
			int index = random.nextInt((nullHashBlocks.size() - 0) + 0);
			direction = directionFromCoordinates(nullHashBlocks.get(index));
		}
		
		if(nullHashBlocks.size()==0) {	
			for(Point p:map.keySet()) {
				for(int i=0; i<worldPos.size();i++) {
					if(p.x == worldPos.get(i).x && p.y == worldPos.get(i).y) {						
						minHash = map.get(p);
						System.out.println("min:" + minHash);
						isHashFound = true;
						break;					
					}					
				}
				if(isHashFound == true){
					break;
				}
			}
			
			possibleBlocks = new ArrayList<>();
			
			for(Point p:map.keySet()) {
				System.out.println("x:" + p.x + ",y: " + p.y + ",hash:" + map.get(p));
				for(int i=0; i<worldPos.size();i++) {					
					if(p.x == worldPos.get(i).x && p.y == worldPos.get(i).y && minHash == map.get(p)) {
						possibleBlocks.add(p);
					}
				}
			}
						
			int index = random.nextInt((possibleBlocks.size() - 0) + 0);			
			direction = directionFromCoordinates(possibleBlocks.get(index));
		}
	}
	
	public String directionFromCoordinates(Point worldPos) {
		String dir = "down";
		int difx;
		int dify;

		difx = worldPos.x - worldX/48;
		dify = worldPos.y - worldY/48;
		System.out.println("current position: "+worldX/48 + "," +worldY/48);
		System.out.println("chosen position: "+worldPos.x + "," +worldPos.y);
		collisionOn = false;
		if(difx == 0) {
			if(dify < 0) {
				dir = "up";
				System.out.println("chosen direction: up!");
			} else {
				dir = "down";
				System.out.println("chosen direction: down!");
			}			
		}else if (dify == 0) {
			if(difx > 0) {
				dir = "right";
				System.out.println("chosen direction: right!");
			} else {
				dir = "left";
				System.out.println("chosen direction: left!");
			}
		}
		return dir;
	}
	
	public ArrayList<Point> detectArray(ArrayList<Entity> entityList, ArrayList<Entity> objectList) {

		ArrayList<Point> detectableBlocks = new ArrayList<>();
		
		boolean isRemoved = false;
		
		//up
		if(worldX/48>0 && worldY/48 -2>0 && worldX/48<50 && worldY/48 -2<50) {
			isRemoved = false;
			for(int j=0; j<entityList.size(); j++){
				if(worldX/48 == entityList.get(j).worldX/48 && worldY/48 -1 == entityList.get(j).worldY/48){						
					isRemoved = true;
					break;
				}
			}
			if(!isRemoved) {				
				for(int j=0; j<objectList.size(); j++){
					if(worldX/48 == objectList.get(j).worldX/48 && worldY/48 -1 == objectList.get(j).worldY/48 && objectList.get(j).collision == true){						
						isRemoved = true;
						break;						
					}
				}			
			}
			if(!isRemoved) {
				if(gp.tileM.mapTileNum[worldX/48][worldY/48 - 1]!=2) {						
					isRemoved = true;
				}			
			}
			if(!isRemoved) {
				System.out.println("up added");
				detectableBlocks.add(new Point(worldX/48,worldY/48 - 2));
			}				
		}
		//down
		isRemoved=false;
		if(worldX/48>0 && worldY/48 +2>0 && worldX/48<50 && worldY/48 +2<50) {
			isRemoved = false;
			for(int j=0; j<entityList.size(); j++){
				if(worldX/48 == entityList.get(j).worldX/48 && worldY/48 + 1 == entityList.get(j).worldY/48){						
					isRemoved = true;
					break;
				}
			}
			if(!isRemoved) {				
				for(int j=0; j<objectList.size(); j++){
					if(worldX/48 == objectList.get(j).worldX/48 && worldY/48 +1 == objectList.get(j).worldY/48 && objectList.get(j).collision == true){							
						isRemoved = true;
						break;							
					}
				}			
			}
			if(!isRemoved) {
				if(gp.tileM.mapTileNum[worldX/48][worldY/48 + 1]!=2) {						
					isRemoved = true;
				}			
			}
			if(!isRemoved) {
				System.out.println("down added");					
				detectableBlocks.add(new Point(worldX/48,worldY/48 + 2));
			}
		}		
		//right
		if(worldX/48 + 2>0 && worldY/48>0 && worldX/48 + 2<50 && worldY/48<50) {
			isRemoved = false;
			for(int j=0; j<entityList.size(); j++){
				if(worldX/48  + 1 == entityList.get(j).worldX/48 && worldY/48== entityList.get(j).worldY/48){						
					isRemoved = true;
					break;
				}
			}
			if(!isRemoved) {				
				for(int j=0; j<objectList.size(); j++){
					if(worldX/48  + 1 == objectList.get(j).worldX/48 && worldY/48== objectList.get(j).worldY/48 && objectList.get(j).collision == true){							
						isRemoved = true;
						break;							
					}
				}			
			}
			if(!isRemoved) {
				if(gp.tileM.mapTileNum[worldX/48 + 1][worldY/48]!=2) {						
					isRemoved = true;
				}			
			}
			if(!isRemoved) {
				System.out.println("right added");
				detectableBlocks.add(new Point(worldX/48 + 2,worldY/48));
			}
			
		}
		//left
		if(worldX/48 -2>0 && worldY/48>0 && worldX/48 - 2<50 && worldY/48<50) {
			isRemoved = false;
			for(int j=0; j<entityList.size(); j++){
				if(worldX/48 - 1 == entityList.get(j).worldX/48 && worldY/48== entityList.get(j).worldY/48){						
					isRemoved = true;
					break;
				}
			}
			if(!isRemoved) {				
				for(int j=0; j<objectList.size(); j++){
					if(worldX/48 - 1 == objectList.get(j).worldX/48 && worldY/48== objectList.get(j).worldY/48 && objectList.get(j).collision == true){							
						isRemoved = true;
						break;							
					}
				}			
			}
			if(!isRemoved) {
				if(gp.tileM.mapTileNum[worldX/48 - 1][worldY/48]!=2) {						
					isRemoved = true;
				}			
			}
			if(!isRemoved) {
				System.out.println("left added");
				detectableBlocks.add(new Point(worldX/48 - 2,worldY/48));
			}
		}
			
		for(int k=0;k<detectableBlocks.size();k++) {
			System.out.println(detectableBlocks.get(k).x+", "+detectableBlocks.get(k).y);
		}
		return detectableBlocks;
	}
	
	public boolean checkIfNewEnemy(ArrayList<Point> worldpos, ArrayList<Entity> entityList) {
		
		boolean isDetected = false;
		for(int i=0; i<worldpos.size();i++){
			for(int j=0; j<entityList.size(); j++) {
				if(faction != entityList.get(j).faction) {
					if(worldpos.get(i).x == entityList.get(j).worldX/48 && worldpos.get(i).y == entityList.get(j).worldY/48) {
						if(worldX - entityList.get(j).worldX == 0) {
							if(worldY - entityList.get(j).worldY > 0) {
								direction = "up";
								isDetected = true;
							}else {
								direction = "down";
								isDetected = true;
							}
						} else if(worldY - entityList.get(j).worldY == 0) {
							if(worldX - entityList.get(j).worldX > 0) {
								direction = "left";
								isDetected = true;
							}else {
								direction = "right";
								isDetected = true;
							}
						}
					}
				}
			}
		}
		return isDetected;
		
	}
	
	public boolean checkIfNewItem(ArrayList<Point> worldpos, ArrayList<Entity> objectList) {
		
		boolean isDetected = false;
		for(int i=0; i<worldpos.size();i++){
			for(int j=0; j<objectList.size(); j++) {
				if(objectList.get(j).typeName == "Key" || objectList.get(j).typeName == "Chest" || objectList.get(j).typeName == "Armor" || objectList.get(j).typeName == "Helmet" || (objectList.get(j).typeName == "Door" && hasKey()==true)) {
					if(worldpos.get(i).x == objectList.get(j).worldX/48 && worldpos.get(i).y == objectList.get(j).worldY/48) {
						if(worldX - objectList.get(j).worldX == 0) {
							if(worldY - objectList.get(j).worldY > 0) {
								direction = "up";
								isDetected = true;
								System.out.println(objectList.get(j).name + " detected: up");
							}else {
								direction = "down";
								isDetected = true;
								System.out.println(objectList.get(j).name+" detected: down");
							}
						} else if(worldY - objectList.get(j).worldY == 0) {
							if(worldX - objectList.get(j).worldX > 0) {
								direction = "left";
								isDetected = true;
								System.out.println(objectList.get(j).name+" detected: left");
							}else {
								direction = "right";
								isDetected = true;
								System.out.println(objectList.get(j).name+" detected: right");
							}
						}
					}
				}
			}
		}
		return isDetected;
	}	
}