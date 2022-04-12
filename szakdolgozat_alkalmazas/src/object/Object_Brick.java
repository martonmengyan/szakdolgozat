package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Object_Brick extends Entity {
	GamePanel gp;
	 public Object_Brick(GamePanel gp) {
		 super(gp);
		 name ="Brick";
		 try {
			 down1 = ImageIO.read(getClass().getResourceAsStream("/objects/object_brick.png"));
			 
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

