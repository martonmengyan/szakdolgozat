package main;

import java.awt.Rectangle;

import entity.Entity;

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
	
	public void checkPotionEvent(Entity entity) {
		
		if(hit(2,1,entity) == true) {
			entity.life--;
		}
	}
	
	public boolean hit(int damageCol, int damageRow, Entity entity) {
		boolean hit = false;
		
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		eventRectangle.x = damageCol*gp.TILE_SIZE + eventRectangle.x;
		eventRectangle.y = damageRow*gp.TILE_SIZE + eventRectangle.y;
		
		if(entity.solidArea.intersects(eventRectangle)) {
			hit = true;
		}
		
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		eventRectangle.x = eventRectangleDefaultX;
		eventRectangle.y = eventRectangleDefaultY;
		
		return hit;
	}
}
