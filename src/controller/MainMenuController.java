package controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import gui.MainMenu;

import javax.swing.JButton;

public class MainMenuController implements ActionListener {
	static boolean debug=false;
	public MainMenuController() {
		System.out.println("mainMenuController");
		MainMenu mainMenu=new MainMenu();
		mainMenu.SetButtonListner(this);
	}

	

	
	public static void main(String[] args){
		System.out.println("starting epic game!!!");
		if(args.length>0){
				if( args[0].equals("d")) {
					//if debug is on.
					debug=true;
				}
		}
		System.out.println("start mainMenuController");
		new MainMenuController();

		System.out.println("efter mainMenuController");
	}

	

	@Override
	public void actionPerformed(ActionEvent ae) {
		System.out.println("triggering");
		System.out.println(((JButton)ae.getSource()).getText());
		if(((JButton)ae.getSource()).getText().equals("exit")){
			System.exit(0);
		}
		if(((JButton)ae.getSource()).getText().equals("new game")){
			System.out.println("startar ett nytt spel");
			//starta ett nytt spel :P
		}
		if(((JButton)ae.getSource()).getText().equals("options")){
			System.out.println("går in i option menu");
		}
		
	}
}
