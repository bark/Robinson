package domain.plants;

import items.food.Paprica;
import items.food.Potato;
import items.food.Tomat;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;


import domain.GameModel;
import domain.Player;
import domain.Tile;
import domain.useble;
import domain.backGroundTile.Water;

public class PotatoPlant extends Plant{

	public PotatoPlant(int x,int y){
		super(x,y);
		picX=32;
		picY=224;
		drops=new Potato();
		String uri="./res/pic/plants.png";
		pic= Toolkit.getDefaultToolkit().getImage(uri);
		zvalue=40;
	}
		
	
}