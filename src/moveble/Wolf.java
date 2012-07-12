package moveble;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import controller.PlayerInterface;

import domain.GameModel;
import domain.Player;

public class Wolf extends Moveable {
	int spead = 10;
	int wait = 0;

	public Wolf(int x, int y) {
		super(x, y);
		img = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
		Graphics imgG = img.getGraphics();
		imgG.setColor(Color.BLUE);
		imgG.fillRect(0, 0, 32, 32);
	}

	@Override
	public void action() {
		if (wait == 0) {
			Player closesPlayer = null;
			double minlength = 999999;
			for (PlayerInterface PlayerInterface : GameModel.getPlayers()) {
				Player player = PlayerInterface.getPlayer();
				double length = hypetunusan(x, y, player.getPosX(),
						player.getPosY());
				if (length < minlength) {
					minlength = length;
					closesPlayer = player;
				}
			}
			double vinkeln = Math.atan2((x - closesPlayer.getPosX()), (y
					- closesPlayer.getPosY() + 1));

			int diffX = (int) (Math.sin(vinkeln) * spead);
			int diffY = (int) (Math.cos(vinkeln) * spead);
			if (GameModel.GetWorldMap().checkPositionIsOk(x - diffX, y,
					x - diffX + 20, y + 20)) {
				x -= diffX;
			} else if (diffY > 0) {
				diffY += diffX;
			} else {
				diffY -= diffX;
			}
			if (GameModel.GetWorldMap().checkPositionIsOk(x, y - diffY, x + 20,
					y - diffY + 20)) {
				y -= diffY;
			} else if (GameModel.GetWorldMap().checkPositionIsOk(x - diffY, y,
					x + 20 - diffY, y + 20)) {
				x -= diffY;
			} else if (GameModel.GetWorldMap().checkPositionIsOk(x + diffY, y,
					x + diffY + 20, y + 20)) {
				x += diffY;
			}
			if (minlength < 10) {
				closesPlayer.loseHp(20);
				wait = 10;
			}
			if (minlength > 1000) {
				System.out.println("TransformToTile");
				TransformToTile();
			}
		} else {
			wait--;
		}
	}

	@Override
	public void drawItSelf(Graphics g, ImageObserver io, int x, int y,
			int with, int higth, float zoom) {
		// TODO Auto-generated method stub

	}

}
