package main;

import entity.Entity;

public class ItemDecider {
	
	GamePanel gp;

	public ItemDecider(GamePanel gp) {
		this.gp = gp;
	}	
	
	public int checkCurrentBlock(Entity entity) {
		int index = 999;
		int X = entity.worldX/48;
		int Y = entity.worldY/48;
		for(int i = 0; i < gp.objectList.size(); i++) {	
			if(gp.objectList.get(i) != null) {
				if(Y > 0 && Y < 50 && X > 0 && X < 50) {
					if(X == gp.objectList.get(i).worldX/48 && Y == gp.objectList.get(i).worldY/48) {
						index = i;	
						break;
					}
				}
			}
		}
		
		return index;
	}
	
	public int checkObject(Entity entity) {
		
		int index = 999;
		int X;
		int Y;
		for(int i = 0; i < gp.objectList.size(); i++) {
				
				if(gp.objectList.get(i) != null) {
					
				//up
				Y = entity.worldY/48 - entity.speed/48;
				X = entity.worldX/48;
				
				if(Y > 0 && Y < 50 && X > 0 && X < 50) {
					if(X == gp.objectList.get(i).worldX/48 && Y == gp.objectList.get(i).worldY/48) {
						index = i;	
						break;
					}
				}
		
				//down
				Y = entity.worldY/48 + entity.speed/48;
				X = entity.worldX/48;
				
				if(Y > 0 && Y < 50 && X > 0 && X < 50) {
					if(X == gp.objectList.get(i).worldX/48 && Y == gp.objectList.get(i).worldY/48) {
						index = i;
						break;
					}
				}
				
				//left
				Y = entity.worldY/48;
				X = entity.worldX/48 - entity.speed/48;
				
				if(Y > 0 && Y < 50 && X > 0 && X < 50) {
					if(X == gp.objectList.get(i).worldX/48 && Y == gp.objectList.get(i).worldY/48) {
						index = i;
						break;
					}
				}
				
				//right
				Y = entity.worldY/48;
				X = entity.worldX/48 + entity.speed/48;
				
				if(Y > 0 && Y < 50 && X > 0 && X < 50) {
					if(X == gp.objectList.get(i).worldX/48 && Y == gp.objectList.get(i).worldY/48) {
						index = i;	
						break;
					}
				}
			}
		}
		
		return index;
	}
	
}
