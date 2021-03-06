//Tile class
package rummi;

import javafx.scene.image.Image;
import rummi.Colour;
import rummi.Number;

public class Tile {

	private Number number;
	private Colour colour;
	private Image tileImage;
	private int tileID;
	
	public Tile(Colour c,Number i, Image t) {
		number = i;
		colour = c;
		tileImage = t;
		tileID = 1;
	}
	
	// For Jokers
	public Tile(Colour j, Image t) {
		number = Number.ZERO;
		colour = j;
		tileImage = t;
		tileID = 10;
		
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
	  
	
	public void setID(int i) {
		this.tileID = i;
	}
	
	public int getID() {
		return this.tileID;
	}
	
	public void setJokerValue(int i) {
		if (tileID > 9) {
			number = number.getVALUES().get(i - 1);
		}
	}
	
	public void setJokerColour(Colour c) {
		if (tileID > 9) {
			colour = c;
		}
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

        // Compare the char and int and id
        return (t.getValue() == this.getValue()) && (t.getColour().toString().equals(this.getColour().toString()) && (t.getID() == this.getID()));
	}
}
