package main;

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
}
