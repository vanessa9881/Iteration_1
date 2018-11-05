//Tile class
package rummi;

import javafx.scene.image.Image;
import rummi.Colour;
import rummi.Number;

public class Tile {

	private Number number;
	private Colour colour;
	private Image tileImage;
	
	public Tile(Colour c,Number i, Image t) {
		number = i;
		colour = c;
		tileImage = t;
	}

	public static String getFilename(Colour colour, Number number) {
	   return "file:resources/"+number.getSymbol() + colour.getSymbol() + ".gif";   
	}
	
	
	public Number getNumberValue() {
		return number;
	}
	
	public Colour getColour() {
		   return colour;
	}
	
	public Image getTileImage() {
		   return tileImage;
	}
	
	@Override
	public String toString() {
		   return colour.toString() + " " + number.toString();
	}

	public String numberToString() {
		   return number.toString();
	}

	public String colourToString() {
		   return colour.toString();
	}	
	
	public int getValue() {
		   String numberValue = number.getSymbol();  
		   return Integer.parseInt(numberValue);  
	}
	
	@Override
    public boolean equals(Object o) {
		// Overrides the equals method for tile, should check if the colour
		// and the NumberValue are the same!
		
		// If the object is compared with itself then return true   
        if (o == this) { 
            return true; 
        } 
  
        // Check if compared object is a Tile
        if (!(o instanceof Tile)) { 
            return false; 
        } 
           
        Tile t = (Tile) o;   
        // Compare the number and colour using their tostring()
        return (t.colourToString().equals(this.colourToString())) && (t.numberToString().equals(this.numberToString()));
	}
}
