package domain.forgroundTile;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import Items.Paprica;

import domain.GameModel;
import domain.Player;
import domain.Tile;
import domain.useble;
import domain.backGroundTile.Water;

public class PapricaPlant  extends Tile implements useble{
	
	private int picX;
	private int picY;

	public PapricaPlant(int x,int y) {
		this.x=x;
		this.y=y;
		picX=128;
		picY=220;
		canNotBeWhit.add(new Water());
		String uri = "./res/pic/plants.png";
		pic = Toolkit.getDefaultToolkit().getImage(uri);
		zvalue = 40;
	}

	@Override
	public void drawItSelf(Graphics g, ImageObserver io, int x, int y,
			int with, int higth, float zoom) {
		g.drawImage(pic, x, y, x + (int) (with * zoom), y
				+ (int) (higth * 2 * zoom), picX, picY, picX+32, picY + 64, io);

	}

	@Override
	public void use(Player pl) {
		System.out.println("lägger ut paprikan");
		picY=280;
		GameModel.GetWorldMap().getPoint(x, y).calculateImage();
		
		if(GameModel.GetWorldMap().checkPointIsOk(x, y+1)){
			GameModel.GetWorldMap().getPoint(x, y+1).add(new Paprica());
		}else if(GameModel.GetWorldMap().checkPointIsOk(x-1, y)){
			GameModel.GetWorldMap().getPoint(x-1, y).add(new Paprica());
		}else if(GameModel.GetWorldMap().checkPointIsOk(x+1, y)){
			GameModel.GetWorldMap().getPoint(x+1, y).add(new Paprica());
		}else if(GameModel.GetWorldMap().checkPointIsOk(x, y+1)){
			GameModel.GetWorldMap().getPoint(x, y-1).add(new Paprica());
		}
		
	}

}
