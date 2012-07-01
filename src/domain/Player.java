package domain;

import java.awt.Desktop.Action;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import controller.GameController;
import controller.GameController.ACTION;
import domain.Tile.DIRECTION;

import Items.Item;

public class Player {

	int posX = 0;
	int posY = 0;

	DIRECTION facing = DIRECTION.SOUTH;
	float lowerTirednessStepp = (float) 0.8;
	float lowerHungerStepp = (float) 0.1;
	// stats
	float spead = 6;

	int maxhp = 100;

	// inventory
	float hp = maxhp;
	float hunger = 40;
	float thirst = 100;
	float tempratur = 47;
	float tiredness = 80;

	ArrayList<Item> inventory = new ArrayList<Item>();
	ACTION currentAction = null;
	Image pic = null;
	Image picDie = null;
	Image picSlash = null;
	int frame = 0;
	GameController gc;

	public Player(int x, int y, GameController gc) {
		posX = x;
		posY = y;
		this.gc = gc;
		String uri = "./res/pic/soldier_altcolor.png";
		pic = Toolkit.getDefaultToolkit().getImage(uri);

		uri = "./res/pic/male_hurt.png";
		picDie = Toolkit.getDefaultToolkit().getImage(uri);

		uri = "./res/pic/male_slash.png";
		picSlash = Toolkit.getDefaultToolkit().getImage(uri);

	}

	public void drawItSelf(Graphics g, ImageObserver io, int x, int y,
			float zoom) {
		int picX = 0;
		int picY = 0;
		Image paintImg = pic;
		if (currentAction == ACTION.GOUP || currentAction == ACTION.RUNUP) {

			picY = 0;
		} else if (currentAction == ACTION.GOLEFT
				|| currentAction == ACTION.RUNLEFT) {

			picY = 64;
		} else if (currentAction == ACTION.GODOWN
				|| currentAction == ACTION.RUNDOWN) {
			picY = 128;
		} else if (currentAction == ACTION.GORIGTH
				|| currentAction == ACTION.RUNRIGTH) {
			picY = 128 + 64;
		} else if (currentAction == ACTION.DIE
				|| currentAction == ACTION.PICKUP) {
			picY = 0;
			paintImg = picDie;
		} else if (currentAction == ACTION.SLASH) {
			switch (facing) {
			case NORTH:
				picY = 0;
				break;
			case SOUTH:
				picY = 128;
				break;
			case WEST:
				picY = 64;
				break;
			case EAST:
				picY =192;
			break;
			
			}
			
			paintImg = picSlash;
		}else if (currentAction == null){
			
		}
			switch (facing) {
			case NORTH:
				picY = 0;
				break;
			case SOUTH:
				picY = 128;
				break;
			case WEST:
				picY = 64;
				break;
			case EAST:
				picY =192;
			break;
		}
		picX = 64 * frame;
		g.drawImage(paintImg, x, y, x + (int) (64 * zoom), y
				+ (int) (64 * zoom), picX, picY, picX + 64, picY + 64, io);
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
		System.out.println("1 currentaction: " + currentAction + " action: "
				+ action);
		if (currentAction != ACTION.DIE && currentAction != ACTION.PICKUP
				&& currentAction != ACTION.SLASH) {// lockeble actions
			System.out.println("2 action:" + action);
			if (action != null) {
				System.out.println("tiredness:" + tiredness);
				if (tiredness < 0) {
					System.out.println("tiredness =0");
					if (action == ACTION.RUNLEFT) {
						action = ACTION.GOLEFT;
					} else if (action == ACTION.RUNRIGTH) {
						action = ACTION.GORIGTH;
					} else if (action == ACTION.RUNUP) {
						action = ACTION.GOUP;
					} else if (action == ACTION.RUNDOWN) {
						action = ACTION.GODOWN;
					}
				}
				move(action);
				if (action == ACTION.RUNLEFT || action == ACTION.RUNDOWN
						|| action == ACTION.RUNUP || action == ACTION.RUNRIGTH) {
					tiredness -= lowerTirednessStepp;
				}
			} else {
				if (tiredness < 100)
					tiredness += lowerTirednessStepp / 2;
			}
			hunger -= (lowerHungerStepp * (tiredness / 100));
			if (action != null)
				currentAction = action;

			if (hunger == 0) {
				System.out.println("you are dead!");
				currentAction = ACTION.DIE;
				action=ACTION.DIE;
				// do death animation
			}
		} else {
			System.out.println("currentaction: " + currentAction);
			if (currentAction == ACTION.PICKUP) {
				System.out.println("action pickup");
				frame = (frame + 1) % 4;
				if (frame == 0) {
					currentAction = null;
				}
			}
			if (currentAction == ACTION.DIE) {
				frame = (frame + 1) % 6;
			}
			if (currentAction == ACTION.SLASH) {
				frame = (frame + 1) % 5;
				if (frame == 0) {
					currentAction = null;
				}
			}

		}

	}

	private void move(ACTION action2) {
		int newposX = posX;
		int newposY = posY;
		if (currentAction == action2) {
			frame = (frame + 1) % 9;
		} else {
			frame = 0;
		}
		switch (action2) {
		case GODOWN:
			newposY = (int) (posY + spead);
			facing = DIRECTION.SOUTH;
			break;
		case GOUP:
			facing = DIRECTION.NORTH;
			newposY = (int) (posY - spead);
			break;
		case GOLEFT:
			newposX = (int) (posX - spead);
			facing = DIRECTION.WEST;
			break;
		case GORIGTH:
			newposX = (int) (posX + spead);
			facing = DIRECTION.EAST;
			break;
		case RUNUP:
			facing = DIRECTION.NORTH;
			newposY = (int) (posY - (spead * 3));
			break;
		case RUNDOWN:
			newposY = (int) (posY + (spead * 3));
			facing = DIRECTION.SOUTH;
			break;
		case RUNRIGTH:
			newposX = (int) (posX + (spead * 3));
			facing = DIRECTION.EAST;
			break;
		case RUNLEFT:
			newposX = (int) (posX - (spead * 3));
			facing = DIRECTION.WEST;
			break;
		default:
			break;
		}
		// kollar om den är sollid
		if (gc.checkPositionIsOk(newposX + 16, newposY + 46, newposX + 44,
				newposY + 64 - 5)) {
			posX = newposX;
			posY = newposY;
		}
	}
}
