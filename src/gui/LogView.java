package gui;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

//this is the log for the game.
public class LogView {
	ArrayList<String> message=new ArrayList<String>();
	public void drawItSelf( Graphics g,ImageObserver io,int x,int y,float zoom){
		for(String s:message){
			g.
		}
	}
	public void addString(String s){
		message.add(s);
	}
}
