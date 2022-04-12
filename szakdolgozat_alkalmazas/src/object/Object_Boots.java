package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Object_Boots extends Entity {
	GamePanel gp;
	 public Object_Boots(GamePanel gp) {
		 super(gp);
		 name ="Boots";
		 try {
			 down1 = ImageIO.read(getClass().getResourceAsStream("/objects/boots.png"));
			 
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		 solidArea = new Rectangle(10,
					10,
					28,
					28);
		 solidAreaDefaultX = 0;
		 solidAreaDefaultY = 0;
	 }
}