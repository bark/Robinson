package domain;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import javax.print.attribute.Size2DSyntax;

import domain.backGroundTile.Grass;

public class MapPart {
	HashMap<String,Tile> MapTranslation = new HashMap<String,Tile>(); 
	Tile[][][] map;

	private int worldX;
	private int worldY;
	public MapPart(int x,int y, int higth, int with){
		worldX=x;
		worldY=y;
		generate(higth, with);
	}
	
	public MapPart(String MapPartUrl){
		loadMap(MapPartUrl);
		
		
	}
	private boolean loadMap(String mapUri){
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
		int x =Integer.parseInt( mapheader[0]);
		int y =Integer.parseInt( mapheader[1]);
		//Where in the world this part is.
		worldX =Integer.parseInt( mapheader[2]);
		worldY =Integer.parseInt( mapheader[3]);
		
		
		map=new Tile[x][y][3];
		for(int layer=0;layer<3;layer++){
			int scanedY=0;	
			while(mapScanner.hasNext()){
				scanedY++;
				String MapLine=mapScanner.nextLine();
				if(MapLine.startsWith("====")){
					break;
				}
				String[] tileRow=MapLine.split(",");
				
				if(tileRow.length!=x){
					System.err.println("line "+scanedY+"have incorect nr of tiles, have "+ tileRow.length +"shood have "+x);
					return false;
				}
				int scanedX=0;
				for(String tileChar:tileRow){
					scanedX++;
					Tile tile=MapTranslation.get(tileChar);
					if(tile==null){
						System.err.println("on layer "+layer+ " on row "+scanedY +" position: "+scanedX +" is a error");
						return false;
					}
					map[scanedX][scanedY][layer]=tile;
				}
			}
			if(scanedY!=y){
				
				System.err.println("get "+scanedY + "lines shood only be "+y);
				return false;
			}
		}	
		return true;
	}
	
	
	
	
	public void saveMap(String uri){
		File headSave =new File(uri);
		try {
			headSave.createNewFile();
			FileWriter fw = new FileWriter(headSave.getName());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(map.length+","+map[0].length+"\n");
			for(int lager=0;lager<3;lager++){
				for(int x=0;x<map.length;x++ ){
					String aRow="";
					for(int y=0;y<map[0].length;y++ ){
						if(map[x][y][lager]!=null){
							aRow+=map[x][y][lager]+",";
						}else{
							aRow+="g,";//g är en standard ruta om inget annat har sagts
						}
						
					}
					bw.write(aRow+"\n");
				}
				bw.write("============");
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public Point getPoition(){
		Point p=new Point(worldX,worldY);
		return p;
	}
	public boolean generate(int higth, int with){
		//TODO denna måste bli smart 
		Grass grass=new Grass();
		map= new Tile[higth][with][3];
		//fyll den med gräs
		for(int i=0;i<higth;i++){
			for(int j=0;j<with;j++){
				map[i][j][0]=grass;
			}
		}	
		return true;
	}
	public int getHigth(){
		return map.length;
	}
	public int getwith(){
		return map[0].length;
	}
	
	public Tile getPoint(int x, int y,int layer){
		return map[x][y][layer];
	}
}
