package moveble;

import java.awt.Image;

import controller.GameController;

import domain.GameModel;
import domain.Tile;

public abstract class Moveable extends Tile {
	int x = 0;
	int y = 0;
	Image img = null;

	public Moveable(int x, int y) {
		this.x = x;
		this.y = y;
		zvalue=17;
	}

	public abstract void action();
	public Image getImg() {
		return img;
	}

	public int getPosX() {
		return x;
	}

	public int getPosY() {
		return y;
	}

	protected double hypetunusan(int startX, int startY, int endX, int endY) {
		int lengthX = (startX - endX);
		int lengthY = (startY - endY);
		return Math.sqrt((lengthX * lengthX) + (lengthY * lengthY));

	}
	public void TransformToTile(){
		System.out.println("TransformToTile");
		if(GameModel.GetWorldMap().getPointFromPx(x, y)!=null){
			GameModel.setInAnimalsRemoveQueue(this);
			//add to tile
			System.out.println();
			GameModel.GetWorldMap().getPointFromPx(x, y).add(this);
		}else{
			System.out.println("point:"+x+":"+y+"dont exitst");
		}
	}
	public void TransformToAnimal(){
		System.out.println("TransformToAnimal");
		GameModel.addAnimals(this);
	}
}
