package Items;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class Tomat extends Item {
	public Tomat(){
		zvalue=45;

		String uri = "./res/pic/plants.png";
		pic = Toolkit.getDefaultToolkit().getImage(uri);
	}
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void drawItSelf(Graphics g, ImageObserver io, int x, int y,
			int with, int higth, float zoom) {
		g.drawImage(pic, x, y, x + (int) (with * zoom), y
				+ (int) (higth * 2 * zoom), 0, 352, 0+32, 352 + 64, io);
		
	}

}
