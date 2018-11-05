package rummi;

public class Tile {

	private int value;
	private char colour;
	private int tileID;
	
	public Tile(char c, int i) {
		value = i;
		colour = c;
		tileID = 0;
	}

	public int getValue() {
		return value;
	}

	public String getColour() {
		return Character.toString(this.colour);
	}
	
	public void setID(int i) {
		this.tileID = i;
	}
	
	public int getID() {
		return this.tileID;
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
        return (t.getValue() == this.getValue()) && (t.getColour().equals(this.getColour()) && (t.getID() == this.getID()));
	}

}
