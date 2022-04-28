package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Entity_Faction1 extends Entity_1 {

	public Entity_Faction1(GamePanel gp) {
		super(gp);
		
		faction = 1;
		loadImage();
	}

    public void loadImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/faction1/agent_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/faction1/agent_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/faction1/agent_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/faction1/agent_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/faction1/agent_left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/faction1/agent_left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/faction1/agent_right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/faction1/agent_right2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
