package domain;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;


public class Player {
	public enum Direction {
	    RIGTH, LEFT, UP, DOWN 
	}	
	int posX=0;
	float spead=2;
	int posY=0;
	//inventory
	int hp;
	int maxhp;
	Direction dir=Direction.DOWN;
	Image pic=null;
	int frame=0;
	
	public Player(int x, int y) {
		posX=x;
		posY=y;
		String uri="./res/pic/soldier_altcolor.png";
		pic= Toolkit.getDefaultToolkit().getImage(uri);
	}
	
	 public void drawItSelf( Graphics g,ImageObserver io, int x,int y,float zoom){
			int picX=0;
			int picY=0;
			if(dir==Direction.UP){

				picY=0;
			}else if(dir==Direction.LEFT){

				picY=64;
			}else if(dir==Direction.DOWN){

				picY=128;
			}else if(dir==Direction.RIGTH){
				
				picY=128+64;
			}
			picX=64*frame;
			
			g.drawImage(pic, x,y,x+(int) (64*zoom),y+(int)(64*zoom),picX, picY, picX+64, picY+64, io);
			// g.setColor(Color.RED);
			 //g.fillOval(x,y,(int) (64*zoom),(int)(64*zoom));
		}
	
	
	
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public void goUp() {
		if(dir==Direction.UP){
			frame=(frame+1)%9;
		}else{
			frame=0;
			dir=Direction.UP;
		}
		posY-=spead;
	}
	public void goDown() {
		if(dir==Direction.DOWN){
			frame=(frame+1)%9;
		}else{
			frame=0;
			dir=Direction.DOWN;
		}
		posY+=spead;
	}
	public void goRigth() {
		if(dir==Direction.RIGTH){
			frame=(frame+1)%9;
		}else{
			frame=0;
			dir=Direction.RIGTH;
		}
		posX+=spead;
	}
	public void goLeft() {
		if(dir==Direction.LEFT){
			frame=(frame+1)%9;
		}else{
			frame=0;
			dir=Direction.LEFT;
		}
		posX-=spead;
		
	}
	
}
