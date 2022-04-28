package object;



import entity.Entity;
import main.GamePanel;

public class Object_Chest extends Entity {
	
	GamePanel gp;
	
	public Object_Chest(GamePanel gp) {
		super(gp);

		typeName = "Chest";
		}
}
