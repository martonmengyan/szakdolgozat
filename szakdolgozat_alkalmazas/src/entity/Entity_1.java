package entity;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

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
	}
	
	private void setDefaultValues() {
		direction = "down";
		speed = gp.TILE_SIZE;
		
		maxLife=1;
    	life = maxLife;
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
	    	int index = random.nextInt(1000)+1;
	
	    	if(index<=250) {
	    		direction="up";
	    	}else if(index<=500 && index>250) {
	    		direction="left";
	    	}else if(index<=750 && index>500) {
	    		direction="right";
	    	}else if(index<=1000 && index>750) {
	    		direction="down";
	    	}
    	}
    
	    
}
