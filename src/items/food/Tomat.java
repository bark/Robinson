package items.food;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import Items.Item;

public class Tomat extends Food {
	
	public Tomat(){
		super();
		picX=0;
		picY=352;
		with=64;
		String uri = "./res/pic/plants.png";
		pic = Toolkit.getDefaultToolkit().getImage(uri);
	}


}
