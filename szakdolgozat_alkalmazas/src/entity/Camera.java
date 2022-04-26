package entity;

import main.GamePanel;
import main.KeyHandler;

public class Camera extends Entity {
	
	KeyHandler keyH;

    //PLAYER POSITION
    public final int screenX;
    public final int screenY;

    public Camera(GamePanel gp, KeyHandler keyH) {
    	
    	super(gp);
    	
    	this.keyH = keyH;
    	
    	screenX = gp.screenWidth/2 - (gp.TILE_SIZE/2);
    	screenY = gp.screenHeight/2 - (gp.TILE_SIZE/2);

        setDefaultValues();
    }
    
    private void setDefaultValues() {
    	
    	//STARTING COORDINATE
    	worldX = gp.TILE_SIZE * 2;
    	worldY = gp.TILE_SIZE * 2;
    	
    	//STARTING DIRECTION
    	direction = "down";
    	
    	speed = gp.TILE_SIZE;
    }

    public void update() {    	    	    	
    	
    	//DIRECTION
    	if(keyH.up == true || keyH.down == true || keyH.left == true || keyH.right == true) {
    	
	    	if(keyH.up == true) {
				direction ="up";
			}else if(keyH.down == true) {
				direction ="down";	
			}else if(keyH.left == true) {
				direction ="left";			
			}else if(keyH.right == true) {
				direction ="right";
			}   
	    	
	    		if(keyH.up == true || keyH.down == true || keyH.right == true || keyH.left == true) {
	    		
		    		switch(direction) {
		    		case "up":
		    			worldY -= 3*speed;
		    			moved = true;
		    			break;
		    		case "down":
		    			worldY += 3*speed;
		    			moved = true;
		    			break;
		    		case "left":
		    			worldX -= 3*speed;
		    			moved = true;
		    			break;
		    		case "right":
		    			worldX += 3*speed;
		    			moved = true;
		    			break;
		    		}
	    		}
    	}
    }
}