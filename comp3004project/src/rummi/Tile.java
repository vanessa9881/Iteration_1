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
	//   return "file:resources/tiles/"+colour.getName() + number.getSymbol() + ".gif";
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
		   	  return number.toString() + " of " + colour.toString();
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
	  

	   /*
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
        // Compare the char and int  
        return (t.getValue() == this.getValue()) && (t.getColour().equals(this.getColour()));
	}
*/
}
