package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;

import javax.swing.JComponent;

//this is the log for the game.
public class LogView extends JComponent {
	
	LinkedList<String> message=new LinkedList<String>();
	
	public LogView(){
		this.setSize(200, 100);
		repaint();
		this.setVisible(true);
		this.setEnabled(true);
	}
	
	public void paint(Graphics g) {
//		super.paint(g);
		System.out.println("Drawing LogView: "+getX()+", "+getY());
		System.out.println("LogView bounds: "+getBounds().x+","+getBounds().y+","+getBounds().width+","+getBounds().height);
		
		int count = 0;
		g.setColor(Color.gray);
		g.drawRect(0, 0, getWidth(), getHeight());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		for(String s:message){
			g.setColor(Color.BLACK);
			if(count > 0){
				g.setColor(Color.DARK_GRAY);
			}
			g.drawString(s, 5, (int)(90 - count * g.getFont().getSize() * 1.5));
			System.out.println("LogView bounds: "+getBounds().x+5+","+(int)(getBounds().y+90-count*g.getFont().getSize() * 1.5)+","+getBounds().width+","+getBounds().height);
			count++;
			if(count == 5){
				break;
			}
		}
		
	};
	
	public void addString(String s){
		message.addFirst(s);
	}
}
