package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Object_Entity_Helmet_02 extends Entity {
	
	GamePanel gp;
	
	 public Object_Entity_Helmet_02(GamePanel gp) {
		 
		 super(gp);
		 
		 name ="Entity_Helmet_02";
		 try {
			 down1 = ImageIO.read(getClass().getResourceAsStream("/objects/Entity_Helmet_02.png"));
			 
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		 
		 typeName = "Helmet";
		 type=2;

	 }
}
