package rummi;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Colour {
   private String name;
   private String symbol;
   
   public final static Colour RED = new Colour("Red", "r");
   public final static Colour BLACK = new Colour("Black","b");
   public final static Colour BLUE = new Colour("Blue","g");
   public final static Colour YELLOW = new Colour("Yellow","y");
   
   public final static List<Colour> VALUES = Collections.unmodifiableList(
		   Arrays.asList(new Colour[] { RED, BLACK, BLUE, YELLOW } ) );

   private Colour(String nameValue, String symbolValue) {
      name = nameValue;
      symbol = symbolValue;
   }
       
   public String getName() {
       return name;
   }
   
   public String getSymbol() {
	      return symbol;
	   }

   @Override
   public String toString() {
      return name;
   }
}

    