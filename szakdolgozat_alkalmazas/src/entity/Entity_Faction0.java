package entity;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Entity_Faction0 extends Entity_1 {

	public Entity_Faction0(GamePanel gp) {
		super(gp);
		
		faction = 1;
		loadImage();
	}

	
    public void loadImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/player_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/player_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/player_down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/player_down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/player_left11.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/player_left22.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/player_right11.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/player_right22.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
