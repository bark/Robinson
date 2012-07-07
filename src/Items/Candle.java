package Items;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

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

	@Override
	public void drawItSelf(Graphics g, ImageObserver io, int x, int y,
			int with, int higth, float zoom) {
		// TODO Auto-generated method stub
		
	}
}
