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
		setLayout(new GridLayout(mapPart.getHigth(),mapPart.getwith()));
		
		
		for(int x=0;x<mapPart.getHigth();x++){
			for(int y=0;y<mapPart.getwith();y++){
				//System.out.println(mapPart.getPoint(x, y, 0));
				add(new JButton(x+" : "+y));
				//add(mapPart.getPoint(x, y, 0));//borde fungera :(
				//experimentLayout mapPart.getPoint(x, y, 0);
			}
		}
		// TODO Auto-generated method stub
		//pack();
		
		revalidate();
		setVisible(true);
	}
	
	
}
