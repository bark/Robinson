package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;

import Items.Item;
import domain.Player;

public class InventoryView extends JPanel{
	
	private Player player;
	
	public InventoryView (Player player){
		this.player = player;
		this.setSize(400, 600);
		this.setBackground(Color.GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		this.setLayout(new GridLayout(4, 6));
		this.setVisible(false);
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.removeAll();
		for(Item item:player.getInventory()){
			this.add(new ItemImage(item.getImage()));
		}
		this.repaint();
	}
	
	
	
	
	
	
	class ItemImage extends JComponent {
		
		private Image image;
		public ItemImage(Image image) {
			setSize(10,10);
			this.image = image;
		}
		
		@Override
		public void paint(Graphics g) {
			g.drawImage(image, 0, 0, null);
		}

	}
	

}
