package domain;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Map {
	HashMap<HashMap<String,Integer>, MapPart> worldMap=new HashMap<HashMap<String,Integer>, MapPart>();
	boolean LoadMap(String mapUri){
		File mapFile = new File(mapUri);  
		Scanner mapScanner;
		try {
			mapScanner = new Scanner(mapFile);
		} catch (FileNotFoundException e) {
			System.out.println("kunde inte hitta filen");
			e.printStackTrace();
			return false;
		}
		String header=mapScanner.nextLine();
		String[] mapheader=header.split(",");
		int nrOfFiles =Integer.parseInt( mapheader[0]);
		int nrOfLines=0;
		while(mapScanner.hasNext()){
			nrOfLines++;
			String mapPartUri=mapScanner.nextLine();
			 MapPart mapPart = new MapPart(mapPartUri);
			 worldMap.put(mapPart.getPoition(),mapPart);
		}
		
		return true;
	}
	public boolean SaveMap(String name){
		File map =new File("./test");
		map.mkdir();
		File headSave =new File("./test/test2");
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
	public boolean generateWorldMap(){
		new MapPart(0,0,0);
		
	}
	
	
	
	
	
}
