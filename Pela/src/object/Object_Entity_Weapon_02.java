package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Object_Entity_Weapon_02 extends Entity {
	
	GamePanel gp;
	
	 public Object_Entity_Weapon_02(GamePanel gp) {
		 
		 super(gp);
		 
		 name ="Entity_Weapon_02";
		 try {
			 down1 = ImageIO.read(getClass().getResourceAsStream("/objects/Entity_Weapon_02.png"));
			 
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		 
		 typeName = "Weapon";
		 type=2;
		 
	 }
}
