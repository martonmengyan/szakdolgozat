package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Object_Chest_0 extends Object_Chest{

	public Object_Chest_0(GamePanel gp) {
		super(gp);
		
		faction = 0;
		name = "Chest_Red";
		try {
			down1 = ImageIO.read(getClass().getResourceAsStream("/objects/chest_0.png"));
				 
		}catch(IOException e) {
			 e.printStackTrace();
		}
	}

}
