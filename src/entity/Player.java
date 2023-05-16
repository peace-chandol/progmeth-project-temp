package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gamePanel;
	KeyHandler keyHandler;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gamePanel, KeyHandler keyHandler) {
		
		this.gamePanel = gamePanel;
		this.keyHandler = keyHandler;
		
		//position of character
		screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
		screenY = gamePanel.screenHeight/2 + 6*(gamePanel.tileSize/2);
		
		//hit box of character
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		//set spawn point
		worldX = gamePanel.tileSize * 9;
		worldY = gamePanel.tileSize * 29;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2.png"));
			
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void pickUpObject(int i) {
		if (i != 999) {
			String objectName = gamePanel.obj[i].name;
			
			switch(objectName) {
			case "ChestClose":
				gamePanel.obj[i] = gamePanel.obj[1];
				gamePanel.gameState = gamePanel.endState;
				gamePanel.stopMusic();
				break;
			}
		}
	}
	
	public void update() {
		
		if (keyHandler.spacePress || keyHandler.aPress || keyHandler.dPress || keyHandler.sPress) {
			if (keyHandler.spacePress) {
				direction = "up";
				
			}
			else if (keyHandler.sPress) {
				direction = "down";
				
			}
			else if (keyHandler.aPress) {
				direction = "left";
				
			}
			else if (keyHandler.dPress) {
				direction = "right";
				
			}
			
			//check tile collision
			collisionOn = false;
			gamePanel.cChecker.checkTile(this);
			
			//check object collision
			int objectIndex =  gamePanel.cChecker.checkObject(this);
			pickUpObject(objectIndex);
			
			//if collision is false, player can move
			if (!collisionOn) {
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			
			spriteCounter++;
			if (spriteCounter > 15) {
				if (spriteNum == 1)
					spriteNum = 2;
				else if (spriteNum == 2)
					spriteNum = 1;
				spriteCounter = 0;
			}
			
		}
		
	}
	
	public void draw(Graphics2D graphics2D) {
		
//		graphics2D.setColor(Color.ORANGE);
//		graphics2D.fillRect(x, y, gamePanel.tileSize, gamePanel.tileSize);
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if (spriteNum == 1)
				image = up1;
			if (spriteNum == 2)
				image = up2;
			break;
		case "down":
			if (spriteNum == 1)
				image = down1;
			if (spriteNum == 2)
				image = down2;
			break;
		case "left":
			if (spriteNum == 1)
				image = left1;
			if (spriteNum == 2)
				image = left2;
			break;
		case "right":
			if (spriteNum == 1)
				image = right1;
			if (spriteNum == 2)
				image = right2;
			break;
		}
		
		graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
	}
}
