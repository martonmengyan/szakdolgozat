package main;

import java.util.ArrayList;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}	
	
	public int checkCurrentBlock(Entity entity, boolean player) {
		int index = 999;
		
		for(int i = 0; i < gp.objectList.size(); i++) {
				
				if(gp.objectList.get(i) != null) {
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				gp.objectList.get(i).solidArea.x = gp.objectList.get(i).worldX + gp.objectList.get(i).solidArea.x;
				gp.objectList.get(i).solidArea.y = gp.objectList.get(i).worldY + gp.objectList.get(i).solidArea.y;
				
					if(entity.solidArea.intersects(gp.objectList.get(i).solidArea)) {
						if(gp.objectList.get(i).collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}

					
				}
			
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.objectList.get(i).solidArea.x = gp.objectList.get(i).solidAreaDefaultX;
				gp.objectList.get(i).solidArea.y = gp.objectList.get(i).solidAreaDefaultY;
			}
		
	
		
		return index;
	}
	
	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i < gp.objectList.size(); i++) {
				
				if(gp.objectList.get(i) != null) {
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				gp.objectList.get(i).solidArea.x = gp.objectList.get(i).worldX + gp.objectList.get(i).solidArea.x;
				gp.objectList.get(i).solidArea.y = gp.objectList.get(i).worldY + gp.objectList.get(i).solidArea.y;
				
				switch(entity.testDirection) {
				case "up":
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(gp.objectList.get(i).solidArea)) {
						if(gp.objectList.get(i).collision == true) {
							entity.collisionOn = true;
						} else 
						if(player == true) {
							index = i;
						}
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					if(entity.solidArea.intersects(gp.objectList.get(i).solidArea)) {
						if(gp.objectList.get(i).collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					if(entity.solidArea.intersects(gp.objectList.get(i).solidArea)) {
						if(gp.objectList.get(i).collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					if(entity.solidArea.intersects(gp.objectList.get(i).solidArea)) {
						if(gp.objectList.get(i).collision == true) {
							entity.collisionOn = true;
						}
						if(player == true) {
							index = i;
						}
					}
					break;
					
				}
			
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.objectList.get(i).solidArea.x = gp.objectList.get(i).solidAreaDefaultX;
				gp.objectList.get(i).solidArea.y = gp.objectList.get(i).solidAreaDefaultY;
			}
		}
		
		return index;
	}
	
}
