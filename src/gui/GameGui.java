package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameGui extends JFrame{
	public GameGui(){
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(new JLabel("om denna syns är något fel"), BorderLayout.CENTER);
		System.out.println("starting game window");
		setSize(300, 300);
		pack();
		setVisible(true);
		
	}
	
	public void setMapView(MapView mapView) {
		getContentPane().removeAll();
		this.getContentPane().add(mapView, BorderLayout.CENTER);
		pack();
		setVisible(true);
		System.out.println("setMapView");
	}
	
}
