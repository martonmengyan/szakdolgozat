package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Object_Chest extends Entity {
	
	GamePanel gp;
	
	 public Object_Chest(GamePanel gp) {
		super(gp);
		 
		name ="Chest";
		try {
			down1 = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
				 
		}catch(IOException e) {
			 e.printStackTrace();
		}
		collision = true;
		 solidArea = new Rectangle(10,
					10,
					28,
					28);
		 solidAreaDefaultX = 0;
		 solidAreaDefaultY = 0;
		}
}
