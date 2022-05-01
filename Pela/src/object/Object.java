package object;

import entity.*;
import main.GamePanel;

public class Object extends Entity {
    public int type;
    public String typeName;
    public String name;

    public Object(GamePanel gp, int type, String typeName, String name) {
        super(gp);

        this.type = type;
        this.typeName = typeName;
        this.name = name;
    }
    
}
