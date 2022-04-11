package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int speed;
    
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    public Rectangle solidArea;
    public boolean collisionOn = false;
    public int solidAreaDefaultX, solidAreaDefaultY;
    
    public int maxLife;
    public int life;
    
    public boolean moved = false;
    public int healTurn = 0;
}
