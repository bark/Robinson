package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;


import Items.Item;

public class GameGui extends JFrame{

	JPanel mainPanel = null;

	MapView mapView= null;
	LogView logView = null;
	StatusView statusView = null;
	ButtonPanel buttonPanel = null;
	InventoryView inventoryView = null;
	Item itemToPaint = null;

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
	
	private void showItem(Item item){
		ItemInfoPanel iip = new ItemInfoPanel(item);
		mainPanel.add(iip);
		iip.setBounds(inventoryView.getX()+inventoryView.getWidth()+10
					, inventoryView.getY(), iip.getWidth(), iip.getHeight());
	}
	
	public void setItemToPaint(Item item){
		this.itemToPaint = item;
		redoPaintOrder();
	}
	
	class ItemInfoPanel extends JPanel {
		
		private Item item;
		public ItemInfoPanel(Item item) {
			setSize(120, 75);
			setBackground(Color.GRAY);
			setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			setVisible(true);
			this.item = item;
		}
		
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(item.getImage(), 2, 2, null);
			g.drawString("ItemName", 5, item.getImage().getHeight(this)+15+g.getFont().getSize());
			g.drawString("Desc desc desc desc desc", item.getImage().getWidth(this)+15, 5+g.getFont().getSize());
			
		}
		
		
	}
	
	private void redoPaintOrder(){
		
		if(logView != null){
			setLogView(logView);
		}
		if(statusView != null){
			setStatusView(statusView);
		}
		if(inventoryView != null){
			setInventoryView(inventoryView);
		}
		if(buttonPanel != null){
			setButtonPanel(buttonPanel);
		}
		if(itemToPaint != null){
			showItem(itemToPaint);
		}
		if(mapView != null){
			setMapView(mapView);
		}
		
	}
	
	

}
