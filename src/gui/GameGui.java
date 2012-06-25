package gui;

import java.awt.BorderLayout;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameGui extends JFrame{
	MapView mapView=null;
	
	public GameGui(){
		super();
		setLayout(null);
		getContentPane().setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
		setFocusable(true);
		KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
       
		setTitle("Robisson");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().add(new JLabel("om denna syns är något fel"), BorderLayout.CENTER);
		System.out.println("starting game window");
		setSize(300, 300);
		pack();
		setVisible(true);
		
	}
	
	public void setMapView(MapView mapView) {
		getContentPane().removeAll();
		this.mapView=mapView;
		//this.getContentPane().add(mapView, BorderLayout.CENTER);
		this.add(mapView);
		mapView.setBounds(25 , 25,
				500,500);//Window.HEIGHT, Window.HEIGHT);
		mapView.setFocusable(true);
		
		pack();
		setVisible(true);
		System.out.println("setMapView");
	}
	public void move(int x,int y,float zoom) {
		System.out.println("new valure x:"+x +" y:"+y);
		System.out.println(mapView);
		if(mapView!=null){
			
			mapView.setBounds(x , y,
					500,500);
					// Window.HEIGHT, Window.HEIGHT);
			pack();
			repaint();
			
		}
	}
}
