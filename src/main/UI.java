package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;

public class UI {

	GamePanel gamePanel;
	Graphics2D graphics2D;
	Font purisaB;
	public boolean gameFinish = false;
	public int commandNum = 0;
	
	public UI(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		
		//font
		try {
			InputStream is = getClass().getResourceAsStream("/font/Purisa Bold.ttf");
			purisaB = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void draw(Graphics2D graphics2D) {
		
		this.graphics2D = graphics2D;
		
		graphics2D.setFont(purisaB);
		graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		graphics2D.setColor(Color.white);
		
		//title state
		if (gamePanel.gameState == gamePanel.titleState) {
			drawTitleScreen();
		}

		//pause state
		if (gamePanel.gameState == gamePanel.pauseState) {
			drawPauseScreen();
		}
		
		if (gamePanel.gameState == gamePanel.endState) {
			drawFinishScreen();
		}
	}
	
	public void drawFinishScreen() {
		graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 60F));
		//finish name
		String text = "Congratulations!";
		int x = getXforCenteredText(text);
		int y = gamePanel.screenHeight/2;
		graphics2D.drawString(text, x, y);
	}
	
	public void drawPauseScreen() {
		graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 96F));
		String text = "PAUSE"; 
		int x = getXforCenteredText(text);
		int y = gamePanel.screenHeight/2;
		graphics2D.drawString(text, x, y);
	}
	
	public int getXforCenteredText(String text) {
		int length = (int)graphics2D.getFontMetrics().getStringBounds(text, graphics2D).getWidth();
		int x = gamePanel.screenWidth/2 - length/2;
		return x;
	}
	
	public void drawTitleScreen() {
		
		graphics2D.setColor(Color.black);
		graphics2D.fillRect(0, 0, gamePanel.screenWidth, gamePanel.screenHeight);
		//title name
		graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 60F));
		String text = "JUMP this SHIT!";
		int x = getXforCenteredText(text);
		int y = gamePanel.tileSize*3;
		
		//shadow
		graphics2D.setColor(Color.gray);
		graphics2D.drawString(text, x+4, y+4);
		
		
		//main color
		graphics2D.setColor(Color.white);
		graphics2D.drawString(text, x, y);
		
		//main character image
		x = gamePanel.screenWidth/2 - (gamePanel.tileSize*2)/2;
		y += gamePanel.tileSize*2;
		graphics2D.drawImage(gamePanel.player.down1, x, y, gamePanel.tileSize*2, gamePanel.tileSize*2, null);
		
		//menu
		graphics2D.setFont(graphics2D.getFont().deriveFont(Font.BOLD, 40F));
		
		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gamePanel.tileSize*3.5;
		graphics2D.drawString(text, x, y);
		if (commandNum == 0) {
			graphics2D.drawString(">", x-gamePanel.tileSize, y);
		}
		
		text = "CONTINUE";
		x = getXforCenteredText(text);
		y += gamePanel.tileSize;
		graphics2D.drawString(text, x, y);
		if (commandNum == 1) {
			graphics2D.drawString(">", x-gamePanel.tileSize, y);
		}
		
		text = "QUIT";
		x = getXforCenteredText(text);
		y += gamePanel.tileSize;
		graphics2D.drawString(text, x, y);
		if (commandNum == 2) {
			graphics2D.drawString(">", x-gamePanel.tileSize, y);
		}
	}
	
	
	
}
