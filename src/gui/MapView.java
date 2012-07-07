package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.JComponent;

import moveble.Moveable;
import moveble.Wolf;

import domain.GameModel;
import domain.Player;
import domain.Tile;
import domain.map.Map;
import domain.map.MapPart;
import domain.map.SortedTileList;


public class MapView extends JComponent{
	
	float zoom=1;
	int centerX;
	int centerY;
	private int leftCornerX;
	private int leftCornerY;

	public MapView() {
		repaint();
//		this.statusView=statusView;
//		this.logView=logView;
		setVisible(true);
		// TODO Auto-generated constructor stub
	}
	public void setMap(MapPart mapPart) {
		repaint();
		setVisible(true);
	}
	public void paint(Graphics g) {
		Image offscreen = null;
		Graphics offgc;
		offscreen = createImage(getWidth(),getHeight());
		offgc = offscreen.getGraphics();
		int tileSize=32;
		
		for(int x=(GameModel.getPlayers().get(0).getPosX()/tileSize)-30;x<(GameModel.getPlayers().get(0).getPosX()/tileSize)+30;x++){
			for(int y=(GameModel.getPlayers().get(0).getPosY()/tileSize)-15;y<(GameModel.getPlayers().get(0).getPosY()/tileSize)+15;y++){
				SortedTileList tileArr=GameModel.GetWorldMap().getPoint(x, y);
				if(tileArr!=null){
					/*for(Tile tile:map.getPoint(x, y)){
						/if(tile.getZValue()>100){
							break;
						}
					//	tile.drawItSelf(offgc,this,x*(int)(64*zoom)-leftCornerX,y*(int)(64*zoom)-leftCornerY,zoom);

					}*/
				//	System.out.println("mitten"+centerX+" "+centerY);
					tileArr.drawUnderIT(offgc, null,(int)( x*(tileSize*zoom))-leftCornerX,(int) (y*(tileSize*zoom))-leftCornerY, zoom);
				}
			}
		}
		

		GameModel.getPlayers().get(0).drawItSelf(offgc,this,(int)getWidth()/2,(int)getHeight()/2,zoom);
		
		
		for(Moveable animal:GameModel.getAnimals()){
			offgc.drawImage(animal.getImg(),animal.getPosX()-leftCornerX, animal.getPosY()-leftCornerY, animal.getPosX()-leftCornerX + (int) (32 * zoom), animal.getPosY()-leftCornerY
					+ (int) (32 * zoom), 0, 0,  32,  32, this);
			//offgc.drawImage(animal.getImg(), animal.getPosX(), animal.getPosY(), animal.getPosX() + (int) (64 * zoom), animal.getPosY()
			//		+ (int) (64 * zoom), 0, 0,  64,  64,this);
			
		}
		
		for(int x=(GameModel.getPlayers().get(0).getPosX()/tileSize)-30;x<(GameModel.getPlayers().get(0).getPosX()/tileSize)+30;x++){
			for(int y=(GameModel.getPlayers().get(0).getPosY()/tileSize)-15;y<(GameModel.getPlayers().get(0).getPosY()/tileSize)+15;y++){
				SortedTileList tileArr=GameModel.GetWorldMap().getPoint(x, y);
				if(tileArr!=null){
					/*for(Tile tile:map.getPoint(x, y)){
						if(tile.getZValue()>100){
							tile.drawItSelf(offgc,this,x*(int)(64*zoom)-leftCornerX,y*(int)(64*zoom)-leftCornerY,zoom);
						}
					}*/
					
					tileArr.drawOverIT(offgc, null,(int)( x*(tileSize*zoom))-leftCornerX,(int) (y*(tileSize*zoom))-leftCornerY, zoom);
				}
			}
		}
		g.drawImage(offscreen, 0, 0, this);
	}
	public void moveCameraTo(int x,int y,float zoom){//move the center of the camera to this point
		
		this.zoom=zoom;
		this.centerX=x;
		this.centerY=y;
		this.leftCornerX=(int)((x*zoom)-((getWidth()/2)));//denna stämmer inte riktigt med zoom
		this.leftCornerY=(int)((y*zoom)-((getHeight()/2)));//denna stämmer inte riktigt med zoom
	}

}
