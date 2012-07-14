package items.food;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import domain.backGroundTile.Grass;
import domain.backGroundTile.Water;
import domain.forgroundTile.Stone;
import Items.Item;

public class Food extends Item{
	int picX;
	int picY;
	int with=32;
	int higth=32;
	
	
	Food(){
		zvalue=65;
		canNotBeWhit.add(new Water());
		canNotBeWhit.add(new Stone());
		haveToBeWhit.add(new Grass());
	}
	 public void drawItSelf( Graphics g,ImageObserver io, int x,int y,int with,int higth,float zoom){
			
			g.drawImage(pic, x,y,x+(int) (with*zoom),y+(int)(higth*zoom),picX, picY, picX+higth, picY+with, io);
	 }
	@Override
	public Image getImage() {
		// TODO Auto-generated method stub
		BufferedImage image = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
		Graphics imageG = image.getGraphics();
		int picX=606;
		int picY=480;
		imageG.drawImage(pic, 0,0,(int) (32),(int)(32),picX, picY, picX+32, picY+32, null);
		
		
	
		return image;
	}

}
