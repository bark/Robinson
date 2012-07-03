package domain.backGroundTile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Grass extends BackgroundTile{
	
	public Grass(){
		zvalue=10;
		String uri="./res/pic/grass.png";
		if(pic==null)
			pic= Toolkit.getDefaultToolkit().getImage(uri);
		double rand=Math.random();
		part=0;
		
	}
	 public void drawItSelf( Graphics g,ImageObserver io, int x,int y,int with,int higth,float zoom){
		int picX=0;
		int picY=0;
		//part=3;
		picX=((int)part%3)*32;
		picY=((int)part/3)*32;
		
		g.drawImage(pic, x,y,x+(int) (with*zoom),y+(int)(higth*zoom),picX, picY, picX+32, picY+32, io);
	//	g.setColor(Color.black);
	//	g.drawRect(x, y, 64, 64);
		 //g.fillOval(x,y,(int) (64*zoom),(int)(64*zoom));
	}
	
}
