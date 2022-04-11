package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {

	GamePanel gp;
	Graphics2D g2;
	
	//font
	Font arial_40;
	Font arial_60;
	
	//for menu
	public int chosenMenuNumber = 0;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_60 = new Font("Arial", Font.PLAIN, 60);
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		
		switch(gp.gameScreenNumber) {
		case "normal":
			drawPlayerCurrentHP();
			break;
		case "end":
			drawEndScreen();
			break;
		case "pause":
			drawPauseScreen();
			break;
		case "title":
			drawTitleScreen();
			break;
		}
	}
	
	public void drawPlayerCurrentHP() {
		
		g2.setFont(arial_60);
		g2.setColor(Color.red);
		
		int x;
		int y;
		int i;
		
		x = gp.TILE_SIZE/2;
		y = gp.TILE_SIZE/2;
		i=0;
		
		while(i<gp.player.life) {
			g2.drawString("-",x,y);
			i++;
			x += textLength(g2,"-");
		}
	}
	
	//PAUSE SCREEN
	public void drawPauseScreen() {
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		String text;
		int x;
		int y;
		
		text = "Paused the Simulation";
		x = gp.screenWidth/2 - textLength(g2,text)/2;
		y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	
	//END SCREEN
	public void drawEndScreen() {
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		String text;
		int x;
		int y;
		
		text = "End of Simulation";
		x = gp.screenWidth/2 - textLength(g2,text)/2;
		y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
		
		gp.gameThread = null;
	}
	
	//TITLE SCREEN
	public void drawTitleScreen() {
		
		g2.setFont(arial_40);
		g2.setColor(Color.white);
		
		String text;
		int x;
		int y;
		int i;
		
		//MAIN MENU TEXT
		i=0;
		text = "MAIN MENU";
		x = gp.screenWidth/2 - textLength(g2,text)/2;
		y = gp.screenHeight/8;
		g2.drawString(text, x, y);
		
		//START
		i=0;
		text = "START";
		x = gp.screenWidth/2 - textLength(g2,text)/2;
		y = gp.screenHeight/8+400;
		g2.drawString(text, x, y);
		if(chosenMenuNumber == 0) {
			while((textLength(g2,"_")*i)+10<textLength(g2,text)) {
				g2.drawString("_", x + textLength(g2,"_")*i, y);
				i++;
			}
		}
		
		//QUIT
		text = "QUIT";
		x = gp.screenWidth/2 - textLength(g2,text)/2;
		y = gp.screenHeight/8+450;
		g2.drawString(text, x, y);
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
