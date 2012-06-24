package gui;

import java.awt.Canvas;
import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.MapPart;

public class MapView extends JPanel{
	public void setMap(MapPart mapPart) {
		setLayout(new GridLayout(10,10));
		
		
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
	
	
}
