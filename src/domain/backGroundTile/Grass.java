package domain.backGroundTile;

import java.awt.Color;
import java.awt.Graphics;

public class Grass extends BackgroundTile{
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//g.drawLine(0, 0, 200, 400);
		
		g.setColor(Color.RED);
		g.fillOval(0,0, 10, 10);

		//g.drawRect(0,0,100,100);
		
		//g.dispose();

		
	}
}
