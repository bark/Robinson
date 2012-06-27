package domain.forgroundTile;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class TreeBottom extends ForgroundTile{
	private Image pic;
	private int part;
	private int treeType;
	
	public TreeBottom(int treeType,int part){
		sollid=true;
		String uri="./res/pic/trunk.png";
		pic= Toolkit.getDefaultToolkit().getImage(uri);
		this.treeType=treeType;
		this.part=part;
		name="treeBot";
	}
	 public void drawItSelf( Graphics g,ImageObserver io, int x,int y,float zoom){
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
			g.drawImage(pic, x,y,x+(int) (64*zoom),y+(int)(64*zoom),picX, picY, picX+32, picY+32, io);
	 }	
}
