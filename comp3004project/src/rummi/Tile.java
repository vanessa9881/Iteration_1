package rummi;

public class Tile {

	public Tile(char c, int i) {
		// TODO Auto-generated constructor stub
	}

	public int getValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getColour() {
		// TODO Auto-generated method stub
		return null;
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
