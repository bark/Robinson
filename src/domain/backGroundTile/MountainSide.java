package domain.backGroundTile;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import domain.Tile;
import domain.Tile.DIRECTION;

public class MountainSide  extends Tile{

	Image pic;
	public MountainSide(){
		zvalue=20;
		sollid=true;
		part=13;
		String uri="./res/pic/mountains.png";
		if(pic==null)
			pic= Toolkit.getDefaultToolkit().getImage(uri);
		part=42;
		
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
				part=999;

			} else if (!directions.contains(DIRECTION.SOUTH)) {
				if (!directions.contains(DIRECTION.EAST)) {
					part = 53;
				} else if (!directions.contains(DIRECTION.WEST)) {
					part = 55;
				} else if (!directions.contains(DIRECTION.NORTHEAST)) {
					part = 12;
				} else if (!directions.contains(DIRECTION.NORTHWEST)) {
					part = 14;
				} else {
					part = 54;
				}
			} else if (!directions.contains(DIRECTION.WEST)) {
				if (!directions.contains(DIRECTION.NORTHEAST)) {
					part = 8;
				} else if (!directions.contains(DIRECTION.SOUTHEAST)) {
					part = 14;
				} else {
					part = 34;
				}
			} else if (!directions.contains(DIRECTION.EAST)) {
				if (!directions.contains(DIRECTION.NORTHWEST)) {
					part = 6;
				} else if (!directions.contains(DIRECTION.SOUTHWEST)) {
					part = 12;
				} else {
					part = 35;
				}
			} else if (!directions.contains(DIRECTION.NORTHEAST)) {
				part = 8;
			} else if (!directions.contains(DIRECTION.NORTHWEST)) {
				part = 9;
			} else if (!directions.contains(DIRECTION.SOUTHEAST)) {
				part = 13;//todo Create this to tiles
			} else if (!directions.contains(DIRECTION.SOUTHWEST)) {
				part = 13;//todo Create this to tiles
			} else {
				
				part=42;
			}

		}
	 void transformToCave(int i){
		 
	 }
	 void transformToLader(int i){
	 
	 }
	 
}
