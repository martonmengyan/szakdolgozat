package entity;

import java.util.Random;


import main.GamePanel;
import object.*;

public class Entity_1 extends Entity {

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
		Random random = new Random();
		str=random.nextInt(2 + 1) - 1;
    	vit=random.nextInt(2 + 1) - 1;
    	eva=random.nextInt(2 + 1) - 1;
    	acc=random.nextInt(2 + 1) - 1;
    	
		maxLife=12;
    	life = maxLife;
    	
    	currentWeapon = new Object_Entity_Weapon_01(gp);
    	currentArmor = new Object_Entity_Armor_01(gp);
    	currentHelmet = new Object_Entity_Helmet_01(gp);
    	key = new Object_Key(gp);
    	
    	setItemStat(currentWeapon);
    	setItemStat(currentArmor);
    	setItemStat(currentHelmet);
    	

	}
	
	public void setItems() {
		inventoryList.add(currentWeapon);
		inventoryList.add(currentArmor);
		inventoryList.add(currentHelmet);
		//inventory.add(key);
	}
	
	public void setItemStat(Entity entity) {
		Random random = new Random();
		entity.str=random.nextInt(3 + 2) - 2;
		entity.vit=random.nextInt(3 + 2) - 2;
		entity.eva=random.nextInt(3 + 2) - 2;
		entity.acc=random.nextInt(3 + 2) - 2;
	}
    
	public void equipStats(Entity entity) {
		str += entity.str;
		vit += entity.vit; 
		eva += entity.eva; 
		acc += entity.acc; 
		entity.isEquipped=true;
	}
    
	    
}