package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Object_Door extends SuperObject {
	 public Object_Door() {
		 name ="Door";
		 try {
			 image = ImageIO.read(getClass().getResourceAsStream("/objects/door.png"));
			 
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		 collision = true;
	 }
}