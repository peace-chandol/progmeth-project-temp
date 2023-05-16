package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_ChestClose extends SuperObject{
	
	public OBJ_ChestClose() {
		name = "ChestClose";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/tiles/chest_closed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
