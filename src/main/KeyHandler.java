package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean wPress, aPress, sPress, dPress, spacePress;
	
	GamePanel gamePanel;
	public KeyHandler(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		
		//title state
		if(gamePanel.gameState == gamePanel.titleState) {
			if (code == KeyEvent.VK_W) {
				gamePanel.ui.commandNum--;
				if (gamePanel.ui.commandNum < 0) {
					gamePanel.ui.commandNum = 2;
				}
			}
			if (code == KeyEvent.VK_S) {
				gamePanel.ui.commandNum++;
				if (gamePanel.ui.commandNum > 2) {
					gamePanel.ui.commandNum = 0;
				}
			}
			if (code == KeyEvent.VK_ENTER) {
				if (gamePanel.ui.commandNum == 0) {
					gamePanel.gameState = gamePanel.playState;
					gamePanel.playMusic(0);
					
				}
				
				if (gamePanel.ui.commandNum == 1) {
					//add later
				}
				
				if (gamePanel.ui.commandNum == 2) {
					System.exit(0);
					
				}
			}
		}
			
		
		//play state
		if (code == KeyEvent.VK_S) {
			sPress = true;
		}
		if (code == KeyEvent.VK_A) {
			aPress = true;
		}
		if (code == KeyEvent.VK_D) {
			dPress = true;
		}
		if (code == KeyEvent.VK_SPACE) {
			spacePress = true;
		}
		if (code == KeyEvent.VK_P) {
			if (gamePanel.gameState == gamePanel.playState)
				gamePanel.gameState = gamePanel.pauseState;
			else if (gamePanel.gameState == gamePanel.pauseState)
				gamePanel.gameState = gamePanel.playState;
		}

	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();

		if (code == KeyEvent.VK_S) {
			sPress = false;
		}
		if (code == KeyEvent.VK_A) {
			aPress = false;
		}
		if (code == KeyEvent.VK_D) {
			dPress = false;
		}
		if (code == KeyEvent.VK_SPACE) {
			spacePress = false;
		}
		
		
	}

}
