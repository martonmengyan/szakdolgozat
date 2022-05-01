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
		
		createObject(46,2,2, "/objects/Entity_Armor_01.png", new Object(gp,2, "Armor", "Entity_Armor_01"), gp.objectList);
		createObject(30,8,2, "/objects/Entity_Armor_01.png", new Object(gp,2, "Armor", "Entity_Armor_01"), gp.objectList);
		createObject(2,40,2, "/objects/Entity_Armor_02.png", new Object(gp,2, "Armor", "Entity_Armor_02"), gp.objectList);
		
		createObject(45,18,2, "/objects/Entity_Weapon_01.png", new Object(gp,2,"Weapon", "Entity_Weapon_01"), gp.objectList);
		createObject(15,5,2, "/objects/Entity_Weapon_01.png", new Object(gp,2, "Weapon", "Entity_Weapon_01"), gp.objectList);
		createObject(4,46,2, "/objects/Entity_Weapon_02.png", new Object(gp,2, "Weapon", "Entity_Weapon_02"), gp.objectList);
		createObject(32,18,2, "/objects/Entity_Weapon_02.png", new Object(gp,2, "Weapon", "Entity_Weapon_02"), gp.objectList);

		createObject(6,21,2, "/objects/Entity_Helmet_01.png", new Object(gp,2, "Helmet", "Entity_Helmet_01"), gp.objectList);
		createObject(39,9,2, "/objects/Entity_Helmet_02.png", new Object(gp,2, "Helmet", "Entity_Helmet_02"), gp.objectList);
		createObject(30,35,2, "/objects/Entity_Helmet_02.png", new Object(gp,2, "Helmet", "Entity_Helmet_02"), gp.objectList);
		
		//Chest_Blue		
		createObject(2,4,1, "/objects/chest_1.png", new Object(gp,0,"Chest", "Chest_Blue"), gp.objectList);
		
		//Chest_Red
		createObject(44,44,0, "/objects/chest_0.png", new Object(gp,0,"Chest", "Chest_Red"), gp.objectList);
		
		//Bricks
		createObject(3,3,2, "/objects/object_brick.png", new Object(gp,0,"Brick", "Brick"), gp.objectList);
		createObject(43,25,2, "/objects/object_brick.png", new Object(gp,0,"Brick", "Brick"), gp.objectList);
		createObject(36,34,2, "/objects/object_brick.png", new Object(gp,0,"Brick", "Brick"), gp.objectList);
		createObject(46,18,2, "/objects/object_brick.png", new Object(gp,0,"Brick", "Brick"), gp.objectList);
		createObject(43,5,2, "/objects/object_brick.png", new Object(gp,0,"Brick", "Brick"), gp.objectList);
		createObject(30,7,2, "/objects/object_brick.png", new Object(gp,0,"Brick", "Brick"), gp.objectList);
		createObject(15,4,2, "/objects/object_brick.png", new Object(gp,0 ,"Brick", "Brick"), gp.objectList);
		createObject(29,35,2, "/objects/object_brick.png", new Object(gp, 0,"Brick", "Brick"), gp.objectList);
		createObject(32,17,2, "/objects/object_brick.png", new Object(gp,0,"Brick", "Brick"), gp.objectList);
		createObject(41,43,2, "/objects/object_brick.png", new Object(gp,0,"Brick", "Brick"), gp.objectList);
		createObject(18,13,2, "/objects/object_brick.png", new Object(gp,0,"Brick", "Brick"), gp.objectList);
		createObject(16,35,2,"/objects/object_brick.png", new Object(gp,0,"Brick", "Brick"), gp.objectList);
		createObject(25,34,2, "/objects/object_brick.png", new Object(gp,0,"Brick", "Brick"), gp.objectList);

		//Door
		
		createObject(7,2,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(3,7,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(8,11,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(4,17,2,"/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(10,23,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(7,34,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(23,23,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(15,30,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(18,9,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(21,36,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(21,14,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(23,40,2,"/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(17,42,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(16,48,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(26,29,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(35,44,2, "/objects/door.png", new Object(gp,0 ,"Door", "Door"), gp.objectList);
		createObject(39,28,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(43,34,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(47,22,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(39,16,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(48,10,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(37,1,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(22,3,2,"/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(35,40,2, "/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(34,24,2,"/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		createObject(27,20,2,"/objects/door.png", new Object(gp,0,"Door", "Door"), gp.objectList);
		
		//Key
		createObject(2,1,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(3,4,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);

		createObject(46,48,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(44,47,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);

		createObject(2,12,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(3,24,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(18,22,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(18,14,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(9,42,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(16,34,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(19,37,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(25,33,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(12,3,2,"/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(15,13,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(45,6,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(30,4,2,"/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(28,44,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(44,15,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(27,17,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(31,14,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(42,27,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(45,24,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(28,24,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(37,32,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(27,36,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		createObject(36,36,2, "/objects/key.png", new Object(gp,0,"Key", "Key"), gp.objectList);
		
	}
	
	public void SetEntity() {

		//Faction0
		createEntity(46,48,new Entity_Faction0(gp), gp.entityList);
		createEntity(44,47,new Entity_Faction0(gp), gp.entityList);
		
		//Faction1
		createEntity(2,1,new Entity_Faction1(gp), gp.entityList);
		createEntity(3,4,new Entity_Faction1(gp), gp.entityList);
		
	}
	
	
	public void createEntity(int x, int y, Entity object, ArrayList<Entity> ArrayList){
        ArrayList.add(object);
        ArrayList.get(ArrayList.size()-1).worldX=x*gp.TILE_SIZE;
        ArrayList.get(ArrayList.size()-1).worldY=y*gp.TILE_SIZE;
    }
	
	public void createObject(int x, int y, int faction, String imagePath, Object object, ArrayList<Object> objectList){
        objectList.add(object);
        objectList.get(objectList.size()-1).worldX=x*gp.TILE_SIZE;
        objectList.get(objectList.size()-1).worldY=y*gp.TILE_SIZE;
        objectList.get(objectList.size()-1).faction=faction;     
        try {
			objectList.get(objectList.size()-1).down1=ImageIO.read(getClass().getResourceAsStream(imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
