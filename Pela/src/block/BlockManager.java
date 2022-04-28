package block;

import java.awt.Graphics2D;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class BlockManager {

	GamePanel gp;
	public Block[] block;
	public int mapBlockNum[][];
	
	public BlockManager(GamePanel gp) {
		this.gp = gp;
		
		block = new Block[10];
		mapBlockNum = new int[gp.WORLDCOLUMNS][gp.WORLDROWS];
		
		getTileImage();
		loadMap("/maps/world01.txt");
	}
	
	public void getTileImage() {
		try {
			
			block[2] = new Block();
			block[2].image = ImageIO.read(getClass().getResourceAsStream("/blocks/block_grass.png"));
			
			block[1] = new Block();
			block[1].image = ImageIO.read(getClass().getResourceAsStream("/blocks/block_brick.png"));
			block[1].collision = true;
			
			block[0] = new Block();
			block[0].image = ImageIO.read(getClass().getResourceAsStream("/blocks/block_empty.png"));
			block[0].collision = true;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.WORLDCOLUMNS && row < gp.WORLDROWS) {
				
				String line = br.readLine();
				
				while(col < gp.WORLDCOLUMNS) {
					
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapBlockNum[col][row] = num;
					col++;
				}
				if(col == gp.WORLDCOLUMNS) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch(Exception e) {
			
		}
	}
	public void draw(Graphics2D g2, ImageObserver observer) {
		
		int worldCol = 0;
		int worldRow = 0;

		while(worldCol < gp.WORLDCOLUMNS && worldRow < gp.WORLDROWS) {
			
			int tileNum = mapBlockNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.TILE_SIZE;
			int worldY = worldRow * gp.TILE_SIZE;
			int screenX = worldX - gp.camera.worldX + gp.camera.screenX;
			int screenY = worldY - gp.camera.worldY + gp.camera.screenY;
			
			if(worldX + gp.TILE_SIZE > gp.camera.worldX - gp.camera.screenX &&
			   worldX - gp.TILE_SIZE < gp.camera.worldX + gp.camera.screenX &&
			   worldY + gp.TILE_SIZE > gp.camera.worldY - gp.camera.screenY &&
			   worldY - gp.TILE_SIZE < gp.camera.worldY + gp.camera.screenY) {
				
				g2.drawImage(block[tileNum].image, screenX, screenY, gp.TILE_SIZE, gp.TILE_SIZE, observer);
			}
			
			worldCol++;
			
			if(worldCol == gp.WORLDCOLUMNS) {
				worldCol = 0;
				worldRow++;
				
			}
		}
	}
}
