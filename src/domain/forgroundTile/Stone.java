package domain.forgroundTile;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class Stone extends ForgroundTile{
	private static Image pic;
	private int part;
	
	public Stone(){
		canNotBeWhit.add(new Tree(0, 0,(double) 0,false));
		zvalue=70;
		sollid=true;
		name="stone";
		String uri="./res/pic/rock.png";
		if(pic==null)
			pic= Toolkit.getDefaultToolkit().getImage(uri);
		double rand=Math.random();
		if(rand<0.7){
			part=0;
		}else{
			part=1;
		}
	}
	 public void drawItSelf( Graphics g,ImageObserver io, int x,int y,int with,int higth,float zoom){
			int picX=0;
			int picY=0;
			if(part==0){
				picX=0;
				picY=0;
			}else if(part==1){
				picX=32;
				picY=0;
			}

			g.drawImage(pic, x,y,x+(int) (with*zoom),y+(int)(higth*zoom),picX, picY, picX+32, picY+32, io);
	 }
	 
	 
	
}
