package controller;

import gui.GameGui;
import gui.MapView;
import gui.StatusView;

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
import domain.Player;

public class GameController implements MouseWheelListener,KeyListener {
	float zoomlv = 1;
	GameGui gameGui = null;
	MapView mapView =null;
	Player player1=null;
	public GameController() {
		System.out.println("gameController");
		Map worldMap = new Map();
		
		player1=new Player(2000,2000);
		
		
		// borde bara skicka det man ser.
		MapPart mappart = worldMap.getPart(0, 0);
		System.out.println("tillverkat en mapPart");

		StatusView statusView=new StatusView(player1);
		MapView mapView = new MapView(mappart,statusView,player1);
		//mapView.moveTo(player1.getPosX(),player1.getPosY(), zoomlv);
		//mapView.setMap();
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
		//System.out.println("zoomlv :"+zoomlv +""+ change+" change");
		zoomlv=zoomlv+change;
		//System.out.println("new zoom valure:"+ zoomlv);
		mapView.moveTo(player1.getPosX(),player1.getPosY(),zoomlv);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("key pressed");
		//System.out.println(e.getKeyCode());
		if(e.getKeyChar()=='w'||e.getKeyCode()==38){
			player1.goUp();
		}else if(e.getKeyChar()=='s'||e.getKeyCode()==40){
			player1.goDown();
		}else if(e.getKeyChar()=='a'||e.getKeyCode()==37){
			player1.goLeft();
		}else if(e.getKeyChar()=='d'||e.getKeyCode()==39){
			player1.goRigth();
			
		}
		
		mapView.moveTo(player1.getPosX(),player1.getPosY(),zoomlv);
		
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
