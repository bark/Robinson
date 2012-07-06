package domain.map;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import domain.Event;
import domain.Tile;
import domain.Event.EVENTACTION;

public class Map {
	MapPart[][] worldMap = new MapPart[100][100];
	int layers = 5;
	int mapPiceSize = 100;
	ArrayList<Event> eventList=new ArrayList<Event>();
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
	private void generateWorldMap(int x, int y) {
		Long seed=(long) 234.124;
		
		MapPart mapPart = new MapPart(x, y, mapPiceSize, mapPiceSize,seed);
		new Thread(mapPart).start();
		eventList.add(new Event(0,EVENTACTION.GENERATE,x,y));
		worldMap[x][y]=mapPart;
		
	}



	public int getLayers() {
		return layers;

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
		int centerpiceX=50;
		int centerpiceY=50;
		if (worldMap[centerpiceX][centerpiceY] == null) {
			System.out.println("generating new world map");
			generateWorldMap(centerpiceX, centerpiceY);
		}
		return worldMap[centerpiceX][centerpiceY].getPoint(x-piceX*100, y-piceY*100);
	}
}
