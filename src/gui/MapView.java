package gui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;

import domain.Player;
import domain.Tile;
import domain.map.Map;
import domain.map.MapPart;


public class MapView extends JComponent{
	Map map=null;
	float zoom=1;
	int centerX;
	int centerY;
	private int leftCornerX;
	private int leftCornerY;
	Player player1;
//	private StatusView statusView;
//	private LogView logView;

	public MapView(Map map, Player player1) {
		this.map=map;
		repaint();
		this.player1=player1;
//		this.statusView=statusView;
//		this.logView=logView;
		setVisible(true);
		// TODO Auto-generated constructor stub
	}
	public void setMap(MapPart mapPart) {
		this.map=map;
		repaint();
		setVisible(true);
	}
	public void paint(Graphics g) {
		Image offscreen = null;
		Graphics offgc;
		offscreen = createImage(getWidth(),getHeight());
		offgc = offscreen.getGraphics();
		
		
		for(int x=(player1.getPosX()/64)-15;x<(player1.getPosX()/64)+15;x++){
			for(int y=(player1.getPosY()/64)-15;y<(player1.getPosY()/64)+15;y++){
				
				for(Tile tile:map.getPoint(x, y)){
					if(tile.getYValure()>100){
						break;
					}
					tile.drawItSelf(offgc,this,x*(int)(64*zoom)-leftCornerX,y*(int)(64*zoom)-leftCornerY,zoom);
				}
			}
		}
		player1.drawItSelf(offgc,this,(int)getWidth()/2,(int)getHeight()/2,zoom);
		
		
		for(int x=(player1.getPosX()/64)-15;x<(player1.getPosX()/64)+15;x++){
			for(int y=(player1.getPosY()/64)-15;y<(player1.getPosY()/64)+15;y++){
				for(Tile tile:map.getPoint(x, y)){
					if(tile.getYValure()>100){
						tile.drawItSelf(offgc,this,x*(int)(64*zoom)-leftCornerX,y*(int)(64*zoom)-leftCornerY,zoom);
					}
				}
			}
		}
//		paint(offgc);
		g.drawImage(offscreen, 0, 0, this);
	}
	public void moveCameraTo(int x,int y,float zoom){//move the center of the camera to this point
		
		this.zoom=zoom;
		this.centerX=x;
		this.centerY=y;
		this.leftCornerX=(int)((x*zoom)-((getWidth()/2)));//denna stämmer inte riktigt med zoom
		this.leftCornerY=(int)((y*zoom)-((getHeight()/2)));//denna stämmer inte riktigt med zoom
//		repaint();
	}

}
