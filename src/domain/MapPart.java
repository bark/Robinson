package domain;

import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import javax.print.attribute.Size2DSyntax;

import domain.Tile.DIRECTION;
import domain.backGroundTile.Dirt;
import domain.backGroundTile.Grass;
import domain.forgroundTile.Stone;
import domain.forgroundTile.TreeBottom;
import domain.forgroundTile.TreeTop;

public class MapPart {
	HashMap<String, Tile> MapTranslation = new HashMap<String, Tile>();
	Tile[][][] map;
	MessageDigest m;

	private int worldX;
	private int worldY;
	Long seed;

	public MapPart(int x, int y, Long i, int higth, int with) {
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.seed = i;
		worldX = x;
		worldY = y;
		generate(higth, with);
	}

	public MapPart(String MapPartUrl) {

	}

	/*
	 * private boolean loadMap(String mapUri){ File mapFile = new File(mapUri);
	 * Scanner mapScanner;
	 * 
	 * try { mapScanner = new Scanner(mapFile); } catch (FileNotFoundException
	 * e) { System.out.println("kunde inte hitta filen"); e.printStackTrace();
	 * return false; } String header=mapScanner.nextLine(); String[]
	 * mapheader=header.split(","); int x =Integer.parseInt( mapheader[0]); int
	 * y =Integer.parseInt( mapheader[1]); //Where in the world this part is.
	 * worldX =Integer.parseInt( mapheader[2]); worldY =Integer.parseInt(
	 * mapheader[3]);
	 * 
	 * 
	 * map=new Tile[x][y][5]; for(int layer=0;layer<3;layer++){ int scanedY=0;
	 * while(mapScanner.hasNext()){ scanedY++; String
	 * MapLine=mapScanner.nextLine(); if(MapLine.startsWith("====")){ break; }
	 * String[] tileRow=MapLine.split(",");
	 * 
	 * if(tileRow.length!=x){
	 * System.err.println("line "+scanedY+"have incorect nr of tiles, have "+
	 * tileRow.length +"shood have "+x); return false; } int scanedX=0;
	 * for(String tileChar:tileRow){ scanedX++; Tile
	 * tile=MapTranslation.get(tileChar); if(tile==null){
	 * System.err.println("on layer "+layer+ " on row "+scanedY
	 * +" position: "+scanedX +" is a error"); return false; }
	 * map[scanedX][scanedY][layer]=tile; } } if(scanedY!=y){
	 * 
	 * System.err.println("get "+scanedY + "lines shood only be "+y); return
	 * false; } } return true; }
	 * 
	 * 
	 * 
	 * 
	 * public void saveMap(String uri){ File headSave =new File(uri); try {
	 * headSave.createNewFile(); FileWriter fw = new
	 * FileWriter(headSave.getName()); BufferedWriter bw = new
	 * BufferedWriter(fw); bw.write(map.length+","+map[0].length+"\n"); for(int
	 * lager=0;lager<3;lager++){ for(int x=0;x<map.length;x++ ){ String aRow="";
	 * for(int y=0;y<map[0].length;y++ ){ if(map[x][y][lager]!=null){
	 * aRow+=map[x][y][lager]+","; }else{ aRow+="g,";//g är en standard ruta om
	 * inget annat har sagts }
	 * 
	 * } bw.write(aRow+"\n"); } bw.write("============"); } bw.close(); } catch
	 * (IOException e) { // TODO Auto-generated catch block e.printStackTrace();
	 * } }
	 */

	public Point getPoition() {
		Point p = new Point(worldX, worldY);
		return p;
	}

	public boolean generate(int higth, int with) {
		Random notRandomRandom = new Random(seed);
		map = new Tile[higth][with][5];
		// fyll den med gräs
		for (int i = 0; i < higth; i++) {
			for (int j = 0; j < with; j++) {
				if (notRandomRandom.nextFloat() * 100 < 5) {
					map[i][j][2] = new Stone();
				}
				map[i][j][0] = new Dirt();
				if (notRandomRandom.nextFloat() * 100 < 20 + nrOf(new Dirt(), i,
						j, 0) * 18)
					map[i][j][1] = new Grass();
			}
		}
		for (int i = 0; i < higth; i++) {
			for (int j = 0; j < with; j++) {
				if (notRandomRandom.nextFloat() * 100 > 99) {
					createATree(i, j);
				}
			}
		}
		fixGrass();
		fixGrass();
		fixGrass();
		fixGrass();
		fixGrass();
		fixGrass();//TODO FIX this
		fixGrass();
		fixGrass();
		fixGrass();
		fixGrass();//TODO FIX this
		return true;
	}

	private void fixGrass() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j][1] != null) {
					ArrayList<DIRECTION> directionsArr = generateArrayOfDirections(
							new Grass(), i, j, 1);
					if (!directionsArr.contains(DIRECTION.NORTH)
							&& !directionsArr.contains(DIRECTION.SOUTH)) {
						map[i][j][1] = null;
					} else if (!directionsArr.contains(DIRECTION.WEST)
							&& !directionsArr.contains(DIRECTION.EAST)) {
						map[i][j][1] = null;
					} else if (!directionsArr.contains(DIRECTION.NORTHEAST)
							&& !directionsArr.contains(DIRECTION.SOUTHWEST)) {
						map[i][j][1] = null;
					} else if (!directionsArr.contains(DIRECTION.NORTHWEST)
							&& !directionsArr.contains(DIRECTION.SOUTHEAST)) {
						map[i][j][1] = null;
					}// 
						else if (!directionsArr.contains(DIRECTION.NORTH)
								&& !directionsArr.contains(DIRECTION.WEST)&& !directionsArr.contains(DIRECTION.SOUTHEAST)) {
							map[i][j][1] = null;
						}else if (!directionsArr.contains(DIRECTION.NORTH)
								&& !directionsArr.contains(DIRECTION.EAST)&& !directionsArr.contains(DIRECTION.SOUTHWEST)) {
							map[i][j][1] = null;
						}else if (!directionsArr.contains(DIRECTION.SOUTH)
								&& !directionsArr.contains(DIRECTION.EAST)&& !directionsArr.contains(DIRECTION.NORTHWEST)) {
							map[i][j][1] = null;
						}else if (!directionsArr.contains(DIRECTION.SOUTH)
								&& !directionsArr.contains(DIRECTION.WEST)&& !directionsArr.contains(DIRECTION.NORTHEAST)) {
							map[i][j][1] = null;
						}else 
					
					{
						// System.out.println(directionsArr);

						map[i][j][1].SetPart(directionsArr);
					}
				}
			}
		}
	}

	private ArrayList<DIRECTION> generateArrayOfDirections(Tile tile, int x,
			int y, int layer) {
		ArrayList<DIRECTION> directions = new ArrayList<DIRECTION>();
		// layer1
		if (isTileOfSort(tile, x - 1, y - 1, layer)) {
			directions.add(DIRECTION.NORTHEAST);
		}
		if (isTileOfSort(tile, x , y-1, layer)) {
			directions.add(DIRECTION.NORTH);
		}
		if (isTileOfSort(tile, x +1, y - 1, layer)) {
			directions.add(DIRECTION.NORTHWEST);
		}
		// layer2
		if (isTileOfSort(tile, x-1, y , layer)) {
			directions.add(DIRECTION.EAST);
		}
		if (isTileOfSort(tile, x+1, y, layer)) {
			directions.add(DIRECTION.WEST);
		}
		// layer3
		if (isTileOfSort(tile, x - 1, y + 1, layer)) {
			directions.add(DIRECTION.SOUTHEAST);
		}
		if (isTileOfSort(tile, x , y+1, layer)) {
			directions.add(DIRECTION.SOUTH);
		}
		if (isTileOfSort(tile, x + 1, y + 1, layer)) {
			directions.add(DIRECTION.SOUTHWEST);
		}
		return directions;
	}

	private int nrOf(Tile tile, int x, int y, int layer) {
		return generateArrayOfDirections(tile, x, y, layer).size();
	}

	private boolean isTileOfSort(Tile tile, int x, int y, int layer) {
		if (x > 0 && x < map.length)// kollar giltigt x värde
			if (y > 0 && y < map[x].length)// kollar gilltigt y värde
				if (map[x][y][layer] != null)// kollar så den innehåller något
					if (tile.getClass() == map[x][y][layer].getClass())
						return true;
		return false;
	}

	/*
	 * int nrOf(Object type,int x,int y,int layer){ int nr=0;
	 * 
	 * @SuppressWarnings("unchecked") final type other = (type) obj;
	 * if(map[x-1][y][layer] instanceof type){
	 * 
	 * } return nr; }
	 */
	void createATree(int x, int y) {
		if (x < 90 && y < 90) {

			// stammen
			map[x][y + 2][2] = new TreeBottom(0, 0);
			map[x][y + 1 + 2][2] = new TreeBottom(0, 1);
			map[x][y + 2 + 2][2] = new TreeBottom(0, 2);

			map[x + 1][y + 2][2] = new TreeBottom(0, 3);
			map[x + 1][y + 1 + 2][2] = new TreeBottom(0, 4);
			map[x + 1][y + 2 + 2][2] = new TreeBottom(0, 5);

			map[x + 2][y + 2][2] = new TreeBottom(0, 6);
			map[x + 2][y + 1 + 2][2] = new TreeBottom(0, 7);
			map[x + 2][y + 2 + 2][2] = new TreeBottom(0, 8);

			// träd toppen
			map[x][y][3] = new TreeTop(0, 0);
			map[x][y + 1][3] = new TreeTop(0, 1);
			map[x][y + 2][3] = new TreeTop(0, 2);

			map[x + 1][y][3] = new TreeTop(0, 3);
			map[x + 1][y + 1][3] = new TreeTop(0, 4);
			map[x + 1][y + 2][3] = new TreeTop(0, 5);

			map[x + 2][y][3] = new TreeTop(0, 6);
			map[x + 2][y + 1][3] = new TreeTop(0, 7);
			map[x + 2][y + 2][3] = new TreeTop(0, 8);

		}
	}

	public int getHigth() {
		return map.length;
	}

	public int getWith() {
		return map[0].length;
	}

	public Tile getPoint(int x, int y, int layer) {
		return map[x][y][layer];
	}
}
