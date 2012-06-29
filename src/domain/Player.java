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
	ACTION action = null;
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
		if (action == ACTION.GOUP || action == ACTION.RUNUP) {

			picY = 0;
		} else if (action == ACTION.GOLEFT || action == ACTION.RUNLEFT) {

			picY = 64;
		} else if (action == ACTION.GODOWN || action == ACTION.RUNDOWN) {

			picY = 128;
		} else if (action == ACTION.GORIGTH || action == ACTION.RUNRIGTH) {

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
		move(action);
	}

	private void move(ACTION action2) {
		int newposX =posX;
		int newposY =posY; 
		if (action == action2) {
			frame = (frame + 1) % 9;
		} else {
			frame = 0;
			action = action2;
		}
		switch (action2) {
			case GODOWN:
				newposY = (int) (posY + spead);
				break;
			case GOUP:
				newposY = (int) (posY - spead);
				break;
			case GOLEFT:
				newposX = (int) (posX - spead);
				break;
			case GORIGTH:
				newposX = (int) (posX + spead);
				break;
			case RUNUP:
				newposY = (int) (posY - (spead*3));
				break;
			case RUNDOWN:
				newposY = (int) (posY + (spead*3));
				break;
			case RUNRIGTH:
				newposX = (int) (posX +(spead*3));
				break;
			case RUNLEFT:
				newposX = (int) (posX - (spead*3));
				break;
			default:
				break;
		}
		// kollar om den är sollid
		if (gc.checkPositionIsOk(newposX + 16, newposY + 46, newposX + 44,
				newposY + 64 -5)) {
			System.out.println("punkten är" + newposX / 64 + ", " + newposY / 64
					+ " och är gå bar");
			posX = newposX;
			posY = newposY;
		} else {
			System.out.println("punkten är" + (int) newposX / 64 + ", "
					+ (int) posY / 64 + " och är INTE gå bar");
		}
	}
}
