package domain;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class Tile{
	 String url="";
	 protected String name="tile";
	 protected Boolean sollid=false;
	 
	 public void drawItSelf( Graphics g,ImageObserver io,int x,int y,float zoom){
			
		}
		
		public boolean isSollid(){
			return sollid;
		}
		public String getName(){
			return name;
		}
}
