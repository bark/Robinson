package domain.plants;

import items.food.Paprica;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;


import domain.GameModel;
import domain.Player;
import domain.Tile;
import domain.useble;
import domain.backGroundTile.Water;

public class PapricaPlant extends plant{
	

	public PapricaPlant(int x,int y) {
		super(x,y);
		picX=128;
		picY=220;
		String uri = "./res/pic/plants.png";
		drops=new Paprica();
		pic = Toolkit.getDefaultToolkit().getImage(uri);
	}



}
