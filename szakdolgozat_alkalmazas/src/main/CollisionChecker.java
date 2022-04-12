package main;

import java.util.ArrayList;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		
		
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.TILE_SIZE;
		int entityRightCol = entityRightWorldX/gp.TILE_SIZE;
		int entityTopRow = entityTopWorldY/gp.TILE_SIZE;
		int entityBottomRow = entityBottomWorldY/gp.TILE_SIZE;
		
		int tileNum1,tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.TILE_SIZE;
			System.out.println(entityBottomWorldY+","+entityTopRow);
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.block[tileNum1].collision == true || gp.tileM.block[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.TILE_SIZE;
			System.out.println(entityBottomWorldY+","+entityBottomRow);
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.block[tileNum1].collision == true || gp.tileM.block[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.TILE_SIZE;
			System.out.println(entityLeftWorldX+","+entityLeftCol);
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.block[tileNum1].collision == true || gp.tileM.block[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.TILE_SIZE;
			System.out.println(entityRightWorldX+","+entityRightCol);
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.block[tileNum1].collision == true || gp.tileM.block[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	
	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i < gp.objectList.size(); i++) {
				
				if(gp.objectList.get(i) != null) {
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				gp.objectList.get(i).solidArea.x = gp.objectList.get(i).worldX + gp.objectList.get(i).solidArea.x;
				gp.objectList.get(i).solidArea.y = gp.objectList.get(i).worldY + gp.objectList.get(i).solidArea.y;
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					if(entity.solidArea.intersects(gp.objectList.get(i).solidArea)) {
						if(gp.objectList.get(i).collision == true) {
							entity.collisionOn = true;
						}
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
	
	
	public int checkEntity(Entity entity, ArrayList<Entity> entityList) {
		
		int index = 999;
		
		for(int i = 0; i < entityList.size(); i++) {
				
			
				if(entityList.get(i) != null) {
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				entityList.get(i).solidArea.x = entityList.get(i).worldX + entityList.get(i).solidArea.x;
				entityList.get(i).solidArea.y = entityList.get(i).worldY + entityList.get(i).solidArea.y;
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				}
				if(entity.solidArea.intersects(entityList.get(i).solidArea)) {
					if(entityList.get(i) != entity) {
						entity.collisionOn = true;
						index = i;
					}
				}
				
			
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				entityList.get(i).solidArea.x = entityList.get(i).solidAreaDefaultX;
				entityList.get(i).solidArea.y = entityList.get(i).solidAreaDefaultY;
			}
		}
		
		return index;
	}
}
