package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import controller.GameController.ACTION;
import domain.Player;

public class LocalPlayer implements PlayerInterface{
	ACTION nextAction=null;
	Player player;
	
	public LocalPlayer(Player player){
		this.player=player;
	}
	
	 

	public boolean keyPressed(KeyEvent e, boolean shiftPressed){
		// System.out.println(e.getKeyCode());
				if (shiftPressed) {
					if (e.getKeyCode() == 87 || e.getKeyCode() == 38) {
						nextAction = ACTION.RUNUP;
					} else if (e.getKeyCode() == 83 || e.getKeyCode() == 40) {
						nextAction = ACTION.RUNDOWN;
					} else if (e.getKeyCode() == 65 || e.getKeyCode() == 37) {
						nextAction = ACTION.RUNLEFT;
					} else if (e.getKeyCode() == 68 || e.getKeyCode() == 39) {
						nextAction = ACTION.RUNRIGTH;
					}
				} else {
					if (e.getKeyChar() == 'w' || e.getKeyCode() == 38) {

						nextAction = ACTION.GOUP;
					} else if (e.getKeyChar() == 's' || e.getKeyCode() == 40) {
						nextAction = ACTION.GODOWN;
					} else if (e.getKeyChar() == 'a' || e.getKeyCode() == 37) {
						nextAction = ACTION.GOLEFT;
					} else if (e.getKeyChar() == 'd' || e.getKeyCode() == 39) {
						nextAction = ACTION.GORIGTH;
					}else if ( e.getKeyCode() == 32) {
						nextAction = ACTION.SLASH;
					}else if ( e.getKeyCode() == 69) {
						nextAction = ACTION.PICKUP;
					}else if(e.getKeyCode() == 81) {
						nextAction = ACTION.USE;
					}
				}
				
		return false;
	}
	@Override
	public void tick() {
		player.action(nextAction);
		nextAction=null;
	}



	@Override
	public Player getPlayer() {
		return player;
	}
}
