package main;

import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import entity.*;
import object.Object;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp=gp;
	}
	
	public void setObject() {

		//Equipable Items
		
		createObject(2,1,2,2, "Armor", "Entity_Armor_01", "/objects/Entity_Armor_01.png", new Object(gp), gp.objectList);
		createObject(2,1,2,2, "Armor", "Entity_Armor_02", "/objects/Entity_Armor_02.png", new Object(gp), gp.objectList);
		
		createObject(2,1,2,2, "Weapon", "Entity_Weapon_01", "/objects/Entity_Weapon_01.png", new Object(gp), gp.objectList);
		createObject(2,1,2,2, "Weapon", "Entity_Weapon_02", "/objects/Entity_Weapon_02.png", new Object(gp), gp.objectList);
		
		createObject(2,1,2,2, "Helmet", "Entity_Helmet_01", "/objects/Entity_Helmet_01.png", new Object(gp), gp.objectList);
		createObject(2,1,2,2, "Helmet", "Entity_Helmet_02", "/objects/Entity_Helmet_02.png", new Object(gp), gp.objectList);
		
		//Chest_Red
		//createObject(3,4,0,1,"Chest", "Chest_Blue", "/objects/chest_1.png", new Object(gp), gp.objectList);
		
		//Chest_Blue
		//createObject(2,4,0,0,"Chest", "Chest_Red", "/objects/chest_0.png", new Object(gp), gp.objectList);
		
		//Bricks
		createObject(3,3,0,2,"Brick", "Brick", "/objects/object_brick.png", new Object(gp), gp.objectList);
		
		//Door
		createObject(7,2,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(3,7,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(8,11,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(4,17,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(10,23,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(7,34,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(23,23,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(15,30,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(18,9,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(21,36,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(21,14,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(23,40,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(17,42,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(16,48,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(26,29,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(35,44,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(39,28,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(43,34,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(47,22,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(39,16,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(48,10,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(37,1,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		createObject(22,3,0,2,"Door", "Door", "/objects/door.png", new Object(gp), gp.objectList);
		
		//Key
		createObject(2,2,0,2,"Key", "Key", "/objects/key.png", new Object(gp), gp.objectList);

	}
	
	public void SetEntity() {

		//Faction0
		//createEntity(0,1,2,new Entity_Faction0(gp), gp.entityList);
		//createEntity(1,1,3,new Entity_Faction0(gp), gp.entityList);
		
		//Faction1
		createEntity(2,1,new Entity_Faction1(gp), gp.entityList);
		createEntity(1,3,new Entity_Faction0(gp), gp.entityList);
		
	}
	
	
	public void createEntity(int x, int y, Entity object, ArrayList<Entity> ArrayList){
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
