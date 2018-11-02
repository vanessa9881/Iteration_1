package rummi;

import java.util.*;

public class Number {
   private String name;
   private String symbol;
    
   public final static Number ONE = new Number( "One", "1" );
   public final static Number TWO = new Number( "Two", "2" );
   public final static Number THREE = new Number( "Three", "3" );
   public final static Number FOUR = new Number( "Four", "4" );
   public final static Number FIVE = new Number( "Five", "5" );
   public final static Number SIX = new Number( "Six", "6" );
   public final static Number SEVEN = new Number( "Seven", "7" );
   public final static Number EIGHT = new Number( "Eight", "8" );
   public final static Number NINE = new Number( "Nine", "9" );
   public final static Number TEN = new Number( "Ten", "10" );
   public final static Number ELEVEN = new Number( "Eleven", "11" );
   public final static Number TWELVE = new Number( "Twelve", "12" );
   public final static Number THIRTEEN = new Number( "Thirteen", "13" );

   public final static java.util.List VALUES =
      Collections.unmodifiableList( Arrays.asList( new Number[] { ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN,
                                     EIGHT, NINE, TEN, ELEVEN, TWELVE, THIRTEEN } ) );
   
   private Number(String nameValue, String symbolValue) {
      name = nameValue;
      symbol = symbolValue;
   }

   public String getName() {
      return name;
   }

   @Override
   public String toString() {
      return name;
   }

   public String getSymbol() {
      return symbol;
   }
}                                                                 