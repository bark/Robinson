package gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.MapPart;


public class MapView extends JComponent{
	MapPart mapPart=null;
	float zoom=1;
	int centerX;
	int centerY;
	private int leftCornerX;
	private int leftCornerY;
	public void setMap(MapPart mapPart) {
		this.mapPart=mapPart;
		repaint();
		setVisible(true);
	}
	public void paint(Graphics g) {
		System.out.println("kör paint på mapview");
		for(int x=0;x<mapPart.getHigth();x++){
			for(int y=0;y<mapPart.getWith();y++){
				
				mapPart.getPoint(x, y, 0).drawItSelf(g,this,x*(int)(64*zoom)-leftCornerX,y*(int)(64*zoom)-leftCornerY,zoom);
			}
		}
		
		g.setColor(Color.green);
		g.fillOval((int)getWidth()/2, (int)getHeight()/2,(int)(64*zoom),(int)( 64*zoom));
		
	}
	public void moveTo(int x,int y,float zoom){//move the center of the camera to this point
		System.out.println("bredden:"+getWidth());
		System.out.println("höjd:"+getHeight());
		this.zoom=zoom;
		this.centerX=x;
		this.centerY=y;
		this.leftCornerX=x-(int)(getHeight()/2);
		this.leftCornerY=y-(int)(getWidth()/2);
		repaint();
	}
}
