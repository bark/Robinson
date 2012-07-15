package domain.plants;

import items.food.Carrot;
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

public class CarrotPlant extends Plant{

	public CarrotPlant(int x,int y){
		super(x,y);
		picX=64;
		picY=224;
		drops=new Carrot();
		String uri="./res/pic/plants.png";
		pic= Toolkit.getDefaultToolkit().getImage(uri);
		zvalue=40;
	}
		
	
}