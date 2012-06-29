package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import Items.Item;
import domain.Player;

public class InventoryView extends JPanel{
	
	private Player player;
	private ActionListener l;
	
	public InventoryView (Player player, ActionListener l){
		this.player = player;
		this.l = l;
		this.setSize(400, 600);
		this.setBackground(Color.GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		GridLayout layout = new GridLayout(400/32, 600/32);

		layout.layoutContainer(this);
		
		this.setLayout(layout);
		this.setVisible(false);
		
		
	}
	
	public void updateView(){
		this.removeAll();
		for(Item item:player.getInventory()){
			this.add(new ItemButton(item));
		}
		
	}
	
	
	
	
	
	public class ItemButton extends JButton {
		
		private Item item;
		public ItemButton(Item item){
			setSize(34,34);
			this.item = item;
			this.setIcon(new ImageIcon(item.getImage()));
			this.addActionListener(l);
		}
		
		public Item getItem(){
			return item;
		}

	}
	

}
