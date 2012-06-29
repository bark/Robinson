package Items;

import java.awt.Image;
import java.awt.Toolkit;

public class Candle extends Item {

	String uri="./res/pic/candle.png";
	Image img = Toolkit.getDefaultToolkit().getImage(uri);
	
	@Override
	public Image getImage() {
		return img;
	}

	public String toString(){
		return "Candle";
	}
}
