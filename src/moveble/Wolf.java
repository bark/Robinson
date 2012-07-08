package moveble;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Wolf  extends Moveable {
	
	public Wolf(int x, int y) {
		super(x, y);
		img=new BufferedImage(32,32, BufferedImage.TYPE_INT_ARGB);
		Graphics imgG = img.getGraphics();
		imgG.setColor(Color.RED);
		imgG.fillRect(0, 0, 32, 32);
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		
	}

}
