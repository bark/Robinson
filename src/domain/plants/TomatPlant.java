package domain.plants;

import items.food.Paprica;
import items.food.Tomat;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;


import domain.GameModel;
import domain.Player;
import domain.Tile;
import domain.useble;
import domain.backGroundTile.Water;

public class TomatPlant extends Plant{

	public TomatPlant(int x,int y){
		super(x,y);
		picX=0;
		picY=224;
		drops=new Tomat();
		String uri="./res/pic/plants.png";
		pic= Toolkit.getDefaultToolkit().getImage(uri);
		zvalue=40;
	}
		
	
}