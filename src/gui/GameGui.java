package gui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameGui extends JFrame{

	JPanel mainPanel = null;

	MapView mapView= null;
	LogView logView = null;
	StatusView statusView = null;
	ButtonPanel buttonPanel = null;
	InventoryView inventoryView = null;

	public GameGui(){
		super();
		getContentPane().setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
		setFocusable(true);

		setTitle("Robisson");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.getContentPane().add(new JLabel("om denna syns är något fel"), BorderLayout.CENTER);
		System.out.println("starting game window");
		pack();
		setVisible(true);

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
//		mainPanel.setLayout(new GridLayout());

		mainPanel.setBounds(0, 0, getContentPane().getWidth(), getContentPane().getHeight()-22);
		mainPanel.setSize(getContentPane().getWidth(), getContentPane().getHeight()-22);
		mainPanel.setVisible(true);
		this.add(mainPanel);




	}

	public void setMapView(MapView mapView) {
		this.mapView=mapView;
		mapView.setBounds(0, 0, this.getContentPane().getWidth(), this.getContentPane().getHeight());
//		mapView.setBounds(100, 100, 300, 300);
		mainPanel.add(mapView);
		mapView.setFocusable(true);		

//		pack();
		setVisible(true);
		System.out.println("setMapView");
	}

	public void setStatusView(StatusView statusView) {
		this.statusView = statusView;
		statusView.setBounds(mainPanel.getWidth()-statusView.getWidth(), mainPanel.getHeight()-statusView.getHeight(),
				statusView.getWidth(), statusView.getHeight());
//		statusView.setBounds(500, 500, 200, 400);
		mainPanel.add(statusView);
		statusView.setFocusable(true);
		
//		pack();
		setVisible(true);
		
		
		System.out.println("setStatusView");

		
		
	}
	
	public void setLogView(LogView logView) {
		this.logView = logView;
		mainPanel.add(logView);
		logView.setBounds(0, mainPanel.getHeight()-logView.getHeight(), logView.getWidth(), logView.getHeight());
//		logView.setLocation(10, mainPanel.getHeight()-logView.getHeight());
		logView.setFocusable(true);
		System.out.println("setLogView");
		mainPanel.revalidate();
		mainPanel.repaint();
		
	}
	
	public void setButtonPanel(ButtonPanel buttonPanel){
		this.buttonPanel = buttonPanel;
		mainPanel.add(buttonPanel);
		int x = mainPanel.getWidth()-statusView.getWidth()-buttonPanel.getWidth();
		int y = mainPanel.getHeight()-buttonPanel.getHeight()-22;
		buttonPanel.setBounds(x, y, buttonPanel.getWidth(), buttonPanel.getHeight());
		System.out.println("setButtonPanel");
	}
	
	public void setInventoryView(InventoryView inventoryView) {
		this.inventoryView = inventoryView;
		mainPanel.add(inventoryView);
		int x = mainPanel.getWidth()/2-inventoryView.getWidth()/2;
		int y = mainPanel.getHeight()/2-inventoryView.getHeight()/2;
		inventoryView.setBounds(x, y, inventoryView.getWidth(), inventoryView.getHeight());
		System.out.println("setInventoryView");
	}
	
	

}
