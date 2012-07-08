package moveble;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import domain.GameModel;
import domain.Player;

public class Hare extends Moveable{
	int spead=20;
	public Hare(int x, int y) {
		super(x, y);
		img=new BufferedImage(32,32, BufferedImage.TYPE_INT_ARGB);
		Graphics imgG = img.getGraphics();
		imgG.setColor(Color.RED);
		imgG.fillRect(0, 0, 32, 32);
	}
	
	@Override
	public void action() {
		for(Player player :GameModel.getPlayers()){
			if(hypetunusan(x,y,player.getPosX(),player.getPosY())<400){
				double vinkeln=Math.atan2((x-player.getPosX()), (y-player.getPosY()+1));
				
				int diffX=(int) (Math.sin(vinkeln)*spead);
				int diffY=(int) (Math.cos(vinkeln)*spead);
				if(GameModel.GetWorldMap().checkPositionIsOk(x+diffX, y, x+diffX+32,y+32)){
					x+=diffX;
				}else if(diffY>0){
					diffY+=diffX;
				}else{
					diffY-=diffX;
				}
				if(GameModel.GetWorldMap().checkPositionIsOk(x, y+diffY, x+32,y+diffY+32)){
					y+=diffY;
				}else if(GameModel.GetWorldMap().checkPositionIsOk(x+diffY, y, x+32+diffY,y+32)){
					x+=diffY;
				}else if(GameModel.GetWorldMap().checkPositionIsOk(x-diffY, y, x-diffY+32,y+32)){
					x-=diffY;
				}
			
			}
		}
	}
	


}
