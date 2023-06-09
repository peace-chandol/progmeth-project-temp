package main;

import entity.Entity;

public class CollisionChecker {

	GamePanel gamePanel;
	
	public CollisionChecker(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityButtomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
		int entityRightCol = entityRightWorldX / gamePanel.tileSize;
		int entityTopRow = entityTopWorldY / gamePanel.tileSize;
		int entityButtomRow = entityButtomWorldY / gamePanel.tileSize;
		
		int tileNum1 , tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
			if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityButtomRow = (entityButtomWorldY + entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityButtomRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityButtomRow];
			if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[entityLeftCol][entityButtomRow];
			if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
			tileNum1 = gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gamePanel.tileManager.mapTileNum[entityRightCol][entityButtomRow];
			if (gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	
	public int checkObject(Entity entity) {
		int index = 999;
		
		for(int i = 0; i < gamePanel.obj.length; i++) {
			if (gamePanel.obj[i] != null) {
				
				//get entity solid area position
				entity.solidArea.x += entity.worldX;
				entity.solidArea.y += entity.worldY;
				
				//get object solid area position
				gamePanel.obj[i].solidArea.x += gamePanel.obj[i].worldX;
				gamePanel.obj[i].solidArea.y += gamePanel.obj[i].worldY;
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						if (gamePanel.obj[i].collision)
							entity.collisionOn = true;
						index = i;
					}
				case "down":
					entity.solidArea.y += entity.speed;
					if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						if (gamePanel.obj[i].collision)
							entity.collisionOn = true;
						index = i;
					}
				case "left":
					entity.solidArea.x -= entity.speed;
					if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						if (gamePanel.obj[i].collision)
							entity.collisionOn = true;
						index = i;
					}
				case "right":
					entity.solidArea.x += entity.speed;
					if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
						if (gamePanel.obj[i].collision)
							entity.collisionOn = true;
						index = i;
					}
				}
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gamePanel.obj[i].solidArea.x = gamePanel.obj[i].solidAreaDefaultX;
				gamePanel.obj[i].solidArea.y = gamePanel.obj[i].solidAreaDefaultY;
			}
		}
		return index;
	}
	

}
