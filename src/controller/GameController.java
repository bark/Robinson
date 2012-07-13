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
import java.util.ArrayList;

import moveble.Hare;
import moveble.Moveable;
import moveble.Wolf;

import Items.Candle;
import Items.Item;

import domain.GameModel;
import domain.GameModel.ACTION;
import domain.Player;
import domain.Tile;
import domain.map.Map;
import domain.map.SortedTileList;

//TODO dela denna klassen till game model med.

public class GameController implements MouseWheelListener, KeyListener,
		Runnable {
	float zoomlv = 1;
	boolean shiftPressed = false;

	private static final int FRAME_DELAY = 200; // 20ms. implies 50fps (1000/20)
												// = 50

	

	ACTION action = null;
	GameGui gameGui = null;

	MapView mapView = null;
//	Map worldMap = null;
	LogView logView = null;
	StatusView statusView = null;
	InventoryView inventoryView = null;
	long cycleTime;
	
	public GameController() {
		System.out.println("gameController");
	

		Player player1 = new Player(25500, 25444, this);
		player1.getInventory().add(new Candle());
		LocalPlayer localPlayer = new LocalPlayer(player1);
		GameModel.addPlayers(localPlayer);
		//GameModel.addAnimals(new Wolf(25600,25844));
		
		// borde bara skicka det man ser.
		System.out.println("tillverkat en mapPart");

		logView = new LogView();
		mapView = new MapView();
		statusView = new StatusView(player1);
		inventoryView = new InventoryView(player1, new InventoryListener());

		gameGui = new GameGui();
		
		gameGui.setLogView(logView);
		gameGui.setStatusView(statusView);
		gameGui.setInventoryView(inventoryView);
		gameGui.setButtonPanel(new ButtonPanel(new ButtonPanelListener()));
		gameGui.setMapView(mapView);
		gameGui.setAlwaysOnTop(true);

		// gameGui.setAlwaysOnTop(true);

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
		mapView.moveCameraTo(GameModel.getLocalPlayer().getPosX(), GameModel.getLocalPlayer().getPosY(), zoomlv);
	}

	@Override
	public void keyPressed(KeyEvent e) {
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
		((LocalPlayer)GameModel.getPlayers().get(0)).keyPressed(e,shiftPressed);
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == 16) {
			shiftPressed = false;
		}
		action = null;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// System.out.println("keyTyped");
	}


	// game loop
	@Override
	public void run() {// ska vara ca 60 fps
		while (true) {
			cycleTime = System.currentTimeMillis();
			
		//	GameModel.getPlayers().get(0).action(action);
			action = null;
			for(PlayerInterface player:GameModel.getPlayers()){
				player.tick();
			}
			
			
			for(Moveable aAnimal:GameModel.getAnimals()){
				aAnimal.action();
			}
			GameModel.removeAnimals();
			// System.out.println("looping....");
			// System.out.println("looping....");
			// kolla vart den går
			// kolla om den får gå dit

			// rita om
			mapView.moveCameraTo(GameModel.getLocalPlayer().getPosX(), GameModel.getLocalPlayer().getPosY(), zoomlv);
			mapView.repaint();
			synchFramerate();
			// Thread.sleep(100);
		}
	}

	private void synchFramerate() {
		cycleTime = cycleTime + FRAME_DELAY;
		long difference = cycleTime - System.currentTimeMillis();
		try {
			Thread.sleep(Math.max(0, difference));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	class ButtonPanelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("inventory")) {
				inventoryView.setVisible(!inventoryView.isVisible());
				GameModel.getLocalPlayer().getInventory().add(new Candle());
				inventoryView.updateView();
			}

		}

	}

	class InventoryListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getClass() != null && e.getSource() instanceof ItemButton) {
				ItemButton ib = (ItemButton) e.getSource();
				if ((e.getModifiers() & e.SHIFT_MASK) != 0) {
					logView.addString("Used item: " + ib.getItem().toString());
					System.out.println("SHIFT_INVENTORY_CLICK");
				} else if ((e.getModifiers() & e.CTRL_MASK) != 0) {
					GameModel.getLocalPlayer().getInventory().remove(ib.getItem());
					System.out.println("CTRL_INV_CLICK");
				} else {
					gameGui.setItemToPaint(ib.getItem());
					System.out.println("INV CLICK");
				}

				inventoryView.updateView();
				inventoryView.validate();
			}

		}

	}

	public Item picUp(Player player) {
		
		int xvalure=(player.getPosX()+32)/32;
		int yvalure=(player.getPosY()+64)/32;
		System.out.println("pass on pickup:"+xvalure+":"+yvalure);
		return GameModel.GetWorldMap().getPointFromPx(player.getPosX()+32,player.getPosY()+64).pickUp(player);		
		
	}
	public void use(Player player) {
		
		int xvalure=(player.getPosX()+32)/32;
		int yvalure=(player.getPosY()+64)/32;
		System.out.println("pass on use:"+xvalure+":"+yvalure);
		GameModel.GetWorldMap().getPointFromPx(player.getPosX()+32,player.getPosY()+64).use(player);		
		
	}
}
