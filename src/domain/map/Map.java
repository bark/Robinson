package domain.map;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import domain.Event;
import domain.Tile;
import domain.Event.EVENTACTION;

public class Map {
	MapPart[][] worldMap = new MapPart[100][100];
	int mapPiceSize = 100;
	ArrayList<Event> eventList=new ArrayList<Event>();
	long sead=1337;
	Random randomNotRandom;
	public Map(){
		randomNotRandom = new Random(sead);
	}
/*	
	boolean LoadMap(String mapUri) {
		File mapFile = new File(mapUri);
		Scanner mapScanner;
		try {
			mapScanner = new Scanner(mapFile);
		} catch (FileNotFoundException e) {
			System.out.println("kunde inte hitta filen");
			e.printStackTrace();
			return false;
		}
		String header = mapScanner.nextLine();
		String[] mapheader = header.split(",");
		int nrOfFiles = Integer.parseInt(mapheader[0]);
		int nrOfLines = 0;
		while (mapScanner.hasNext()) {
			nrOfLines++;
			String mapPartUri = mapScanner.nextLine();
			MapPart mapPart = new MapPart(mapPartUri);
			worldMap.put(mapPart.getPoition(), mapPart);
		}

		return true;
	}

	public boolean SaveMap(String name) {
		File map = new File("./test");
		map.mkdir();
		File headSave = new File("./test/test2");
		try {
			headSave.createNewFile();
			FileWriter fw = new FileWriter(headSave.getName());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(worldMap.size());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}
*/
	public boolean checkPositionIsOk(int topX, int topY, int botX, int botY) {
		//System.out.println("punkten är" + topX + ", " + topY+"tiles: "+topX/32+":"+topY/32);
				
		if (checkPointIsOk(topX, botY)) {
			if (checkPointIsOk(topX, topY)) {
				if (checkPointIsOk(botX, botY)) {
					if (checkPointIsOk(botX, topY)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	public boolean checkPointIsOk(int x, int y) {
		SortedTileList tileArr = getPointFromPx(x, y);
		if(tileArr!=null){
			for (Tile tile : tileArr) {
				if (tile.isSollid()) {
					return false;
				}
			}
		}else{
			return false;
		}
		return true;
	}

	
	private void generateWorldMap(int x, int y) {
		Long seed=(long) 13*x*1000+y*42;//13 and 42 is random chosen numbers
		System.out.println("seed x:"+x+"y:"+y+"seed:"+seed);
		/*float water = 20;// detta är hur många procent som borde bli 100 är hela saken är vatten
		float heat = 20;
		float flatness=0; //who flatt the world is going to be //used in rocks 
		float vegitation=40;//used in trees and plants 
		float friendliness=100; //this is a number how dangerus this place is.//not used
		*/

		float water = randomNotRandom.nextFloat()*100;// detta är hur många procent som borde bli 100 är hela saken är vatten
		float heat = randomNotRandom.nextFloat()*100;
		float flatness=randomNotRandom.nextFloat()*100; //who flatt the world is going to be //used in rocks 
		float vegitation=randomNotRandom.nextFloat()*100;//used in trees and plants 
		float friendliness=randomNotRandom.nextFloat()*100; //this is a number how dangerus this place is.//not used
		
		System.out.println("water: "+ water);
		System.out.println("heat: "+ heat);
		System.out.println("flatness:"+ flatness);
		System.out.println("vegitation:"+ vegitation);
		System.out.println("friendliness:"+ friendliness);
		
		MapPart mapPart = new MapPart(x, y, mapPiceSize, mapPiceSize,seed,water, heat, flatness,vegitation, friendliness, worldMap[x][y-1], worldMap[x][y+1], worldMap[x-1][y], worldMap[x+1][y]);
		new Thread(mapPart).start();
		eventList.add(new Event(0,EVENTACTION.GENERATE,x,y));
		worldMap[x][y]=mapPart;
		
	}

	public int getHigth() {
		return mapPiceSize;// worldMap.get(new Point(0,0)).getHigth();

	}

	public int getWith() {
		return mapPiceSize;// worldMap.get(new Point(0,0)).getWith();
		
	}
	
	
	public SortedTileList getPointFromPx(int x, int y) {
		return getPoint(x/32,y/32);
	}

	public SortedTileList getPoint(int x, int y) {
		int piceX = (int) (x / 100);
		int piceY = (int) (y / 100);
			if (worldMap[piceX][piceY] == null) {
			System.out.println("generating new world map");
			generateWorldMap(piceX, piceY);
		}
		return worldMap[piceX][piceY].getPoint(x-piceX*100, y-piceY*100);
	}
}
