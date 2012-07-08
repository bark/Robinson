package domain;

import java.util.ArrayList;

import domain.map.Map;

import moveble.Moveable;

public class GameModel {
	static ArrayList<Moveable> animals= new ArrayList<Moveable>();
	static ArrayList<Player> players= new ArrayList<Player>(); //ska användas
	static Map worldMap= new Map();
	public static void addPlayers(Player player){
		players.add(player);
	}
	public static ArrayList<Player> getPlayers(){
		return players;
	}
	public static void addAnimals(Moveable animal){
		animals.add(animal);
	}
	public static ArrayList<Moveable> getAnimals(){
		return animals;
	}
	public static Map GetWorldMap(){
		return worldMap;
	}
	
}
