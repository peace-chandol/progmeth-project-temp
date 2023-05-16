package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_ChestOpen extends SuperObject{

	public OBJ_ChestOpen() {
		name = "chestClose";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/tiles/chest_opened.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
