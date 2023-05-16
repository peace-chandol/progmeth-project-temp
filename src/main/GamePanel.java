package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	//SCREEN SETTINGS
	final int originalTileSize = 16;	//16x16 pixel of start character
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;	//48x48 pixel
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;	//768 pixels
	public final int screenHeight = tileSize * maxScreenRow;	//576 pixels

	//WORLD SETTINGS	
	public final int maxWorldCol = 18;	//map column size
	public final int maxWorldRow = 32;	//map row size
	
	//FPS
	int FPS = 60;

	//system
	TileManager tileManager = new TileManager(this);
	KeyHandler keyHandler = new KeyHandler(this);
	Sound music = new Sound();
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public Player player = new Player(this, keyHandler);
	public UI ui = new UI(this);
	public SuperObject obj[] = new SuperObject[10];
	public AssetSetter assetSetter = new AssetSetter(this);
	Thread gameThread;
	
	//game state
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int endState = 3;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));	//ตั้งขนาดกรอบ
		this.setBackground(Color.PINK);
		this.setDoubleBuffered(true);	//improve game's rendering performance
		this.addKeyListener(keyHandler);
		this.setFocusable(true);	//GamePanel can be focus to receive key input
	}

	
	public void setupGame() {
		//playMusic(0);
		gameState = titleState;
		assetSetter.setObject();
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run() {
		
		double drawInterval = 1000000000/FPS;	//1 seconds 60 frames
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while (gameThread != null) {	//repeat loop when programming was running	
			
			// 1 UPDATE: update information such as character positions
			update();
			// 2 DRAW: draw the screen with the updated information
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if (remainingTime < 0)
					remainingTime = 0;
				
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		if (gameState == playState) {
			player.update();
		}
		if (gameState == pauseState) {
		}
	}
	
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		
		Graphics2D graphics2D = (Graphics2D)graphics;
		
		//title screen
		if (gameState == titleState) {
			ui.draw(graphics2D);
		}
		else {
			tileManager.draw(graphics2D);
			player.draw(graphics2D);
			ui.draw(graphics2D);
			for (int i=0; i<obj.length; i++)
				if (obj[i] != null)
					obj[i].draw(graphics2D, this);
		}
		
		graphics2D.dispose();	//release any system resources that it is using
		
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSE(int i) {	//se = sound effect
		se.setFile(i);
		se.play();
		//sound effect no need to loop
	}
	
}
