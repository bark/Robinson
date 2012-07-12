package domain;

import java.util.ArrayList;

import controller.PlayerInterface;

import domain.map.Map;

import moveble.Moveable;

public class GameModel {
	static ArrayList<Moveable> animals= new ArrayList<Moveable>();
	static ArrayList<PlayerInterface> players= new ArrayList<PlayerInterface>(); //ska användas
	static Map worldMap= new Map();
	static ArrayList<Moveable> animalsRemoveQueue=new ArrayList<Moveable>();
	public static void addPlayers(PlayerInterface player){
		players.add(player);
	}
	public static ArrayList<PlayerInterface> getPlayers(){
		return players;
	}
	
	public static Player getLocalPlayer(){
		return players.get(0).getPlayer();
	}
	
	public static void addAnimals(Moveable animal){
		animals.add(animal);
	}
	public static void setInAnimalsRemoveQueue(Moveable animal){
		animalsRemoveQueue.add(animal);
	}
	public static void removeAnimals(){
		animals.removeAll(animalsRemoveQueue);
	}
	
	public static ArrayList<Moveable> getAnimals(){
		return animals;
	}
	
	
	public static Map GetWorldMap(){
		return worldMap;
	}
	
}
