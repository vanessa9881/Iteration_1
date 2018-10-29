package rummi;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

	private ArrayList<Tile> tileList;
	
	public Deck() {
		tileList = new ArrayList<Tile>();
		int i = 0;
		while(i !=2) {
			for (int x = 1; x <= 13; x++) {
				tileList.add(new Tile('k', x));
			}
			for (int x = 1; x <= 13; x++) {
				tileList.add(new Tile('k', x));
			}
			for (int x = 1; x <= 13; x++) {
				tileList.add(new Tile('k', x));
			}
			for (int x = 1; x <= 13; x++) {
				tileList.add(new Tile('k', x));
			}
			i++;
		}
	}
	
	public int getNumberOfTiles() {
		return tileList.size();
	}

	public Tile peek() {
		return tileList.get(0);
	}

	public Tile draw() {
		Tile drawnTile = tileList.get(0);
		tileList.remove(0);
		return drawnTile;
	}

	public ArrayList<Tile> getDeck() {
		return tileList;
	}

	public void shuffle() {
		Collections.shuffle(tileList);
		
	}

}
