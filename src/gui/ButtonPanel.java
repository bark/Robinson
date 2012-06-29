package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	
	
	JButton menuButton;
	JButton inventoryButton;
	JButton abilitiesButton;

	
	
	public ButtonPanel(ActionListener a){
		this.setLayout(new GridLayout(3, 1));
		this.setSize(100, 200);
		this.setBackground(Color.GRAY);
		
		
		menuButton = new JButton("Menu");
		menuButton.setActionCommand("menu");
		menuButton.addActionListener(a);
		this.add(menuButton);
		
		inventoryButton = new JButton("Inventory");
		inventoryButton.setActionCommand("inventory");
		inventoryButton.addActionListener(a);
		this.add(inventoryButton);
		
		
		abilitiesButton = new JButton("Abilities");
		abilitiesButton.setActionCommand("abilities");
		abilitiesButton.addActionListener(a);
		this.add(abilitiesButton);
		
		this.setVisible(true);
		
		
		
	}
	
	
	
	
	
	
	
	
}
