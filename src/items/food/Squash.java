package items.food;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import Items.Item;

public class Squash extends Food {
	
	public Squash(){
		super();
		picX=96+64;
		picY=352;
		String uri = "./res/pic/plants.png";
		pic = Toolkit.getDefaultToolkit().getImage(uri);
	}


}
