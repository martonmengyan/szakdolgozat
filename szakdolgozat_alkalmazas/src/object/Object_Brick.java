package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Object_Brick extends SuperObject {
	 public Object_Brick() {
		 name ="Brick";
		 try {
			 image = ImageIO.read(getClass().getResourceAsStream("/objects/object_brick.png"));
			 
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		 collision = true;
	 }
}

