package rummi;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Number class mainly used 
public class Number {
   private String name;
   private String symbol;
    
   final static Number ONE = new Number( "One", "1" );
   final static Number TWO = new Number( "Two", "2" );
   final static Number THREE = new Number( "Three", "3" );
   final static Number FOUR = new Number( "Four", "4" );
   final static Number FIVE = new Number( "Five", "5" );
   final static Number SIX = new Number( "Six", "6" );
   final static Number SEVEN = new Number( "Seven", "7" );
   final static Number EIGHT = new Number( "Eight", "8" );
   final static Number NINE = new Number( "Nine", "9" );
   final static Number TEN = new Number( "Ten", "10" );
   final static Number ELEVEN = new Number( "Eleven", "11" );
   final static Number TWELVE = new Number( "Twelve", "12" );
   final static Number THIRTEEN = new Number( "Thirteen", "13" );

   public final static List VALUES =
      Collections.unmodifiableList( Arrays.asList( new Number[] { ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN,
                                     EIGHT, NINE, TEN, ELEVEN, TWELVE, THIRTEEN } ) );
   
   private Number(String nameValue, String symbolValue) {
      name = nameValue;
      symbol = symbolValue;
   }

   @Override
   public String toString() {
      return name;
   }

   public String getSymbol() {
      return symbol;
   }
}                                                                 