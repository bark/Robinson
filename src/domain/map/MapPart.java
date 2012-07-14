package domain.map;

import items.food.Mushroom;
import items.food.MushroomBad;
import items.food.Potato;

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
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.print.attribute.Size2DSyntax;


import domain.Tile;
import domain.Tile.DIRECTION;
import domain.backGroundTile.Dirt;
import domain.backGroundTile.Grass;
import domain.backGroundTile.Water;
import domain.forgroundTile.Stone;
import domain.forgroundTile.Tree;
import domain.plants.CarrotPlant;
import domain.plants.PalsternackaPlant;
import domain.plants.PapricaPlant;
import domain.plants.PotatoPlant;
import domain.plants.SquashPlant;
import domain.plants.TomatPlant;

public class MapPart implements Runnable {
	private SortedTileList[][] map;
	MessageDigest m;

	private int worldX;
	private int worldY;
	Long seed;
	int higth, with;
	float water = 0;// detta är hur många procent som borde bli 100 är hela
					// saken är vatten
	float heat = 0;

	float flatness = 0; // who flatt the world is going to be //not used
	float vegitation = 0;
	float friendliness = 100; // this is a number how dangerus this place is.
	int nr = 0; // this is the number of mapparts created before.
	MapPart north, south, west, east;

	public MapPart(int x, int y, int higth, int with, long seed, float water,
			float heat, float flatness, float vegitation, float friendliness,
			MapPart north, MapPart south, MapPart west, MapPart east) {
		this.seed = seed;

		this.higth = higth;
		this.with = with;

		this.water = water;
		this.heat = heat;
		this.flatness = flatness;
		this.vegitation = vegitation;
		this.friendliness = friendliness;

		this.north = north;
		this.south = south;
		this.west = west;
		this.east = east;
		worldX = x;
		worldY = y;

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

	private void fixTileLayout(Tile tile) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].contains(tile)) {
					ArrayList<DIRECTION> directionsArr = generateArrayOfDirections(
							tile, i, j);
						map[i][j].getTileOfType(tile).SetPart(directionsArr);
				}
			}
		}
	}
	private void addTiles(Tile tile) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (!map[i][j].contains(tile)) {
					int nr = generateArrayOfCrossDirections(tile, i, j).size();
					if(nr>2){
						map[i][j].add(new Grass());//this shood not be hard coded.
					}
				}
			}
		}
	}
	
	private void removeTiles(Tile tile) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j].contains(tile)) {
					ArrayList<DIRECTION> directionsArr = generateArrayOfDirections(
							tile, i, j);
					if (!directionsArr.contains(DIRECTION.NORTH)
							&& !directionsArr.contains(DIRECTION.SOUTH)) {
						map[i][j].removeTileOfType(tile);
					} else if (!directionsArr.contains(DIRECTION.WEST)
							&& !directionsArr.contains(DIRECTION.EAST)) {
						map[i][j].removeTileOfType(tile);
					} else if (!directionsArr.contains(DIRECTION.NORTHEAST)
							&& !directionsArr.contains(DIRECTION.SOUTHWEST)) {
						map[i][j].removeTileOfType(tile);
					} else if (!directionsArr.contains(DIRECTION.NORTHWEST)
							&& !directionsArr.contains(DIRECTION.SOUTHEAST)) {
						map[i][j].removeTileOfType(tile);
					}//
					else if (!directionsArr.contains(DIRECTION.NORTH)
							&& !directionsArr.contains(DIRECTION.WEST)
							&& !directionsArr.contains(DIRECTION.SOUTHEAST)) {
						map[i][j].removeTileOfType(tile);
					} else if (!directionsArr.contains(DIRECTION.NORTH)
							&& !directionsArr.contains(DIRECTION.EAST)
							&& !directionsArr.contains(DIRECTION.SOUTHWEST)) {
						map[i][j].removeTileOfType(tile);
					} else if (!directionsArr.contains(DIRECTION.SOUTH)
							&& !directionsArr.contains(DIRECTION.EAST)
							&& !directionsArr.contains(DIRECTION.NORTHWEST)) {
						map[i][j].removeTileOfType(tile);
					} else if (!directionsArr.contains(DIRECTION.SOUTH)
							&& !directionsArr.contains(DIRECTION.WEST)
							&& !directionsArr.contains(DIRECTION.NORTHEAST)) {
						map[i][j].removeTileOfType(tile);
					} 
				}
			}
		}
	}
	
	private boolean createASee(int curentX, int curentY, int seeCenterX, int seeCenterY,int nr,
			Random notRandomRandom) {
		System.out.println("createASee");
		if (nr==0){
			System.out.println("return true");
			return true;
		}
		if (curentX > 0 && curentY > 0 && seeCenterX > 0 && seeCenterY > 0
				&& curentX < map.length-1 && curentY < map[0].length-1
				&& seeCenterX < map.length-1 && seeCenterY < map[0].length-1) {
			
			System.out.println("water on point:"+curentX+":"+curentY);
			map[curentX][curentY].add(new Water());
			map[curentX + 1][curentY].add(new Water());
			map[curentX][curentY + 1].add(new Water());
			map[curentX - 1][curentY].add(new Water());
			map[curentX][curentY - 1].add(new Water());
			boolean succsess = false;
			nr--;
			double nr1 = hypetunusan(curentX + 1, curentY, seeCenterX, seeCenterY)+5
					* ((notRandomRandom.nextDouble()*10 ) + 1);
			double nr2 = hypetunusan(curentX, curentY + 1, seeCenterX, seeCenterY)+5
					* ((notRandomRandom.nextDouble()*10) + 1);
			double nr3 = hypetunusan(curentX - 1, curentY, seeCenterX, seeCenterY)+5
					* ((notRandomRandom.nextDouble() *10) + 1);
			double nr4 = hypetunusan(curentX, curentY - 1, seeCenterX, seeCenterY)+5
					* ((notRandomRandom.nextDouble()*10) + 1);
			double worst = theWorstNumber(nr1, nr2, nr3, nr4);
			if (worst == nr1) {
				succsess = createASee(curentX + 1, curentY, seeCenterX, seeCenterY,nr,
						notRandomRandom);
			} else if (worst == nr2) {
				succsess = createASee(curentX, curentY + 1, seeCenterX, seeCenterY,nr,
						notRandomRandom);
			} else if (worst == nr3) {
				succsess = createASee(curentX - 1, curentY, seeCenterX, seeCenterY,nr,
						notRandomRandom);
			} else if (worst == nr4) {
				succsess = createASee(curentX, curentY - 1, seeCenterX, seeCenterY,nr,
						notRandomRandom);
			}

			if (!succsess) {
				createASee(curentX, curentY, seeCenterX, seeCenterY,nr, notRandomRandom);
			}
			return true;
		} else {
			return false;
		}
	}
	
	
	

	private boolean createARiver(int startX, int startY, int endX, int endY,
			Random notRandomRandom) {
		if (startX == endX && startY == endY)
			return true;
		if (startX > 0 && startY > 0 && endX > 0 && endY > 0
				&& startX < map.length && startY < map[0].length
				&& endX < map.length && endY < map[0].length) {
			// System.out.println("kommer förbi mördar ifsatsen");
			map[startX][startY].add(new Water());
			map[startX + 1][startY].add(new Water());
			map[startX][startY + 1].add(new Water());
			map[startX - 1][startY].add(new Water());
			map[startX][startY - 1].add(new Water());
			boolean succsess = false;
			double nr1 = hypetunusan(startX + 1, startY, endX, endY)
					* ((notRandomRandom.nextDouble() / 10) + 1);
			double nr2 = hypetunusan(startX, startY + 1, endX, endY)
					* ((notRandomRandom.nextDouble() / 10) + 1);
			double nr3 = hypetunusan(startX - 1, startY, endX, endY)
					* ((notRandomRandom.nextDouble() / 10) + 1);
			double nr4 = hypetunusan(startX, startY - 1, endX, endY)
					* ((notRandomRandom.nextDouble() / 10) + 1);
			double worst = theWorstNumber(nr1, nr2, nr3, nr4);
			if (worst == nr1) {
				succsess = createARiver(startX + 1, startY, endX, endY,
						notRandomRandom);
			} else if (worst == nr2) {
				succsess = createARiver(startX, startY + 1, endX, endY,
						notRandomRandom);
			} else if (worst == nr3) {
				succsess = createARiver(startX - 1, startY, endX, endY,
						notRandomRandom);
			} else if (worst == nr4) {
				succsess = createARiver(startX, startY - 1, endX, endY,
						notRandomRandom);
			}

			if (!succsess) {
				createARiver(startX, startY, endX, endY, notRandomRandom);
			}
			return true;
		} else {
			return false;
		}
	}

	double theWorstNumber(double a, double b, double c, double d) {
		double worst = a;
		if (worst > b) {
			worst = b;
		}
		if (worst > c) {
			worst = c;
		}
		if (worst > d) {
			worst = d;
		}
		return worst;
	}

	private double hypetunusan(int startX, int startY, int endX, int endY) {
		int lengthX = (startX - endX);
		int lengthY = (startY - endY);
		return Math.sqrt((lengthX * lengthX) + (lengthY * lengthY));

	}

	private ArrayList<DIRECTION> generateArrayOfCrossDirections(Tile tile,
			int x, int y) {
		ArrayList<DIRECTION> directions = new ArrayList<DIRECTION>();
		if (isTileOfSort(tile, x, y - 1)) {
			directions.add(DIRECTION.NORTH);
		}
		if (isTileOfSort(tile, x - 1, y)) {
			directions.add(DIRECTION.EAST);
		}
		if (isTileOfSort(tile, x + 1, y)) {
			directions.add(DIRECTION.WEST);
		}
		if (isTileOfSort(tile, x, y + 1)) {
			directions.add(DIRECTION.SOUTH);
		}
		return directions;
	}

	private ArrayList<DIRECTION> generateArrayOfDiganalDirections(Tile tile,
			int x, int y) {
		ArrayList<DIRECTION> directions = new ArrayList<DIRECTION>();
		// layer1
		if (isTileOfSort(tile, x - 1, y - 1)) {
			directions.add(DIRECTION.NORTHEAST);
		}

		if (isTileOfSort(tile, x + 1, y - 1)) {
			directions.add(DIRECTION.NORTHWEST);
		}
		// layer2

		// layer3
		if (isTileOfSort(tile, x - 1, y + 1)) {
			directions.add(DIRECTION.SOUTHEAST);
		}

		if (isTileOfSort(tile, x + 1, y + 1)) {
			directions.add(DIRECTION.SOUTHWEST);
		}
		return directions;
	}

	private ArrayList<DIRECTION> generateArrayOfDirections(Tile tile, int x,
			int y) {
		ArrayList<DIRECTION> directions = generateArrayOfDiganalDirections(
				tile, x, y);
		directions.addAll(generateArrayOfCrossDirections(tile, x, y));
		return directions;
	}

	private int nrOf(Tile tile, int x, int y) {
		return generateArrayOfDirections(tile, x, y).size();
	}

	private boolean isTileOfSort(Tile tile, int x, int y) {
		if (x > 0 && x < map.length)// kollar giltigt x värde
			if (y > 0 && y < map[x].length)// kollar gilltigt y värde
				if (map[x][y] != null)
					if (map[x][y].getTileOfType(tile) != null)
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
	void createATree(int x, int y, int type) {
		if (nrOf(new Water(), x, y) == 0) {

			double prio = (y * 10) - (x);
			if (x < 90 && y < 90) {

				// stammen
				map[x][y + 2].add(new Tree(type, 0, prio, false));
				map[x][y + 1 + 2].add(new Tree(type, 1, prio, false));
				map[x][y + 2 + 2].add(new Tree(type, 2, prio, false));

				map[x + 1][y + 2].add(new Tree(type, 3, prio, false));
				map[x + 1][y + 1 + 2].add(new Tree(type, 4, prio, false));
				map[x + 1][y + 2 + 2].add(new Tree(type, 5, prio, false));

				map[x + 2][y + 2].add(new Tree(type, 6, prio, false));
				map[x + 2][y + 1 + 2].add(new Tree(type, 7, prio, false));
				map[x + 2][y + 2 + 2].add(new Tree(type, 8, prio, false));

				// träd toppen
				map[x][y].add(new Tree(type, 0, prio, true));
				map[x][y + 1].add(new Tree(type, 1, prio, true));
				map[x][y + 2].add(new Tree(type, 2, prio, true));

				map[x + 1][y].add(new Tree(type, 3, prio, true));
				map[x + 1][y + 1].add(new Tree(type, 4, prio, true));
				map[x + 1][y + 2].add(new Tree(type, 5, prio, true));

				map[x + 2][y].add(new Tree(type, 6, prio, true));
				map[x + 2][y + 1].add(new Tree(type, 7, prio, true));
				map[x + 2][y + 2].add(new Tree(type, 8, prio, true));
			}
		}
	}

	public int getHigth() {
		return map.length;
	}

	public int getWith() {
		return map[0].length;
	}

	public SortedTileList getPoint(int x, int y) {
		if (map != null) {
			return map[x][y];
		}
		return null;
	}

	@Override
	public void run() {
		Random notRandomRandom = new Random(seed);
		System.out.println("seed: " + seed);
		map = new SortedTileList[higth][with];
		for (int i = 0; i < higth; i++) {
			for (int j = 0; j < with; j++) {
				map[i][j] = new SortedTileList(i, j);
				if (notRandomRandom.nextFloat() * 100 < flatness / 20) {
					map[i][j].add(new Stone());
				}
				map[i][j].add(new Dirt());
				if (notRandomRandom.nextFloat() * 100 < ((vegitation) / 2)
						+ nrOf(((Tile) new Grass()), i, j) * (vegitation / 10))
					map[i][j].add(new Grass());

			}

		}
		createASee(80 ,80 ,80,80,1000, notRandomRandom);

//		if(water>50){
//			createARiver((int)(notRandomRandom.nextFloat() * 100) ,(int)( notRandomRandom.nextFloat() * 100) ,(int)( notRandomRandom.nextFloat() * 100) ,(int)( notRandomRandom.nextFloat() * 100) , notRandomRandom);
//		}
		for (int i = 0; i < 5; i++) {addTiles(new Grass());}
		for (int i = 0; i < 10; i++) { removeTiles(new Grass()); }
		
		
		for (int i = 0; i < 10; i++) { removeTiles(new Water()); }
		fixTileLayout(new Grass()); 
		fixTileLayout(new Water()); 
		//fix so corect tile sort on corect place.
		/*
		 * for (int i = 0; i < 10; i++) { fixTile(new Water()); }
		 * 
		 * for (int i = 0; i < 10; i++) { fixTile(new Grass()); } //
		 * removeExtraTiles();
		 */
		
		
		for (int i = 0; i < higth; i++) {
			for (int j = 0; j < with; j++) {
				if (notRandomRandom.nextFloat() * 100 < vegitation / 60) {
					if (new Mushroom().canBeAdded(map[i][j])) {
						map[i][j].add(new PotatoPlant(i+worldX*100,j+worldY*100));
					}
				}
				if (notRandomRandom.nextFloat() * 100 < vegitation / 60) {
					if (new Mushroom().canBeAdded(map[i][j])) {
						map[i][j].add(new SquashPlant(i+worldX*100,j+worldY*100));
					}
				}
				if (notRandomRandom.nextFloat() * 100 < vegitation / 60) {
					if (new Mushroom().canBeAdded(map[i][j])) {
						map[i][j].add(new CarrotPlant(i+worldX*100,j+worldY*100));
					}
				}
				if (notRandomRandom.nextFloat() * 100 < vegitation / 60) {
					if (new Mushroom().canBeAdded(map[i][j])) {
						map[i][j].add(new PalsternackaPlant(i+worldX*100,j+worldY*100));
					}
				}
				
				
				if (notRandomRandom.nextFloat() * 100 < vegitation / 600) {
					if (new Mushroom().canBeAdded(map[i][j])) {
						map[i][j].add(new Mushroom());
					}
				}
				if (notRandomRandom.nextFloat() * 100 < vegitation / 600) {
					if (new Mushroom().canBeAdded(map[i][j])) {
						map[i][j].add(new MushroomBad());
					}
				}
				if (notRandomRandom.nextFloat() * 100 < vegitation / 1000) {
					if (new TomatPlant(i, j).canBeAdded(map[i][j])) {
						map[i][j].add(new TomatPlant(i + worldX * 100, j
								+ worldY * 100));
					}
				}
				if (notRandomRandom.nextFloat() * 100 < vegitation / 800) {
					if (new PapricaPlant(i, j).canBeAdded(map[i][j])) {
						map[i][j].add(new PapricaPlant(i + worldX * 100, j
								+ worldY * 100));
					}
				}
			}
		}
		for (int i = 0; i < higth; i++) {
			for (int j = 0; j < with; j++) {
				if (notRandomRandom.nextFloat() * 100 < vegitation / 50) {
					if (notRandomRandom.nextDouble() * 100 > heat) {
						createATree(i, j, 1);
					} else {
						createATree(i, j, 3);
					}
				}
			}
		}
		for (int i = 0; i < higth; i++) {
			for (int j = 0; j < with; j++) {
				// loading pictures to memory
				map[i][j].calculateImage();
			}
		}

	}
}
