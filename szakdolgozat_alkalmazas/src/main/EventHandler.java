package main;

import java.awt.Rectangle;

public class EventHandler {

	GamePanel gp;
	Rectangle eventRectangle;
	int eventRectangleDefaultX, eventRectangleDefaultY;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		
		eventRectangle = new Rectangle(10,
									10,
									28,
									28);
		
		eventRectangleDefaultX = eventRectangle.x;
		eventRectangleDefaultY = eventRectangle.y;
		
	}
	
	public void createPotionEvent(int x, int y, String direction, int duration) {
		//create potion event for duration
	}
	
	public void checkPotionEvent() {
		
		if(hit(2,1) == true) {
			gp.player.life--;
		}
	}
	
	public boolean hit(int damageCol, int damageRow) {
		boolean hit = false;
		
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		eventRectangle.x = damageCol*gp.TILE_SIZE + eventRectangle.x;
		eventRectangle.y = damageRow*gp.TILE_SIZE + eventRectangle.y;
		
		if(gp.player.solidArea.intersects(eventRectangle)) {
			hit = true;
		}
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		eventRectangle.x = eventRectangleDefaultX;
		eventRectangle.y = eventRectangleDefaultY;
		
		return hit;
	}
}
