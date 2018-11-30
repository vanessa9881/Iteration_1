package rummi;

import java.util.Comparator;

// Comparator class that can be used when calling
// ArrayList<Tile> tList.sort(Comparator)
// Will sort by value, and then by colour
// With the colour sorting being in the order:
// "Red", "Black", "Blue", "Yellow"
public class TileComparator implements Comparator<Tile>{

	@Override
	public int compare(Tile t1, Tile t2) {
		if (t1.getColour().equals("j") || t2.getColour().equals("j")) {return 1;}
		if (t1.getValue() > t2.getValue()) {return 1;}
		else if (t1.getValue() < t2.getValue()) {return -1;}
		// Tiles have the same value so compare colours
		else {
			// Both have the same value and colour
			if (t1.getColour().toString().equals(t2.getColour().toString())){return 0;}
			
			// Both do not have the same colour. Therefore,
			// we can check if the first tile is red or yellow,
			// and place it either ahead or behind of the second tile
			else if(t1.getColour().toString().equals("Red")) {return 1;}
			else if(t1.getColour().toString().equals("Yellow")) {return -1;}
			// If we get here, we know that both tile 1 and 2 are neither
			// red or yellow, meaning that one is black and one is blue
			else if (t1.getColour().toString().equals("Black")) {return 1;}
			// Tile 1 is blue and tile 2 is black
			return -1;
		}
	}
}
