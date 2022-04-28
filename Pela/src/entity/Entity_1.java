package entity;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import object.Object;

public class Entity_1 extends Entity {

    public Object currentWeapon;
    public Object currentArmor;
    public Object currentHelmet;
    
	public Entity_1(GamePanel gp) {
		super(gp);
		
		
		setDefaultValues();
		setItems();
		
		equipStats(currentWeapon);
    	equipStats(currentArmor);
    	equipStats(currentHelmet);
    	
	}
	
	public void setDefaultValues() {
		type=2;
		direction = "right";
		speed = gp.TILE_SIZE;
		str=1;
    	vit=1;
    	eva=1;
    	acc=1;
    	
		maxLife=12;
    	life = maxLife;
    	
    	currentWeapon = new Object(gp);
    	currentArmor = new Object(gp);
    	currentHelmet = new Object(gp);
    	
    	setItemStat(currentWeapon, 2, "Weapon", "Entity_Weapon_01", "/objects/Entity_Weapon_01.png");
    	setItemStat(currentArmor, 2, "Armor", "Entity_Armor_01", "/objects/Entity_Armor_01.png");
    	setItemStat(currentHelmet, 2, "Helmet", "Entity_Helmet_01", "/objects/Entity_Helmet_01.png");
    	

	}
	
	public void setItems() {
		inventoryList.add(currentWeapon);
		inventoryList.add(currentArmor);
		inventoryList.add(currentHelmet);
		//inventory.add(key);
	}
	
	public void setItemStat(Object entity, int type, String typeName, String name, String imagePath) {
		Random random = new Random();
		entity.str=random.nextInt(3 + 2) - 2;
		entity.vit=random.nextInt(3 + 2) - 2;
		entity.eva=random.nextInt(3 + 2) - 2;
		entity.acc=random.nextInt(3 + 2) - 2;
		entity.type = type;
		entity.typeName = typeName;
		entity.name = name;
		try {
			entity.down1=ImageIO.read(getClass().getResourceAsStream(imagePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	public void equipStats(Object entity) {
		str += entity.str;
		vit += entity.vit; 
		eva += entity.eva; 
		acc += entity.acc; 
		entity.isEquipped=true;
	}
    
	    
}