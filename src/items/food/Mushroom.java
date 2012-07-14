package items.food;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import Items.Item;

import domain.useble;
import domain.backGroundTile.Dirt;
import domain.backGroundTile.Grass;
import domain.backGroundTile.Water;
import domain.forgroundTile.Stone;

public class Mushroom  extends Food{
	private static Image pic;
	
	public Mushroom(){
		super();
		picX=606;
		picY=480;
		String uri="./res/pic/tileset01.png";
		if(pic==null)
			pic= Toolkit.getDefaultToolkit().getImage(uri);
		
	}

}
