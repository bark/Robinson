package domain;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Tile {
	public static enum DIRECTION {
		NORTH, SOUTH, WEST, EAST, NORTHWEST, NORTHEAST, SOUTHWEST, SOUTHEAST
	}

	String url = "";

	protected String name = "tile";
	protected Boolean sollid = false;

	public void drawItSelf(Graphics g, ImageObserver io, int x, int y,
			float zoom) {

	}

	public void SetPart(ArrayList<DIRECTION> directions) {
	}

	public boolean isSollid() {
		return sollid;
	}

	public String getName() {
		return name;
	}
}
