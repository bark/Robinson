package gui;

import java.awt.Canvas;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.MapPart;
/*
public class MapView extends Canvas{
	MapPart mapPart
	public void setMap(MapPart mapPart) {
		//setLayout(new GridLayout(10,10));
		//setSize(300, 300);
		this.mapPart=mapPart;
		
		// TODO Auto-generated method stub
		//pack();
		
		//revalidate();
		setVisible(true);
	}
	
	public void paint(Graphics g) {
		for(int x=0;x<10;x++){
			for(int y=0;y<10;y++){
				//System.out.println(mapPart.getPoint(x, y, 0));
				//add(new JButton(x+": "+y));
				g.drawImage((Canvas) mapPart.getPoint(x, y, 0), x*64, y*64, null );
				//add(mapPart.getPoint(x, y, 0));
				//experimentLayout mapPart.getPoint(x, y, 0);
			}
		}
	}
	
	
}

*/



public class MapView extends JPanel{
	public void setMap(MapPart mapPart) {
		setLayout(new GridLayout(10,10));
		//setSize(300, 300);
		
		for(int x=0;x<10;x++){
			for(int y=0;y<10;y++){
				//System.out.println(mapPart.getPoint(x, y, 0));
				//add(new JButton(x+": "+y));
				add(mapPart.getPoint(x, y, 0));
				//experimentLayout mapPart.getPoint(x, y, 0);
			}
		}
		// TODO Auto-generated method stub
		//pack();
		
		revalidate();
		setVisible(true);
	}
	
	public Dimension getMinimumSize()
    { 
		return new Dimension(1000,1000); 
    }
    
    public Dimension getPreferredSize()
    { 
    	return getMinimumSize(); 
    }
}
