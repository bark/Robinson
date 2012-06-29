package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import javax.swing.JComponent;

import domain.Player;

public class StatusView extends JComponent {
	Player player=null;
	public StatusView(Player player) {
		this.player=player;
		this.setSize(400, 200);
		this.setVisible(true);
		repaint();
		
	}
	
	@Override
	public void paint(Graphics g) {
//		super.paint(g);
		drawItSelf(g,null,0,0, 0f);
	}
	
	public void drawItSelf( Graphics g,ImageObserver io,int x,int y,float zoom){
		g.setColor(Color.GRAY);
		g.fillRect(x, y, 400, 200);	
		
		g.setColor(Color.RED);
		g.fillRect(x+300, y+(200-(player.getHpLeft()*2)), 90, 200);
		
		
		
		g.setColor(Color.GREEN);
		g.fillRect(x+200, y+(200-(player.getHunger()*2)), 90, 200);
		
		
		g.setColor(Color.BLUE);
		g.fillRect(x+100, y+( 200-(player.getThirst()*2)), 90, 200);
		
		g.setColor(Color.YELLOW);
		g.fillRect(x, y+(200-(player.getTiredness()*2)), 90, 200);
		
		
		g.setColor(Color.GRAY);
		g.fillRect(x, y+180, 400, 200);	
		
		g.setColor(Color.BLACK);
		g.drawChars("hp".toCharArray(), 0, 2, x+350, y+193);
		g.drawChars("Hunger".toCharArray(), 0, 6, x+220, y+193);
		g.drawChars("Thirst".toCharArray(), 0, 6, x+120, y+193);
		g.drawChars("Tiredness".toCharArray(), 0, 9, x+20, y+193);
	
		
	}
	
}
