package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.LinkedList;

//this is the log for the game.
public class LogView {
	LinkedList<String> message=new LinkedList<String>();
	public void drawItSelf(Graphics g,ImageObserver io,int x,int y,float zoom){
		int count = 0;
		g.setColor(Color.gray);
		g.drawRect(x, y, 200, 100);
		g.fillRect(x, y, 200, 100);
		for(String s:message){
			g.setColor(Color.BLACK);
			if(count > 0){
				g.setColor(Color.DARK_GRAY);
			}
			g.drawString(s, x+5, (int)(y+90 - count * g.getFont().getSize() * 1.5));
			count++;
			if(count == 5){
				break;
			}
		}
	}
	public void addString(String s){
		message.addFirst(s);
	}
}
