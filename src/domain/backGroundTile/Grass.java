package domain.backGroundTile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Grass extends BackgroundTile{
	public Grass(){
		yvalure=10;
		String uri="./res/pic/grass.png";
		if(pic==null)
			pic= Toolkit.getDefaultToolkit().getImage(uri);
		double rand=Math.random();
		part=0;
		
	}
	
}
