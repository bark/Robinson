package domain.backGroundTile;

import java.awt.Color;
import java.awt.Graphics;

public class Grass extends BackgroundTile{
	
	@Override
	public void paint(Graphics g) {
		//g.drawLine(0, 0, 200, 400);
		g.setColor(Color.RED);
		g.clearRect (0,0,100,100);
		System.out.println("line");
		super.paint(g);
		
	}
}
