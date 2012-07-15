package domain.backGroundTile;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import domain.Tile;

public class Mountains extends Tile {


		Image pic;
		public Mountains(){
			zvalue=30;
			sollid=false;
			part=13;
			String uri="./res/pic/mountains.png";
			if(pic==null)
				pic= Toolkit.getDefaultToolkit().getImage(uri);
			part=0;
			
		}

		 public void drawItSelf( Graphics g,ImageObserver io, int x,int y,int with,int higth,float zoom){
			int picX=0;
			int picY=0;
			//part=3;
			picX=((int)part%12)*32;
			picY=((int)part/12)*32;
			
			g.drawImage(pic, x,y,x+(int) (with*zoom),y+(int)(higth*zoom),picX, picY, picX+32, picY+32, io);
		//	g.setColor(Color.black);
		//	g.drawRect(x, y, 64, 64);
			 //g.fillOval(x,y,(int) (64*zoom),(int)(64*zoom));
		}
			
		 
		 public void SetPart(ArrayList<DIRECTION> directions) {

				if (!directions.contains(DIRECTION.NORTH)) {
					if (!directions.contains(DIRECTION.EAST)) {
						part = 5;
					} else if (!directions.contains(DIRECTION.WEST)) {
						part = 7;
					} else if (!directions.contains(DIRECTION.SOUTHWEST)) {
						part = 8;
					} else if (!directions.contains(DIRECTION.SOUTHEAST)) {
						part = 6;
					} else {
						part = 6;
					}

				} else if (!directions.contains(DIRECTION.SOUTH)) {
					if (!directions.contains(DIRECTION.EAST)) {
						part = 29;
					} else if (!directions.contains(DIRECTION.WEST)) {
						part = 31;
					} else if (!directions.contains(DIRECTION.NORTHEAST)) {
						part = 12;
					} else if (!directions.contains(DIRECTION.NORTHWEST)) {
						part = 14;
					} else {
						part = 30;
					}
				} else if (!directions.contains(DIRECTION.WEST)) {
					if (!directions.contains(DIRECTION.NORTHEAST)) {
						part = 8;
					} else if (!directions.contains(DIRECTION.SOUTHEAST)) {
						part = 14;
					} else {
						part = 19;
					}
				} else if (!directions.contains(DIRECTION.EAST)) {
					if (!directions.contains(DIRECTION.NORTHWEST)) {
						part = 6;
					} else if (!directions.contains(DIRECTION.SOUTHWEST)) {
						part = 12;
					} else {
						part = 17;
					}
				} else if (!directions.contains(DIRECTION.NORTHEAST)) {
					part = 8;
				} else if (!directions.contains(DIRECTION.NORTHWEST)) {
					part = 9;
				} else if (!directions.contains(DIRECTION.SOUTHEAST)) {
					part = 20;
				} else if (!directions.contains(DIRECTION.SOUTHWEST)) {
					part = 21;
				} else {
					
					part=18;
				}

			}
		 
	

}
