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
import domain.Tile;

public class GameController implements MouseWheelListener, KeyListener {
	float zoomlv = 1;
	GameGui gameGui = null;

	MapView mapView = null;
	Player player1 = null;
	Map worldMap = null;
	LogView logView = null;

	public GameController() {
		System.out.println("gameController");
		worldMap = new Map();

		player1 = new Player(256, 256, this);

		// borde bara skicka det man ser.
		worldMap.getPart(0, 0);
		System.out.println("tillverkat en mapPart");


		StatusView statusView=new StatusView(player1);		
		logView = new LogView();
		mapView = new MapView(worldMap,statusView,logView, player1);
		
		gameGui = new GameGui();
		gameGui.setMapView(mapView);
		gameGui.setAlwaysOnTop(true);

		// för scrollen ska fungera
		gameGui.addMouseWheelListener(this);
		gameGui.addKeyListener(this);

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		float change = (float) (arg0.getClickCount() * arg0.getWheelRotation() * 0.01);
		// System.out.println("zoomlv :"+zoomlv +""+ change+" change");
		zoomlv = zoomlv + change;
		// System.out.println("new zoom valure:"+ zoomlv);
		mapView.moveTo(player1.getPosX(), player1.getPosY(), zoomlv);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println(e.getKeyCode());
		if (e.getKeyChar() == 'w' || e.getKeyCode() == 38) {
			player1.goUp();
		} else if (e.getKeyChar() == 's' || e.getKeyCode() == 40) {
			player1.goDown();
		} else if (e.getKeyChar() == 'a' || e.getKeyCode() == 37) {
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

		mapView.moveTo(player1.getPosX(), player1.getPosY(), zoomlv);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("key released");
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("keyTyped");
	}

	public Tile translatePxToTile(int x, int y, int layer) {
		
		int tileX=(int) ((x / 64));
		int tileY=(int) ((y / 64));
		
		System.out.println("punkten är" + tileX+ ", " + tileY);
		return worldMap.getPoint(tileX, tileY, layer);
	}

	public boolean checkPositionIsOk(int x, int y) {
		if(x<0||y<0){
			return false;
		}
		for (int i = 0; i < worldMap.getLayers(); i++) {
			if (translatePxToTile(x, y, i) != null) {
				if (translatePxToTile(x, y, i).isSollid()) {
					 System.out.println("it is a : "+translatePxToTile(x, y, i).getName());
					return false;
				}
			}
		}
		return true;
	}

}
