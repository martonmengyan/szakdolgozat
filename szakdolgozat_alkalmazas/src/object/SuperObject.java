package object;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import main.GamePanel;

public class SuperObject {

	public BufferedImage image = null;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Point worldPos;
	public Rectangle solidArea = new Rectangle(0,0,48,48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	
	public void draw(Graphics2D g2, GamePanel gp, ImageObserver observer) {
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.TILE_SIZE > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.TILE_SIZE < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.TILE_SIZE > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.TILE_SIZE < gp.player.worldY + gp.player.screenY) {
			
			g2.drawImage(image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, observer);
		}
	}
}
