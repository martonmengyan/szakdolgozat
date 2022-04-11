package entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;

    //PLAYER POSITION
    private Point screenPos;
    public final int screenX;
    public final int screenY;
    
    int ammountKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
    	
    	this.gp = gp;
    	this.keyH = keyH;
    	
    	screenX = gp.screenWidth/2 - (gp.TILE_SIZE/2);
    	screenY = gp.screenHeight/2 - (gp.TILE_SIZE/2);
    	screenPos = new Point(screenX,screenY);
    	
    	//hitbox
    	solidArea = new Rectangle(10,
    							10, 
    							28, 
    							28);
    	
    	solidAreaDefaultX = solidArea.x;
    	solidAreaDefaultY = solidArea.y;

        loadImage();

        setDefaultValues();
    }
    
    private void setDefaultValues() {
    	
    	//STARTING COORDINATE
    	worldX = gp.TILE_SIZE * 2;
    	worldY = gp.TILE_SIZE * 2;
    	
    	//STARTING DIRECTION
    	direction = "down";
    	
    	speed = gp.TILE_SIZE;
    	
    	//STARTING HP
    	maxLife=10;
    	life = maxLife;
    }

    private void loadImage() {
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
    
    public void pickUpObject(int i) {
    	
    	if(i != 999) {
    		
    		String objectName = gp.obj[i].name;
    		
    		switch(objectName) {
    		case "Key":
    			ammountKey++;
    			gp.obj[i] = null;
    			System.out.println("Key:"+ammountKey);
    			break;
    		case "Door":
    			if(ammountKey > 0) {
    				gp.obj[i] = null;
    				ammountKey--;
    			}
    			System.out.println("Key:"+ammountKey);
    			break;
    		case "Boots":
    			//utasítás
    			gp.obj[i] = null;
    			break;
    		case "Chest":
    			gp.gameScreenNumber = "end";
    			break;
    		}
    	}
    }
    
    public void playerHPRegenerate() {
    	if(true) {
    		gp.player.life++;
    	}
    }

    public void draw(Graphics2D g2, ImageObserver observer) {

    	BufferedImage image = null;
        
        switch(direction) {
        case "up":
        	if(spriteNum == 1) {
        		image = up1;
        	}
        	if(spriteNum == 2) {
        		image = up2;
        	}
        	break;
        case "down":
        	if(spriteNum == 1) {
        		image = down1;
        	}
        	if(spriteNum == 2) {
        		image = down2;
        	}
        	break;
        case "left":
        	if(spriteNum == 1) {
        		image = left1;
        	}
        	if(spriteNum == 2) {
        		image = left2;
        	}
        	break;
        case "right":
        	if(spriteNum == 1) {
        		image = right1;
        	}
        	if(spriteNum == 2) {
        		image = right2;
        	}
        	break;
        }
        g2.drawImage(image, screenPos.x, screenPos.y, gp.TILE_SIZE, gp.TILE_SIZE, observer);
        
    }
    
    public void update() {
    	    	
    	if(moved==true) {
	    	
    		//heal 1 HP once per 3 turn until its max life
    		if(gp.player.life < gp.player.maxLife) {
	        	if(healTurn == 2) {
	        		playerHPRegenerate();
	        		healTurn = 0;
	        	}else healTurn++;
    		}
    		
	    	//event check
	    	gp.eventH.checkPotionEvent();
	    	
	    	gp.player.moved=false;
	    	
    	}
    	
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
	    	
	    	//collision check
	    	collisionOn = false;
	    	gp.colChecker.checkTile(this);
	    	
	    	int objIndex = gp.colChecker.checkObject(this, true);
	    	
	    	if(keyH.wantToPickUp == true) {
	    		pickUpObject(objIndex);
	    		keyH.wantToPickUp = false;
	    		}

	    	//movement if possible once
	    	if(collisionOn == false) {
	    		if(keyH.up == true || keyH.down == true || keyH.right == true || keyH.left == true) {
	    		
		    		switch(direction) {
		    		case "up":
		    			worldY -= speed;
		    			keyH.up = false;
		    			System.out.println("moved to up");
		    			gp.player.moved = true;
		    			break;
		    		case "down":
		    			worldY += speed;
		    			keyH.down = false;
		    			System.out.println("moved to down");
		    			gp.player.moved = true;
		    			break;
		    		case "left":
		    			worldX -= speed;
		    			keyH.left = false;
		    			System.out.println("moved to left");
		    			gp.player.moved = true;
		    			break;
		    		case "right":
		    			worldX += speed;
		    			keyH.right = false;
		    			System.out.println("moved to right");
		    			gp.player.moved = true;
		    			break;
		    		}
	    		}
	    	
		    	spriteCounter++;
		    	if(spriteCounter >= 1) {
		    		if(spriteNum == 1) {
		    			spriteNum = 2;
		    		}
		    		else if(spriteNum == 2) {
		    			spriteNum = 1;
		    		}
		    		spriteCounter = 0;
		    	}
	    	}
    	}
    }
}