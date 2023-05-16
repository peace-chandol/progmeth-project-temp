package main;

import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//ใช้ในการปิดหน้าต่าง
		window.setResizable(false);		//ไม่ให้เปลี่ยนขนาดหน้าต่าง
		window.setTitle("is it JUMP KING?");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();	//fit size to the window
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.setupGame();
		gamePanel.startGameThread();
		
		
	}
}
