package controller;

import gui.GameGui;
import gui.LogView;
import gui.MapView;
import gui.StatusView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import domain.Map;
import domain.MapPart;
import domain.Player;

public class GameController implements MouseWheelListener,KeyListener {
	float zoomlv = 1;
	GameGui gameGui = null;
	MapView mapView =null;
	Player player1=null;
	LogView logView = null;
	public GameController() {
		System.out.println("gameController");
		Map worldMap = new Map();
		
		player1=new Player(2000,2000);
		
		
		// borde bara skicka det man ser.
		MapPart mappart = worldMap.getPart(0, 0);
		System.out.println("tillverkat en mapPart");

		StatusView statusView=new StatusView(player1);
		
		logView = new LogView();
		
		MapView mapView = new MapView(mappart,statusView,logView, player1);
		
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
		//System.out.println(e.getKeyCode());
		if(e.getKeyChar()=='w'||e.getKeyCode()==38){
			player1.goUp();
		}else if(e.getKeyChar()=='s'||e.getKeyCode()==40){
			player1.goDown();
		}else if(e.getKeyChar()=='a'||e.getKeyCode()==37){
			player1.goLeft();
		}else if(e.getKeyChar()=='d'||e.getKeyCode()==39){
			player1.goRigth();	
		}else if(e.getKeyChar()=='z'){
			zoomlv += 0.1;
			logView.addString("Zoomade med z");
		}else if(e.getKeyChar()=='x'){
			zoomlv -= 0.1;
			logView.addString("Zoomade med x");
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
