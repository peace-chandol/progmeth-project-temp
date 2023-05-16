package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gamePanel;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		
		tile = new Tile[10];
		mapTileNum = new int [gamePanel.maxWorldCol][gamePanel.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/world01.txt");
	}
	
	public void getTileImage() {
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].collision = true;
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
				String line = br.readLine();
				
				while (col < gamePanel.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gamePanel.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//camera
	public void draw(Graphics2D graphics2D) {
		
	    for (int worldRow = 0; worldRow < gamePanel.maxWorldRow; worldRow++) {
	        for (int worldCol = 0; worldCol < gamePanel.maxWorldCol; worldCol++) {
	        	int tileNum = mapTileNum[worldCol][worldRow];
	        	int worldX = worldCol * gamePanel.tileSize;
	        	int worldY = worldRow * gamePanel.tileSize;
	        	int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
	        	int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;
	        	
	        	if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
	        			worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
	        			worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
	        			worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY)
	            graphics2D.drawImage(tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
	        }
	    }
	}
	
	


	
}
