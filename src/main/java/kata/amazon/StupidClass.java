package kata.amazon;

import com.sun.javaws.exceptions.ErrorCodeResponseException;

public class StupidClass {

   public StupidClass() {
      new StupidClass();
   }

   public static void main(String[] args) {
      // throw new Throwable(); -> need to declare it
      //throw new Error("I'm a stupid class error!");
      // new StupidClass();
   }
}
