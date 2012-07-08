package moveble;

import java.awt.Image;

public abstract class Moveable {
	int x = 0;
	int y = 0;
	Image img = null;

	public Moveable(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public abstract void action();

	public Image getImg() {
		return img;
	}

	public int getPosX() {
		return x;
	}

	public int getPosY() {
		return y;
	}

	protected double hypetunusan(int startX, int startY, int endX, int endY) {
		int lengthX = (startX - endX);
		int lengthY = (startY - endY);
		return Math.sqrt((lengthX * lengthX) + (lengthY * lengthY));

	}
}
