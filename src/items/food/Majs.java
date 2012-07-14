package items.food;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import Items.Item;

public class Majs extends Food {
	
	public Majs(){
		super();
		picX=96+96;
		picY=352;
		String uri = "./res/pic/plants.png";
		pic = Toolkit.getDefaultToolkit().getImage(uri);
	}


}
