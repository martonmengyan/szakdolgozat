package main;

import entity.*;
import object.*;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp=gp;
	}
	
	public void setObject() {
		gp.obj[0] = new Object_Key();
		gp.obj[0].worldX=1*gp.TILE_SIZE;
		gp.obj[0].worldY=1*gp.TILE_SIZE;
		
		gp.obj[1] = new Object_Door();
		gp.obj[1].worldX=5*gp.TILE_SIZE;
		gp.obj[1].worldY=4*gp.TILE_SIZE;
		
		gp.obj[5] = new Object_Door();
		gp.obj[5].worldX=1*gp.TILE_SIZE;
		gp.obj[5].worldY=4*gp.TILE_SIZE;
		
		gp.obj[6] = new Object_Door();
		gp.obj[6].worldX=6*gp.TILE_SIZE;
		gp.obj[6].worldY=1*gp.TILE_SIZE;
		
		gp.obj[2] = new Object_Chest();
		gp.obj[2].worldX=10*gp.TILE_SIZE;
		gp.obj[2].worldY=6*gp.TILE_SIZE;
		
		gp.obj[3] = new Object_Boots();
		gp.obj[3].worldX=10*gp.TILE_SIZE;
		gp.obj[3].worldY=10*gp.TILE_SIZE;
		
		gp.obj[4] = new Object_Brick();
		gp.obj[4].worldX=3*gp.TILE_SIZE;
		gp.obj[4].worldY=3*gp.TILE_SIZE;
	}
	
	public void SetEntity() {
		
		gp.entity[1] = new Entity_1(gp);
		gp.entity[1].worldX=4*gp.TILE_SIZE;
		gp.entity[1].worldY=2*gp.TILE_SIZE;
		
		gp.entity[2] = new Entity_1(gp);
		gp.entity[2].worldX=1*gp.TILE_SIZE;
		gp.entity[2].worldY=2*gp.TILE_SIZE;
		
		gp.entity[0] = new Entity_1(gp);
		gp.entity[0].worldX=1*gp.TILE_SIZE;
		gp.entity[0].worldY=1*gp.TILE_SIZE;
		
		gp.entity[3] = new Entity_1(gp);
		gp.entity[3].worldX=1*gp.TILE_SIZE;
		gp.entity[3].worldY=3*gp.TILE_SIZE;
	}
	
	public void createObject(int x, int y, String objects) {
		int i = 0;
		while(gp.obj[i] != null) {
			i++;
		}
		switch(objects) {
		case "Boots":
			gp.obj[i] = new Object_Boots();
			gp.obj[i].worldX=x;
			gp.obj[i].worldY=y;
			break;
		case "Key":
			gp.obj[i] = new Object_Key();
			gp.obj[i].worldX=x;
			gp.obj[i].worldY=y;
			break;
		case "Door":
			gp.obj[i] = new Object_Door();
			gp.obj[i].worldX=x;
			gp.obj[i].worldY=y;
			break;
		case "Chest":
			gp.obj[i] = new Object_Chest();
			gp.obj[i].worldX=x;
			gp.obj[i].worldY=y;
			break;
		}				
	}
}
