package domain.map;

import java.util.TreeSet;

import domain.Tile;
import domain.backGroundTile.Grass;
import domain.backGroundTile.Water;

public class SortedTileList extends TreeSet<Tile>{

	Tile getTileOfType(Tile tile){
		for(Tile loop:this){
			if(loop.getClass()==tile.getClass())
				return loop;
		}
		return null;
	}
	boolean removeTileOfType(Tile tile){
		Tile remove=getTileOfType(tile);
		if(remove!=null){
			return this.remove(remove);
		}
		return false;
	}
	boolean existTileOfType(Tile tile){
		for(Tile loop:this){
			if(loop.getClass()==tile.getClass()){
				return true;
			
			}
		}
		return false;
	}
	
	void addTile(Tile tile){
		
			this.add(tile);
		
	}
}
