package domain;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Map {
	HashMap<Point, MapPart> worldMap = new HashMap<Point, MapPart>();
	int layers=5;
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

	private MapPart generateWorldMap(int x,int y) {
		Point p= new Point(x,y);

		MapPart mapPart = new MapPart(x, y, 100, 100);
		worldMap.put(p, mapPart);
		return mapPart;

	}
	public MapPart getPart(int x,int y){
		Point p= new Point(x,y);
		MapPart part=worldMap.get(p);
		if(part==null){
			System.out.println("denna biten fins inte genererar ny bit");
			part=generateWorldMap(x,y);
		}
		return part;
	}
	public int getLayers() {
		return layers;
		
	}
	
	public int getHigth() {
		return worldMap.get(new Point(0,0)).getHigth();
		
	}
	public int getWith() {
		return worldMap.get(new Point(0,0)).getWith();
		
	}

	public Tile getPoint(int x, int y, int i) {

		return worldMap.get(new Point(0,0)).getPoint(x,y,i);
	}
}
