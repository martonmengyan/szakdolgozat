package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Object_Entity_Armor_02 extends Entity {
	
	GamePanel gp;
	
	 public Object_Entity_Armor_02(GamePanel gp) {
		 
		 super(gp);
		 
		 name ="Entity_Armor_02";
		 try {
			 down1 = ImageIO.read(getClass().getResourceAsStream("/objects/Entity_Armor_02.png"));
			 
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		 
		 typeName = "Armor";
		 type=2;
		 itemStr = 0;
		 itemVit = 0;
		 itemEva = 0;
		 itemAcc = 0;
	 }
}
