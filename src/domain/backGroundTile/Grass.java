package domain.backGroundTile;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class Grass extends BackgroundTile{
	static Image pic=null;
	int part=0; //0-3 0 är standard.
	public Grass(){
		yvalure=10;
		String uri="./res/pic/grass.png";
		if(pic==null)
			pic= Toolkit.getDefaultToolkit().getImage(uri);
		double rand=Math.random();
		part=0;
		
	}
	public void SetPart(ArrayList<DIRECTION> directions){
	
		if(!directions.contains(DIRECTION.NORTH)){
			if(!directions.contains(DIRECTION.EAST)){
					part=6;
			}else if(!directions.contains(DIRECTION.WEST)){
					part=8;
			}
			else if(!directions.contains(DIRECTION.SOUTHWEST)){
				part=8;
			}
			else if(!directions.contains(DIRECTION.SOUTHEAST)){
				part=6;
			}else{
					part=7;
			}
				
		}else if(!directions.contains(DIRECTION.SOUTH)){
			if(!directions.contains(DIRECTION.EAST)){
				part=12;
			}else if(!directions.contains(DIRECTION.WEST)){
					part=14;
			}else if(!directions.contains(DIRECTION.NORTHEAST)){
				part=12;
			}
			else if(!directions.contains(DIRECTION.NORTHWEST)){
				part=14;
			}else{
					part=13;
			}
		}else if(!directions.contains(DIRECTION.WEST)){
			if(!directions.contains(DIRECTION.NORTHEAST)){
				part=8;
			}else if(!directions.contains(DIRECTION.SOUTHEAST)){
				part=14;
			}else{
				part=11;
			}
		}else if(!directions.contains(DIRECTION.EAST)){
			if(!directions.contains(DIRECTION.NORTHWEST)){
				part=6;
			}else if(!directions.contains(DIRECTION.SOUTHWEST)){
				part=12;
			}else{
				part=9;
			}
		}else if(!directions.contains(DIRECTION.NORTHEAST)){
			part=5;
		}else if(!directions.contains(DIRECTION.NORTHWEST)){
			part=4;
		}else if(!directions.contains(DIRECTION.SOUTHEAST)){
			part=2;
		}else if(!directions.contains(DIRECTION.SOUTHWEST)){
			part=1;
		}else{
//			System.out.println("gräs runt omkring");
			//fullscreen=true;
			double rand=Math.random();
			if(rand<0.6){
				part=10;
			}else if(rand<0.75){
				part=15;
			}else if(rand<0.85){
				part=16;
			}else{
				part=17;
			}
		}
		
	}
	
	 public void drawItSelf( Graphics g,ImageObserver io, int x,int y,float zoom){
		int picX=0;
		int picY=0;
		//part=3;
		picX=((int)part%3)*32;
		picY=((int)part/3)*32;
		
		g.drawImage(pic, x,y,x+(int) (64*zoom),y+(int)(64*zoom),picX, picY, picX+32, picY+32, io);
	//	g.setColor(Color.black);
	//	g.drawRect(x, y, 64, 64);
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
