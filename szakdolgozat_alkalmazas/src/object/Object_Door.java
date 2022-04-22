package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Object_Door extends Entity {
	GamePanel gp;
	 public Object_Door(GamePanel gp) {
		 super(gp);
		 name ="Door";
		 try {
			 down1 = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			 
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		 collision = true;
		 typeName = "Door";
		 solidArea = new Rectangle(10,
					10,
					28,
					28);
		 solidAreaDefaultX = 0;
		 solidAreaDefaultY = 0;
	 }
}