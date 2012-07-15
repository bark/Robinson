package domain.plants;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import Items.Item;
import domain.GameModel;
import domain.Player;
import domain.Tile;
import domain.useble;
import domain.backGroundTile.Grass;
import domain.backGroundTile.Water;
import domain.forgroundTile.Stone;
import domain.forgroundTile.Tree;

public  class Plant extends Tile implements useble{
	protected int picX;
	protected int picY;
	protected Item drops;
	protected Plant(int x,int y){
		this.x=x;
		this.y=y;
		canNotBeWhit.add(new Water());
		
		canNotBeWhit.add(new Tree(0,0,(double) 0, false));
		canNotBeWhit.add(new Stone());
		haveToBeWhit.add(new Grass());
		zvalue = 40;
	}
	
	@Override
	public void drawItSelf(Graphics g, ImageObserver io, int x, int y,
			int with, int higth, float zoom) {
		g.drawImage(pic, x, y, x + (int) (with * zoom), y
				+ (int) (higth * 2 * zoom), picX, picY, picX+32, picY + 64, io);

	}

	public void use(Player pl) {
		picY=288;
		GameModel.GetWorldMap().getPoint(x, y).calculateImage();
		
		if(GameModel.GetWorldMap().checkPointIsOk(x, y+1)){
			GameModel.GetWorldMap().getPoint(x, y+1).add(drops);
		}else if(GameModel.GetWorldMap().checkPointIsOk(x-1, y)){
			GameModel.GetWorldMap().getPoint(x-1, y).add(drops);
		}else if(GameModel.GetWorldMap().checkPointIsOk(x+1, y)){
			GameModel.GetWorldMap().getPoint(x+1, y).add(drops);
		}else if(GameModel.GetWorldMap().checkPointIsOk(x, y+1)){
			GameModel.GetWorldMap().getPoint(x, y-1).add(drops);
		}
		
	}

}
