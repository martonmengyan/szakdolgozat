package entity;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import object.*;

public class Entity_1 extends Entity {

	public Entity_1(GamePanel gp) {
		super(gp);
		
		solidArea = new Rectangle(10,
				10, 
				28, 
				28);
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		loadImage();
		setDefaultValues();
		setItems();
    	equipStats(inventoryList.get(1));
    	equipStats(currentArmor);
    	equipStats(currentHelmet);
	}
	
	public void setDefaultValues() {
		type=2;
		direction = "down";
		speed = gp.TILE_SIZE;
		
		str=1;
    	vit=4;
    	eva=1;
    	acc=1;
    	
		maxLife=vit;
    	life = maxLife;
    	
    	exp=0;
    	reqExp=10;
    	
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
	
	
    public void loadImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left11.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left22.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right11.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right22.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setAction() {
		Random random = new Random();    	

    	do {
    		int index = random.nextInt(1000)+1;
    		collisionOn = false;
    		if(index<=250) {
    			testDirection="up";
        	}else if(index<=500 && index>250) {
        		testDirection="left";
        	}else if(index<=750 && index>500) {
        		testDirection="right";
        	}else if(index<=1000 && index>750) {
        		testDirection="down";
        	}
			int objIndex = gp.colChecker.checkObject(this, true);
	    	int entityIndex = gp.colChecker.checkEntity(this, gp.entityList);
	    	gp.colChecker.checkTile(this);
    		if(collisionOn == true) {
    			System.out.println("WRONG DIRECTION");
    		}
    	} while(collisionOn != false);
    	direction = testDirection;
    }
    
	public void equipStats(Entity entity) {
		str += entity.str;
		vit += entity.vit; 
		eva += entity.eva; 
		acc += entity.acc; 
		entity.isEquipped=true;
	}
    
	    
}