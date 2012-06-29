package controller;

import gui.ButtonPanel;
import gui.GameGui;
import gui.InventoryView;
import gui.InventoryView.ItemButton;
import gui.LogView;
import gui.MapView;
import gui.StatusView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import Items.Candle;

import domain.Map;
import domain.Player;
import domain.Tile;

public class GameController implements MouseWheelListener, KeyListener,
		Runnable {
	float zoomlv = 1;
	boolean shiftPressed = false;

	public static enum ACTION {
		GORIGTH, GOLEFT, GOUP, GODOWN, RUNDOWN, RUNUP, RUNLEFT, RUNRIGTH
	}

	ACTION action = null;
	GameGui gameGui = null;

	MapView mapView = null;
	Player player1 = null;
	Map worldMap = null;
	LogView logView = null;
	StatusView statusView = null;
	InventoryView inventoryView = null;

	public GameController() {
		System.out.println("gameController");
		worldMap = new Map();

		player1 = new Player(25444, 25444, this);
		player1.getInventory().add(new Candle());
		player1.getInventory().add(new Candle());
		player1.getInventory().add(new Candle());
		player1.getInventory().add(new Candle());

		// borde bara skicka det man ser.
		System.out.println("tillverkat en mapPart");

		logView = new LogView();
		mapView = new MapView(worldMap, player1);
		statusView = new StatusView(player1);
		inventoryView = new InventoryView(player1, new InventoryListener());

		gameGui = new GameGui();


		gameGui.setLogView(logView);
		gameGui.setStatusView(statusView);
		gameGui.setInventoryView(inventoryView);
		gameGui.setButtonPanel(new ButtonPanel(new ButtonPanelListener()));
		gameGui.setMapView(mapView);
		gameGui.setAlwaysOnTop(true);
		
		
		


//		gameGui.setAlwaysOnTop(true);

		// för scrollen ska fungera
		gameGui.addMouseWheelListener(this);
		gameGui.addKeyListener(this);
		new Thread(this).start();
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		float change = (float) (arg0.getClickCount() * arg0.getWheelRotation() * 0.01);
		// System.out.println("zoomlv :"+zoomlv +""+ change+" change");
		zoomlv = zoomlv + change;
		// System.out.println("new zoom valure:"+ zoomlv);
		mapView.moveCameraTo(player1.getPosX(), player1.getPosY(), zoomlv);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println(e.getKeyCode());
		if (shiftPressed) {
			if (e.getKeyCode() == 87|| e.getKeyCode() == 38) {
				action = ACTION.RUNUP;
				System.out.println("shift kay and W");
			} else if (e.getKeyCode() == 83|| e.getKeyCode() == 40) {
				action = ACTION.RUNDOWN;
			} else if (e.getKeyCode() == 65|| e.getKeyCode() == 37) {
				action = ACTION.RUNLEFT;
			} else if (e.getKeyCode() == 68|| e.getKeyCode() == 39) {
				action = ACTION.RUNRIGTH;
			}
		} else {
			if (e.getKeyChar() == 'w' || e.getKeyCode() == 38) {

				action = ACTION.GOUP;
			} else if (e.getKeyChar() == 's' || e.getKeyCode() == 40) {
				action = ACTION.GODOWN;
			} else if (e.getKeyChar() == 'a' || e.getKeyCode() == 37) {
				action = ACTION.GOLEFT;
			} else if (e.getKeyChar() == 'd' || e.getKeyCode() == 39) {
				action = ACTION.GORIGTH;
			}
		}
		if (e.getKeyChar() == 'z') {
			zoomlv += 0.1;
			logView.addString("Zoomade med z");
		} else if (e.getKeyChar() == 'x') {
			zoomlv -= 0.1;
			logView.addString("Zoomade med x");
		}
		if (e.getKeyCode() == 16) {
			shiftPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("reliased " + e.getKeyCode());
		if (e.getKeyCode() == 16) {
			System.out.println(" shiftkey reliased");
			shiftPressed = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		// System.out.println("keyTyped");
	}

	public Tile translatePxToTile(int x, int y, int layer) {

		int tileX = (int) ((x / 64));
		int tileY = (int) ((y / 64));

		System.out.println("punkten är" + tileX + ", " + tileY);
		return worldMap.getPoint(tileX, tileY, layer);
	}

	public boolean checkPositionIsOk(int topX, int topY, int botX, int botY) {

		for (int i = 0; i < worldMap.getLayers(); i++) {

			if (translatePxToTile(topX, botY, i) != null) {

				if (translatePxToTile(topX, botY, i).isSollid()) {
					System.out.println("it is a : "
							+ translatePxToTile(topX, botY, i).getName());
					return false;
				}
			}
			if (translatePxToTile(botX, topY, i) != null) {

				if (translatePxToTile(botX, topY, i).isSollid()) {
					System.out.println("it is a : "
							+ translatePxToTile(botX, topY, i).getName());
					return false;
				}
			}

			if (translatePxToTile(botX, botY, i) != null) {

				if (translatePxToTile(botX, botY, i).isSollid()) {
					System.out.println("it is a : "
							+ translatePxToTile(botX, botY, i).getName());
					return false;
				}
			}

			if (translatePxToTile(topX, topY, i) != null) {

				if (translatePxToTile(topX, topY, i).isSollid()) {
					System.out.println("it is a : "
							+ translatePxToTile(topX, topY, i).getName());
					return false;
				}
			}
		}
		return true;
	}

	// game loop
	@Override
	public void run() {// ska vara ca 60 fps
		while (true) {
			if (action != null) {
				player1.action(action);
				action = null;
			}
			//System.out.println("looping....");
			// System.out.println("looping....");
			// kolla vart den går
			// kolla om den får gå dit

			// rita om
			mapView.moveCameraTo(player1.getPosX(), player1.getPosY(), zoomlv);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	class ButtonPanelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("inventory")){
				inventoryView.setVisible(!inventoryView.isVisible());
				player1.getInventory().add(new Candle());
				inventoryView.updateView();
			}
			
		}
		
	}
	
	class InventoryListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getClass() != null && e.getSource() instanceof ItemButton){
				ItemButton ib = (ItemButton)e.getSource();
				player1.getInventory().remove(ib.getItem());
				inventoryView.updateView();
			}
			
		}
		
	}
}
