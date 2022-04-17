package main;

import java.util.ArrayList;
import java.util.Random;

import entity.*;
import object.*;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp=gp;
	}
	
	public void setObject() {
		
		createEntity(0,1,1,new Object_Key(gp), gp.objectList);
		createEntity(1,5,4,new Object_Door(gp), gp.objectList);
		createEntity(2,10,6,new Object_Chest(gp), gp.objectList);
		createEntity(3,10,10,new Object_Boots(gp), gp.objectList);
		createEntity(4,3,3,new Object_Brick(gp), gp.objectList);
		createEntity(5,1,5,new Object_Door(gp), gp.objectList);
		createEntity(6,6,1,new Object_Door(gp), gp.objectList);
	}
	
	public void SetEntity() {

		createEntity(0,4,2,new Entity_Faction0(gp), gp.entityList);
		//createEntity(1,1,2,new Entity_Faction0(gp), gp.entityList);
		//createEntity(2,1,4,new Entity_Faction1(gp), gp.entityList);
		createEntity(3,1,3,new Entity_Faction1(gp), gp.entityList);
		
	}
	
	
	public void createEntity(int objID, int x, int y, Entity object, ArrayList<Entity> ArrayList){
        ArrayList.add(object);
        ArrayList.get(ArrayList.size()-1).worldX=x*gp.TILE_SIZE;
        ArrayList.get(ArrayList.size()-1).worldY=y*gp.TILE_SIZE;
    }
	

}
