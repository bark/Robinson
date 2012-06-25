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

public class GameController implements MouseWheelListener {
	int x = 25;
	int y = 25;
	int zoomlv = 1;
	GameGui gameGui = null;

	public GameController() {
		System.out.println("gameController");
		Map worldMap = new Map();

		// borde bara skicka det man ser.
		MapPart mappart = worldMap.getPart(0, 0);
		System.out.println("tillverkat en mapPart");

		MapView mapView = new MapView();
		mapView.setMap(mappart);
		gameGui = new GameGui();
		gameGui.setMapView(mapView);
		gameGui.setAlwaysOnTop(true);

		// för scrollen ska fungera
		gameGui.addMouseWheelListener(this);

		InputMap myInputMap = mapView.getInputMap(JComponent.WHEN_FOCUSED);
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "a");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "w");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "s");
		myInputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "d");
		myInputMap.put(KeyStroke.getKeyStroke("SPACE"), "space");

		ActionMap myActionMap = mapView.getActionMap();
		AbstractAction space = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("space är tryckt");

			}
		};
		AbstractAction a = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("move left");
				x += 5;
				gameGui.move(x, y,zoomlv);
			}
		};
		AbstractAction w = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("move forward");
				y += 5;
				gameGui.move(x, y,zoomlv);
			}
		};
		AbstractAction s = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("move back");
				y -= 5;
				gameGui.move(x, y,zoomlv);
			}
		};
		AbstractAction d = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("move rigth");
				x -= 5;
				gameGui.move(x, y,zoomlv);
			}
		};
		myActionMap.put("a", a);
		myActionMap.put("w", w);
		myActionMap.put("s", s);
		myActionMap.put("d", d);
		myActionMap.put("space", space);

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent arg0) {
		
		System.out.println("scrollning");
	}

}
