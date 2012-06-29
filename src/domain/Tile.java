package domain;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Tile implements Comparable{
	public static enum DIRECTION {
		NORTH, SOUTH, WEST, EAST, NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEAST
	}
	protected int yvalure=0;//100 is the human
	String url = "";
	protected Boolean fullscreen=false;
	protected String name = "tile";
	protected Boolean sollid = false;

	public void drawItSelf(Graphics g, ImageObserver io, int x, int y,
			float zoom) {

	}
	public Boolean isFullscreen() {
		return fullscreen;
	}
	public int getYValure(){
		return yvalure;
	}
	
	public void SetPart(ArrayList<DIRECTION> directions) {
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
}
