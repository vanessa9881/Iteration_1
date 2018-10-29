package rummi;

public class Tile {

	private int value;
	private char colour;
	
	public Tile(char c, int i) {
		value = i;
		colour = c;
	}

	public int getValue() {
		return value;
	}

	public String getColour() {
		switch (colour) {
		
		case 1: colour = 'k';
		return "Black";
		
		case 2: colour = 'r';
		return "Red";
		
		case 3: colour = 'o';
		return "Orange";
		
		case 4: colour = 'b';
		return "Blue";
		
		default: colour ='?';
		return "Error, tile had no valid colour!";
		}
	}
	
	@Override
    public boolean equals(Object o) {
		// Overrides the equals method for tile, should check if the colour
		// and the value are the same!
		
		// If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        // Check if compared object is a Tile
        if (!(o instanceof Tile)) { 
            return false; 
        } 
           
        Tile t = (Tile) o;   
        // Compare the char and int  
        return (t.getValue() == this.getValue()) && (t.getColour().equals(this.getColour()));
	}

}
