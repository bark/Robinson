package domain.forgroundTile;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

import domain.backGroundTile.Grass;
import domain.backGroundTile.Water;

public class TreeBottom extends ForgroundTile{
	private static Image pic;
	private int part;
	private int treeType;
	
	public TreeBottom(int treeType,int part,int prio){
		canNotBeWhit.add(new Water());
		if(part==3)
		{
			zvalue=105+prio;
		}else if(part==7){
			zvalue=69+prio;
		}else{
		
			zvalue=60+prio;
		}
		String uri="./res/pic/trunk.png";
		pic= Toolkit.getDefaultToolkit().getImage(uri);
		this.treeType=treeType;
		this.part=part;
		name="treeBot";
	}
	@Override
	public boolean isSollid() {
		return(part==4);
	}

	 public void drawItSelf( Graphics g,ImageObserver io, int x,int y,int with,int higth,float zoom){
			int picX=0;
			int picY=0;
			if(treeType==0){
				picX=0;
				picY=0;
			}else if(treeType==1){
				picX=94;
				picY=0;
			}
			if(part==0){
				
			}if(part==1){
				picY+=32;
			}if(part==2){
				picY+=64;
			}if(part==3){
				picX+=32;
			}if(part==4){
				picX+=32;
				picY+=32;
			}if(part==5){
				picX+=32;
				picY+=64;
			}if(part==6){
				picX+=64;
			}if(part==7){
				picX+=64;
				picY+=32;
			}if(part==8){
				picY+=64;
				picX+=64;
			}
			g.drawImage(pic, x,y,x+(int) (with*zoom),y+(int)(higth*zoom),picX, picY, picX+32, picY+32, io);
	 }	
}
