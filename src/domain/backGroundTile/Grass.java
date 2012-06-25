package domain.backGroundTile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class Grass extends BackgroundTile{
	Image pic;
	int part=0; //0-3 0 är standard.
	public Grass(){
		super();
		String uri="./res/pic/grass.png";
		pic= Toolkit.getDefaultToolkit().getImage(uri);
		double rand=Math.random();
		if(rand<0.6){
			part=0;
		}else if(rand<0.75){
			part=1;
		}else if(rand<0.85){
			part=2;
		}else{
			part=3;
		}
	}
	
	 public void drawItSelf( Graphics g,ImageObserver io, int x,int y,float zoom){
		int picX=0;
		int picY=0;
		if(part==0){
			picX=32;
			picY=94;
		}else if(part==1){
			picX=0;
			picY=160;
		}else if(part==2){
			picX=32;
			picY=160;
		}else if(part==3){
			picX=64;
			picY=160;
		}
		g.drawImage(pic, x,y,x+(int) (64*zoom),y+(int)(64*zoom),picX, picY, picX+32, picY+32, io);
		// g.setColor(Color.RED);
		 //g.fillOval(x,y,(int) (64*zoom),(int)(64*zoom));
	}
	/*@Override
	public void paint(Graphics g) {
		super.paint(g);
		//g.drawLine(0, 0, 200, 400);
		
		
		g.fillOval(0,0, 64, 64);
		
		//g.drawRect(0,0,100,100);
		
		//g.dispose();

		
	}*/
}
