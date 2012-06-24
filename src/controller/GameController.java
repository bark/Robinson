package controller;

import gui.GameGui;
import gui.MapView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import domain.Map;
import domain.MapPart;

public class GameController  implements ActionListener {
	public GameController(){
		System.out.println("gameController");
		Map worldMap = new Map();
		
		//borde bara skicka det man ser.
		MapPart mappart=worldMap.getPart(0,0);
		System.out.println("tillverkat en mapPart");
		
		MapView mapView =new MapView();
		mapView.setMap(mappart);
		GameGui gameGui = new GameGui();
		gameGui.setMapView(mapView);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
