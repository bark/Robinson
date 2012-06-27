package domain;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import controller.GameController;
import controller.GameController.ACTION;

import Items.Item;

public class Player {

	int posX = 0;
	int posY = 0;

	// stats
	float spead = 4;

	int maxhp = 100;

	// inventory
	float hp = maxhp;
	float hunger = 40;
	float thirst = 100;
	float tempratur = 47;
	float tiredness = 80;

	ArrayList<Item> inventory = new ArrayList<Item>();
	ACTION action=null;
	Image pic = null;
	int frame = 0;
	GameController gc;

	public Player(int x, int y, GameController gc) {
		posX = x;
		posY = y;
		this.gc = gc;
		String uri = "./res/pic/soldier_altcolor.png";
		pic = Toolkit.getDefaultToolkit().getImage(uri);
	}

	public void drawItSelf(Graphics g, ImageObserver io, int x, int y,
			float zoom) {
		int picX = 0;
		int picY = 0;
		if (action == ACTION.UP) {

			picY = 0;
		} else if (action == ACTION.LEFT) {

			picY = 64;
		} else if (action == ACTION.DOWN) {

			picY = 128;
		} else if (action == ACTION.RIGTH) {

			picY = 128 + 64;
		}
		picX = 64 * frame;

		g.drawImage(pic, x, y, x + (int) (64 * zoom), y + (int) (64 * zoom),
				picX, picY, picX + 64, picY + 64, io);
		// g.setColor(Color.RED);
		// g.fillOval(x,y,(int) (64*zoom),(int)(64*zoom));
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	private void goUp() {

		if (action == ACTION.UP) {
			frame = (frame + 1) % 9;
		} else {
			frame = 0;
			action = ACTION.UP;
		}
		int newposY = (int) (posY - spead);

		// kollar om den är sollid
		if (gc.checkPositionIsOk(posX + 10, newposY + 12, posX + 64 - 10,
				newposY + 64 - 5)) { // +32 tittar på mitten av gubben

			posY = newposY;
		} else {
			System.out.println("punkten är" + (int) posX / 64 + ", "
					+ (int) newposY / 64 + " och är INTE gå bar");
		}

	}

	private void goDown() {
		if (action == ACTION.DOWN) {
			frame = (frame + 1) % 9;
		} else {
			frame = 0;
			action = ACTION.DOWN;
		}
		int newposY = (int) (posY + spead);

		// kollar om den är sollid

		if (gc.checkPositionIsOk(posX + 10, newposY + 12, posX + 64 - 10,
				newposY + 64 - 5)) { // +32 tittar på mitten av gubben
			posY = newposY;
		} else {
			System.out.println("punkten är" + (int) posX / 64 + ", "
					+ (int) newposY / 64 + " och är INTE gå bar");
		}
	}

	private void goRigth() {
		if (action == ACTION.RIGTH) {
			frame = (frame + 1) % 9;
		} else {
			frame = 0;
			action = ACTION.RIGTH;
		}
		int newposX = (int) (posX + spead);

		// kollar om den är sollid
		if (gc.checkPositionIsOk(newposX + 10, posY + 12, newposX + 64 - 10,
				posY + 64 - 5)) {
			System.out.println("punkten är" + newposX / 64 + ", " + posY / 64
					+ " och är gå bar");
			posX = newposX;
		} else {
			System.out.println("punkten är" + (int) newposX / 64 + ", "
					+ (int) posY / 64 + " och är INTE gå bar");
		}
	}

	private void goLeft() {
		if (action == ACTION.LEFT) {
			frame = (frame + 1) % 9;
		} else {
			frame = 0;
			action = ACTION.LEFT;
		}
		int newposX = (int) (posX - spead);

		// kollar om den är sollid
		if (gc.checkPositionIsOk(newposX + 10, posY + 12, newposX + 64 - 10,
				posY + 64 - 5)) {
			System.out.println("punkten är" + newposX / 64 + ", " + posY / 64
					+ " och är gå bar");
			posX = newposX;
		} else {
			System.out.println("punkten är" + (int) newposX / 64 + ", "
					+ (int) posY / 64 + " och är INTE gå bar");
		}

	}

	public int getHpLeft() {
		return (int) (getMaxhp() / getHp()) * 100;
	}

	public int getMaxhp() {
		return maxhp;
	}

	public void setMaxhp(int maxhp) {
		this.maxhp = maxhp;
	}

	public float getHp() {
		return hp;
	}

	public void setHp(float hp) {
		this.hp = hp;
	}

	public int getHunger() {
		return (int) hunger;
	}

	public void setHunger(float hunger) {
		this.hunger = hunger;
	}

	public int getThirst() {
		return (int) thirst;
	}

	public void setThirst(float thirst) {
		this.thirst = thirst;
	}

	public float getTempratur() {
		return tempratur;
	}

	public void setTempratur(float tempratur) {
		this.tempratur = tempratur;
	}

	public int getTiredness() {
		return (int) tiredness;
	}

	public void setTiredness(float tiredness) {
		this.tiredness = tiredness;
	}

	public void addItem(Item item) {
		inventory.add(item);
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public void action(ACTION action) {
		//this.action=action;
		switch (action) {
		case UP:
			goUp();
			break;
		case DOWN:
			goDown();
			break;
		case LEFT:
			goLeft();
			break;
		case RIGTH:
			goRigth();
			break;

		default:
			break;
		}
		// TODO Auto-generated method stub

	}

}
