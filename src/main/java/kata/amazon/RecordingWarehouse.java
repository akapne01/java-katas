package kata.amazon;

import java.util.ArrayList;
import java.util.List;

public class RecordingWarehouse extends Warehouse {

   private Customer customer;
   private Item item;

   @Override
   public void dispatch(Customer customer, Item item) {
      this.customer = customer;
      this.item = item;
   }

   public void verifyDispatched(Customer customer, Item item) {
      if (this.customer != customer || this.item != item) {
         throw new RuntimeException("fail test");
      }
   }

}
