package domain.map;

import java.util.TreeSet;

import domain.Tile;

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
}
