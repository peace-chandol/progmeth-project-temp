package main;

import object.OBJ_ChestClose;
import object.OBJ_ChestOpen;

public class AssetSetter {

	GamePanel gamePanel;
	
	public AssetSetter(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void setObject() {
		gamePanel.obj[0] = new OBJ_ChestClose();
		gamePanel.obj[0].worldX = gamePanel.tileSize*10;
		gamePanel.obj[0].worldY = gamePanel.tileSize*10;
		
		gamePanel.obj[1] = new OBJ_ChestOpen();
	}
}
