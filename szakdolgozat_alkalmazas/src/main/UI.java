package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import entity.Entity;
import entity.Entity_1;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	
	//font
	Font font_15;
	Font font_25;
	Font font_40;
	Font arial_40;
	
	//for slots in inventory
	public int slotCol = 1;
	public int slotRow = 1;
	public int maxSlotCol = 4;
	public int maxSlotRow = 2;
	
	//for menu
	public int chosenMenuNumber = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
				
		try {			
			InputStream is = getClass().getResourceAsStream("/ttf/customfont.ttf");
			font_15 = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(15f);
			is = getClass().getResourceAsStream("/ttf/customfont.ttf");
			font_25 = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(25f);
			is = getClass().getResourceAsStream("/ttf/customfont.ttf");
			font_40 = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(40f);
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
	}
	
	public void draw(Graphics2D g2, ArrayList<Entity> entityList) {
		this.g2 = g2;

		switch(gp.gameScreenNumber) {
		case "normal":
			if(gp.isPaused) {
				drawPauseScreen();
			}
			if(gp.statIsVisible) {
				drawStatWindow(entityList.get(gp.entityIndex));	
				if(gp.inventoryIsVisible) {
					drawInventoryWindow(entityList.get(gp.entityIndex));
				}
			}
			break;
		case "end":
			drawEndScreen();
			break;
		case "title":
			drawTitleScreen();
			break;
		}
	}
	
	public void drawInventoryWindow(Entity entity) {
		
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		//WINDOW
		int windowX = gp.TILE_SIZE*5 + 30;
		int windowY = gp.screenHeight/2-gp.TILE_SIZE*4;
		final int width = gp.TILE_SIZE*5;
		final int height = gp.TILE_SIZE*9;
		String text = "Inventory";
		
		g2.setColor(Color.gray);
		g2.fillRect(windowX, windowY, width, height);
		
		g2.setColor(Color.black);
		g2.fillRect(windowX+5, windowY+20, width-10, height-40);
		
		g2.setFont(font_15);
		g2.setColor(Color.white);
		
		g2.drawString(text, windowX+5, windowY+16);
		
		//INVENTORY SLOTS
		int startFromX = windowX +20;
		int startFromY = windowY +40;
		int slotX = startFromX;
		int slotY = startFromY;
		
		//INVENTORY BACKGROUND
		Color myWhite = new Color(255, 191, 128);
		g2.setColor(myWhite);
		
		for(int i = 0; i<maxSlotCol; i++) {
			for(int j = 0; j<maxSlotRow; j++) {
				g2.fillRect(startFromX, startFromY + (gp.TILE_SIZE * j), gp.TILE_SIZE*4, gp.TILE_SIZE);
			}
		}
		
		//EVERY SLOT
		g2.setColor(Color.gray);
		g2.setStroke(new BasicStroke(4));
		
		for(int i = 0; i<maxSlotCol; i++) {
			for(int j = 0; j<maxSlotRow; j++) {
				g2.drawRect(startFromX + (gp.TILE_SIZE * i), startFromY + (gp.TILE_SIZE * j), gp.TILE_SIZE, gp.TILE_SIZE);
			}
		}
		
		//ITEMS
		for(int i = 0; i < entity.inventoryList.size(); i++) {
			g2.drawImage(entity.inventoryList.get(i).down1.getScaledInstance(gp.TILE_SIZE, gp.TILE_SIZE, Image.SCALE_DEFAULT), slotX, slotY, null);


			slotX += gp.TILE_SIZE;
			
			if(i == 3) {
				slotX = startFromX;
				slotY += gp.TILE_SIZE;
			}
		}
		
		//ITEM STATS
		int itemIndex = getInventoryIndex();
		if(itemIndex  < entity.inventoryList.size()) {
			drawItemStatistics(startFromX,startFromY,entity.inventoryList.get(itemIndex));

		}
		
		//CHOSEN SLOT
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(4));
		g2.drawRect(startFromX + (gp.TILE_SIZE * (slotCol-1)), startFromY + (gp.TILE_SIZE * (slotRow-1)), gp.TILE_SIZE, gp.TILE_SIZE);
		
		
	}
	
	public void drawItemStatistics(int x, int y, Entity entity) {
		
		int startFromX = x - 10;
		int startFromY = y + (gp.TILE_SIZE*(maxSlotRow+1)-20);
		String[] names = {"Str","Vit","Eva","Acc","Equipped:"};
		int emptiness = 30;
		
		g2.setFont(font_25);
		g2.setColor(Color.white);
		g2.drawString(entity.typeName, startFromX+5, startFromY+50+emptiness*0);
		
		if(entity.type==2) {
			for(int i=0; i<names.length; i++) {
				g2.setFont(font_25);
				g2.setColor(Color.white);
				g2.drawString(names[i], startFromX+5, startFromY+50+emptiness*(i+1));
				
			}		

			String value;
			int i=1;
			value=String.valueOf(entity.str);
			g2.setFont(font_25);
			g2.setColor(Color.white);
			g2.drawString(value, x+startFromX-gp.TILE_SIZE*2-textLength(g2,"_"), startFromY+50+emptiness*i);
			i++;
			value=String.valueOf(entity.vit);
			g2.setFont(font_25);
			g2.setColor(Color.white);
			g2.drawString(value, x+startFromX-gp.TILE_SIZE*2-textLength(g2,"_"), startFromY+50+emptiness*i);
			i++;
			value=String.valueOf(entity.eva);
			g2.setFont(font_25);
			g2.setColor(Color.white);
			g2.drawString(value, x+startFromX-gp.TILE_SIZE*2-textLength(g2,"_"), startFromY+50+emptiness*i);
			i++;
			value=String.valueOf(entity.acc);
			g2.setFont(font_25);
			g2.setColor(Color.white);
			g2.drawString(value, x+startFromX-gp.TILE_SIZE*2-textLength(g2,"_"), startFromY+50+emptiness*i);
			i++;
			value=String.valueOf(entity.isEquipped);
			g2.setFont(font_25);
			g2.setColor(Color.white);
			g2.drawString(value, x+startFromX-gp.TILE_SIZE*2-textLength(g2,"___"), startFromY+50+emptiness*i);
		}
		
	}
	
	public int getInventoryIndex() {
		int itemIndex = (slotCol-1) + ((slotRow-1)*4);
		return itemIndex;
	}

	public void drawStatWindow(Entity entity) {
		
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

		
		//WINDOW
		int x = 20;
		int y = gp.screenHeight/2-gp.TILE_SIZE*4;
		final int width = gp.TILE_SIZE*5;
		final int height = gp.TILE_SIZE*9;
		String text = "Statistics";
		String[] names = {"Number:","Level","Exp","ReqExp","HP","Str","Vit","Eva","Acc"};
		int emptiness = 30;
		
		
		
		g2.setColor(Color.gray);
		g2.fillRect(x, y, width, height);
		
		g2.setColor(Color.black);
		g2.fillRect(x+5, y+20, width-10, height-40);
		
		g2.setFont(font_15);
		g2.setColor(Color.white);
		
		g2.drawString(text, x+5, y+16);
		
		//NAMES
		for(int i=0; i<names.length; i++) {
			g2.setFont(font_25);
			g2.setColor(Color.white);
			g2.drawString(names[i], x+5, y+50+emptiness*i);
			
		}
		
		String value;
		
		//VALUES
		int i=0;
		value=String.valueOf(gp.entityIndex+1);
		g2.setFont(font_25);
		g2.setColor(Color.white);
		g2.drawString(value, x + textLength(g2,names[i]) + textLength(g2,value), y+50+emptiness*i);
		i++;
		value=String.valueOf(entity.lvl);
		g2.setFont(font_25);
		g2.setColor(Color.white);
		g2.drawString(value, x+width-50, y+50+emptiness*i);
		i++;
		value=String.valueOf(entity.exp);
		g2.setFont(font_25);
		g2.setColor(Color.white);
		g2.drawString(value, x+width-50, y+50+emptiness*i);
		i++;
		value=String.valueOf(entity.reqExp);
		g2.setFont(font_25);
		g2.setColor(Color.white);
		g2.drawString(value, x+width-50, y+50+emptiness*i);
		i++;
		value=String.valueOf(entity.life);
		g2.setFont(font_25);
		g2.setColor(Color.white);
		g2.drawString(value, x+width-50, y+50+emptiness*i);
		i++;
		value=String.valueOf(entity.str);
		g2.setFont(font_25);
		g2.setColor(Color.white);
		g2.drawString(value, x+width-50, y+50+emptiness*i);
		i++;
		value=String.valueOf(entity.vit);
		g2.setFont(font_25);
		g2.setColor(Color.white);
		g2.drawString(value, x+width-50, y+50+emptiness*i);
		i++;
		value=String.valueOf(entity.eva);
		g2.setFont(font_25);
		g2.setColor(Color.white);
		g2.drawString(value, x+width-50, y+50+emptiness*i);
		i++;
		value=String.valueOf(entity.acc);
		g2.setFont(font_25);
		g2.setColor(Color.white);
		g2.drawString(value, x+width-50, y+50+emptiness*i);
		
		//BUTTONS
		g2.setColor(Color.white);
		g2.setFont(font_15);
		text = "PRESS Q";
		g2.drawString(text, x+5, y+5 + height-10);
		
		text = "PRESS E";
		g2.drawString(text, x+width-5-textLength(g2,text), y+5 + height-10);		
	}
	
	//PAUSE SCREEN
	public void drawPauseScreen() {
		
		String text;
		int x;
		int y;
		text = "Paused";
		
		g2.setFont(font_40);
		g2.setColor(Color.white);
		
		x = gp.TILE_SIZE/2;
		y = gp.TILE_SIZE;
		
		g2.drawString(text, x, y);
	}
	
	//END SCREEN
	public void drawEndScreen() {
		
		g2.setFont(font_40);
		if(gp.winnerFaction == "Red") {
			g2.setColor(Color.red);
		}
		g2.setColor(Color.blue);
		
		String text;
		int x;
		int y;
		
		text = "End";
		x = gp.screenWidth/2 - textLength(g2,"End of Simulation")/2;
		y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
		
		g2.setColor(Color.white);
		
		text = "of Simulation";
		x = gp.screenWidth/2 - textLength(g2,"End of Simulation")/2 + textLength(g2,"End ");
		y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
		
		gp.gameThread = null;
	}
	
	//TITLE SCREEN
	public void drawTitleScreen() {
		
		
		g2.setColor(Color.white);
		
		String text;
		int x;
		int y;
		int i;
		
		//MAIN MENU TEXT
		i=0;
		g2.setFont(font_40);
		text = "MAIN MENU";
		x = gp.screenWidth/2 - textLength(g2,text)/2;
		y = gp.screenHeight/8;
		g2.drawString(text, x, y);
		
		//START
		i=0;
		g2.setFont(font_40);
		text = "START";
		x = gp.screenWidth/2 - textLength(g2,text)/2;
		y = gp.screenHeight/8+400;
		g2.drawString(text, x, y);
		g2.setFont(arial_40);
		if(chosenMenuNumber == 0) {
			while((textLength(g2,"_")*i)+10<textLength(g2,text)) {
				g2.drawString("_", x + textLength(g2,"_")*i, y);
				i++;
			}
		}
		
		//QUIT
		g2.setFont(font_40);
		text = "QUIT";
		x = gp.screenWidth/2 - textLength(g2,text)/2;
		y = gp.screenHeight/8+450;
		g2.drawString(text, x, y);
		g2.setFont(arial_40);
		if(chosenMenuNumber == 1) {
			while((textLength(g2,"_")*i)+10<textLength(g2,text)) {
				g2.drawString("_", x + textLength(g2,"_")*i, y);
				i++;
			}
		}
	}
	
	//TEXT LENGTH
	public int textLength(Graphics2D g2, String text) {
		int textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		return textLength;
	}
}