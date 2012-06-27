package gui;

import java.awt.Graphics;

import javax.swing.JComponent;

import domain.MapPart;
import domain.Player;


public class MapView extends JComponent{
	MapPart mapPart=null;
	float zoom=1;
	int centerX;
	int centerY;
	private int leftCornerX;
	private int leftCornerY;
	Player player1;
	private StatusView statusView;
	private LogView logView;
	
	public MapView(MapPart mappart,StatusView statusView, LogView logView, Player player1) {
		this.mapPart=mappart;
		repaint();
		this.player1=player1;
		this.statusView=statusView;
		this.logView=logView;
		setVisible(true);
		// TODO Auto-generated constructor stub
	}
	public void setMap(MapPart mapPart) {
		this.mapPart=mapPart;
		repaint();
		setVisible(true);
	}
	public void paint(Graphics g) {
		for(int x=0;x<mapPart.getHigth();x++){
			for(int y=0;y<mapPart.getWith();y++){
				if(mapPart.getPoint(x, y, 0)!=null)
					mapPart.getPoint(x, y, 0).drawItSelf(g,this,x*(int)(64*zoom)-leftCornerX,y*(int)(64*zoom)-leftCornerY,zoom);
			}
		}
		
		
		for(int x=0;x<mapPart.getHigth();x++){
			for(int y=0;y<mapPart.getWith();y++){
				if(mapPart.getPoint(x, y, 1)!=null)
					mapPart.getPoint(x, y, 1).drawItSelf(g,this,x*(int)(64*zoom)-leftCornerX,y*(int)(64*zoom)-leftCornerY,zoom);
			
			}
		}
		player1.drawItSelf(g,this,(int)getWidth()/2,(int)getHeight()/2,zoom);
		
		for(int x=0;x<mapPart.getHigth();x++){
			for(int y=0;y<mapPart.getWith();y++){
				if(mapPart.getPoint(x, y, 2)!=null)
					mapPart.getPoint(x, y, 2).drawItSelf(g,this,x*(int)(64*zoom)-leftCornerX,y*(int)(64*zoom)-leftCornerY,zoom);
	
			}
		}
		for(int x=0;x<mapPart.getHigth();x++){
			for(int y=0;y<mapPart.getWith();y++){
				if(mapPart.getPoint(x, y, 3)!=null)
					mapPart.getPoint(x, y, 3).drawItSelf(g,this,x*(int)(64*zoom)-leftCornerX,y*(int)(64*zoom)-leftCornerY,zoom);
	
			}
		}
		for(int x=0;x<mapPart.getHigth();x++){
			for(int y=0;y<mapPart.getWith();y++){
				if(mapPart.getPoint(x, y, 4)!=null)
					mapPart.getPoint(x, y, 4).drawItSelf(g,this,x*(int)(64*zoom)-leftCornerX,y*(int)(64*zoom)-leftCornerY,zoom);
	
			}
		}
		
		
		statusView.drawItSelf(g,this,(int)getWidth()-400,(int)getHeight()-200,zoom);
		logView.drawItSelf(g, this, 0, getHeight()-100, zoom);
	}
	public void moveTo(int x,int y,float zoom){//move the center of the camera to this point
		
		this.zoom=zoom;
		this.centerX=x;
		this.centerY=y;
		this.leftCornerX=(int)((x*zoom)-((getWidth()/2)));//denna stämmer inte riktigt med zoom
		this.leftCornerY=(int)((y*zoom)-((getHeight()/2)));//denna stämmer inte riktigt med zoom
		repaint();
	}
}
