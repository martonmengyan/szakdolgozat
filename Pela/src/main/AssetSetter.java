package main;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.*;
import object.*;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp=gp;
	}
	
	public void setObject() {
		
		//Keys
		createEntity(0,1,1,new Object_Key(gp), gp.objectList);
		
		//Doors
		createEntity(1,7,2,new Object_Door(gp), gp.objectList);
		createEntity(5,3,7,new Object_Door(gp), gp.objectList);
		createEntity(9,11,2,new Object_Door(gp), gp.objectList);
		
		//Equipable Items
		createEntity(3,4,2,new Object_Entity_Helmet_02(gp), gp.objectList);
		createEntity(3,4,3,new Object_Entity_Weapon_02(gp), gp.objectList);
		createEntity(3,4,1,new Object_Entity_Armor_02(gp), gp.objectList);
		createEntity(3,5,2,new Object_Entity_Helmet_02(gp), gp.objectList);
		createEntity(3,5,3,new Object_Entity_Weapon_02(gp), gp.objectList);
		createEntity(3,5,1,new Object_Entity_Armor_02(gp), gp.objectList);
		//Chest
		//createEntity(2,3,2,new Object_Chest_1(gp), gp.objectList);
		//createEntity(2,3,5,new Object_Chest_2(gp), gp.objectList);
	
		//Bricks
		createEntity(4,3,3,new Object_Brick(gp), gp.objectList);	
	}
	
	public void SetEntity() {

		//Faction0
		//createEntity(0,1,2,new Entity_Faction0(gp), gp.entityList);
		//createEntity(1,1,3,new Entity_Faction0(gp), gp.entityList);
		
		//Faction1
		createEntity(2,2,1,new Entity_Faction1(gp), gp.entityList);
		//createEntity(3,1,4,new Entity_Faction1(gp), gp.entityList);
		
	}
	
	
	public void createEntity(int objID, int x, int y, Entity object, ArrayList<Entity> ArrayList){
        ArrayList.add(object);
        ArrayList.get(ArrayList.size()-1).worldX=x*gp.TILE_SIZE;
        ArrayList.get(ArrayList.size()-1).worldY=y*gp.TILE_SIZE;
    }
	
	public void createObject(int x, int y, int type, int faction, String typeName, String name, String imagePath, Entity object, ArrayList<Entity> ArrayList){
        ArrayList.add(object);
        ArrayList.get(ArrayList.size()-1).worldX=x*gp.TILE_SIZE;
        ArrayList.get(ArrayList.size()-1).worldY=y*gp.TILE_SIZE;
        ArrayList.get(ArrayList.size()-1).type=type;
        ArrayList.get(ArrayList.size()-1).faction=faction;
        ArrayList.get(ArrayList.size()-1).typeName=typeName;
        ArrayList.get(ArrayList.size()-1).name=name;        
        try {
			ArrayList.get(ArrayList.size()-1).down1=ImageIO.read(getClass().getResourceAsStream(imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
