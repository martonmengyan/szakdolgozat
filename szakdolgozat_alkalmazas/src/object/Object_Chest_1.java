package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Object_Chest_1 extends Object_Chest{

	public Object_Chest_1(GamePanel gp) {
		super(gp);
		
		faction = 1;
		name = "Chest_Blue";
		try {
			down1 = ImageIO.read(getClass().getResourceAsStream("/objects/chest_1.png"));
				 
		}catch(IOException e) {
			 e.printStackTrace();
		}
	}

}
