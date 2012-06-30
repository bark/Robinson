package domain.backGroundTile;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import domain.Tile;

public class Water extends Tile {
	Image pic;
	public Water(){
		yvalure=40;
		sollid=true;
		String uri="./res/pic/water.png";
		if(pic==null)
			pic= Toolkit.getDefaultToolkit().getImage(uri);
		part=0;
		
	}

	 public void drawItSelf( Graphics g,ImageObserver io, int x,int y,float zoom){
		int picX=0;
		int picY=0;
		//part=3;
		picX=((int)part%3)*32;
		picY=((int)part/3)*32;
		
		g.drawImage(pic, x,y,x+(int) (64*zoom),y+(int)(64*zoom),picX, picY, picX+32, picY+32, io);
	//	g.setColor(Color.black);
	//	g.drawRect(x, y, 64, 64);
		 //g.fillOval(x,y,(int) (64*zoom),(int)(64*zoom));
	}
}
