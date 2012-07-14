package domain.plants;

import items.food.Carrot;
import items.food.Palsernaka;
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

public class PalsternackaPlant extends plant{

	public PalsternackaPlant(int x,int y){
		super(x,y);
		picX=94;
		picY=220;
		drops=new Palsernaka();
		String uri="./res/pic/plants.png";
		pic= Toolkit.getDefaultToolkit().getImage(uri);
		zvalue=40;
	}
		
	
}