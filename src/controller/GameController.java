package controller;

import gui.GameGui;
import gui.MapView;

import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import domain.Map;
import domain.MapPart;

public class GameController implements MouseWheelListener,KeyListener {
	int x = 500;
	int y = 500;
	float zoomlv = 1;
	GameGui gameGui = null;
	MapView mapView =null;
	public GameController() {
		System.out.println("gameController");
		Map worldMap = new Map();

		// borde bara skicka det man ser.
		MapPart mappart = worldMap.getPart(0, 0);
		System.out.println("tillverkat en mapPart");

		MapView mapView = new MapView();
		mapView.moveTo(x, y, zoomlv);
		mapView.setMap(mappart);
		
		this.mapView=mapView;
		gameGui = new GameGui();
		gameGui.setMapView(mapView);
		gameGui.setAlwaysOnTop(true);

		// för scrollen ska fungera
		gameGui.addMouseWheelListener(this);
		gameGui.addKeyListener(this);


	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		float change=(float) (arg0.getClickCount()*arg0.getWheelRotation()*0.01);
		System.out.println("zoomlv :"+zoomlv +""+ change+" change");
		zoomlv=zoomlv+change;
		System.out.println("new zoom valure:"+ zoomlv);
		mapView.moveTo(x, y,zoomlv);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println("key pressed");
		System.out.println(e.getKeyChar());
		if(e.getKeyChar()=='w'){
			y-=3;
		}else if(e.getKeyChar()=='s'){
			y+=3;
		}else if(e.getKeyChar()=='a'){
			x-=3;
		}else if(e.getKeyChar()=='d'){
			x+=3;
			
		}
		mapView.moveTo(x, y,zoomlv);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("key released");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("keyTyped");
	}

}
