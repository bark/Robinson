package domain;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import controller.GameController;

import Items.Item;

public class Player {
	public enum Direction {
		RIGTH, LEFT, UP, DOWN
	}

	int posX = 0;
	int posY = 0;

	// stats
	float spead = 2;

	int maxhp = 100;

	// inventory
	float hp = maxhp;
	float hunger = 40;
	float thirst = 100;
	float tempratur = 47;
	float tiredness = 80;

	ArrayList<Item> inventory = new ArrayList<Item>();

	Direction dir = Direction.DOWN;
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
		if (dir == Direction.UP) {

			picY = 0;
		} else if (dir == Direction.LEFT) {

			picY = 64;
		} else if (dir == Direction.DOWN) {

			picY = 128;
		} else if (dir == Direction.RIGTH) {

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

	public void goUp() {

		if (dir == Direction.UP) {
			frame = (frame + 1) % 9;
		} else {
			frame = 0;
			dir = Direction.UP;
		}
		int newposY = (int) (posY - spead);

		// kollar om den är sollid
		if (gc.checkPositionIsOk(posX+32, newposY+32)) { //+32 tittar på mitten av gubben
			
			posY = newposY;
		} else {
			System.out.println("punkten är" + (int) posX / 64 + ", "
					+ (int) newposY / 64 + " och är INTE gå bar");
		}

	}

	public void goDown() {
		if (dir == Direction.DOWN) {
			frame = (frame + 1) % 9;
		} else {
			frame = 0;
			dir = Direction.DOWN;
		}
		int newposY = (int) (posY + spead);

		// kollar om den är sollid

		if (gc.checkPositionIsOk(posX+32, newposY+64)) {
			posY = newposY;
		} else {
			System.out.println("punkten är" + (int) posX / 64 + ", "
					+ (int) newposY / 64 + " och är INTE gå bar");
		}
	}

	public void goRigth() {
		if (dir == Direction.RIGTH) {
			frame = (frame + 1) % 9;
		} else {
			frame = 0;
			dir = Direction.RIGTH;
		}
		int newposX = (int) (posX + spead);

		// kollar om den är sollid
		if (gc.checkPositionIsOk(newposX+40, posY+32)) {
			System.out.println("punkten är" + newposX / 64 + ", " + posY / 64
					+ " och är gå bar");
			posX = newposX;
		} else {
			System.out.println("punkten är" + (int) newposX / 64 + ", "
					+ (int) posY / 64 + " och är INTE gå bar");
		}
	}

	public void goLeft() {
		if (dir == Direction.LEFT) {
			frame = (frame + 1) % 9;
		} else {
			frame = 0;
			dir = Direction.LEFT;
		}
		int newposX = (int) (posX - spead);

		// kollar om den är sollid
		if (gc.checkPositionIsOk(newposX+20, posY+32)) {
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

}
