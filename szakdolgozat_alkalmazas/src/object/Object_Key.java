package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Object_Key extends Entity {
	
	GamePanel gp;
	
	 public Object_Key(GamePanel gp) {
		 
		 super(gp);
		 
		 name ="Key";
		 try {
			 down1 = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
			 
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		 type=0;
		 solidArea = new Rectangle(10,
					10,
					28,
					28);
		 solidAreaDefaultX = 0;
		 solidAreaDefaultY = 0;
		 typeName = "Key";
	 }
}
