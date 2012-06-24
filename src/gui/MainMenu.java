package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicOptionPaneUI.ButtonActionListener;

public class MainMenu extends JFrame {
	private JButton totorial;
	private JButton newGame;
	private JButton config;	
	private JButton loadGame;
	private JButton exit;

	//tutorial
	//new game
	//load game
	//config
	//exit
	public MainMenu(){
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jpanel=new JPanel(); 
		jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.PAGE_AXIS));
		jpanel.add(Box.createRigidArea(new Dimension(0,5)));
		totorial=new JButton("totorial");
		jpanel.add(totorial, BorderLayout.CENTER);
		newGame=new JButton("new Game");
		jpanel.add(newGame, BorderLayout.CENTER);
		loadGame=new JButton("load Game");
		this.getContentPane().add(loadGame, BorderLayout.CENTER);
		config = new JButton("config");
		jpanel.add(config, BorderLayout.CENTER);
		exit=new JButton("Exit");
		jpanel.add(exit, BorderLayout.CENTER);
		this.getContentPane().add(jpanel);
		this.pack();
		
		this.setVisible(true);
	}
    public 	void SetButtonListner(ActionListener ActionListener){
    	System.out.println("SetButtonListner");
    	totorial.addActionListener(ActionListener);
		newGame.addActionListener(ActionListener);
		config.addActionListener(ActionListener);
		loadGame.addActionListener(ActionListener);
		exit.addActionListener(ActionListener);
    }
	
}
