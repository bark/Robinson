package domain;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Tile implements Comparable{
	public static enum DIRECTION {
		NORTH, SOUTH, WEST, EAST, NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEAST
	}
	protected Image pic;
	protected int part=0;
	protected int yvalure=0;//100 is the human
	String url = "";
	protected Boolean fullscreen=false;
	protected String name = "tile";
	protected Boolean sollid = false;


	public Boolean isFullscreen() {
		return fullscreen;
	}
	public int getYValure(){
		return yvalure;
	}

	public boolean isSollid() {
		return sollid;
	}

	public String getName() {
		return name;
	}

	@Override
	public int compareTo(Object arg0) {
		return yvalure-((Tile)arg0).getYValure();
	}
	 public void drawItSelf( Graphics g,ImageObserver io, int x,int y,float zoom){

	}
	 
		public void SetPart(ArrayList<DIRECTION> directions){
			
			if(!directions.contains(DIRECTION.NORTH)){
				if(!directions.contains(DIRECTION.EAST)){
						part=6;
				}else if(!directions.contains(DIRECTION.WEST)){
						part=8;
				}
				else if(!directions.contains(DIRECTION.SOUTHWEST)){
					part=8;
				}
				else if(!directions.contains(DIRECTION.SOUTHEAST)){
					part=6;
				}else{
						part=7;
				}
					
			}else if(!directions.contains(DIRECTION.SOUTH)){
				if(!directions.contains(DIRECTION.EAST)){
					part=12;
				}else if(!directions.contains(DIRECTION.WEST)){
						part=14;
				}else if(!directions.contains(DIRECTION.NORTHEAST)){
					part=12;
				}
				else if(!directions.contains(DIRECTION.NORTHWEST)){
					part=14;
				}else{
						part=13;
				}
			}else if(!directions.contains(DIRECTION.WEST)){
				if(!directions.contains(DIRECTION.NORTHEAST)){
					part=8;
				}else if(!directions.contains(DIRECTION.SOUTHEAST)){
					part=14;
				}else{
					part=11;
				}
			}else if(!directions.contains(DIRECTION.EAST)){
				if(!directions.contains(DIRECTION.NORTHWEST)){
					part=6;
				}else if(!directions.contains(DIRECTION.SOUTHWEST)){
					part=12;
				}else{
					part=9;
				}
			}else if(!directions.contains(DIRECTION.NORTHEAST)){
				part=5;
			}else if(!directions.contains(DIRECTION.NORTHWEST)){
				part=4;
			}else if(!directions.contains(DIRECTION.SOUTHEAST)){
				part=2;
			}else if(!directions.contains(DIRECTION.SOUTHWEST)){
				part=1;
			}else{
				double rand=Math.random();
				if(rand<0.6){
					part=10;
				}else if(rand<0.75){
					part=15;
				}else if(rand<0.85){
					part=16;
				}else{
					part=17;
				}
			}
			
		}

}
