package kata.amazon;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class AmazonTest {

   private Warehouse warehouse = mock(Warehouse.class);
   private Wallet wallet = mock(Wallet.class);
   private Notifier notifier = mock(Notifier.class);
   private Customer customer = new Customer();
   private Item item = new Item(10, "a toaster");
   private Amazon amazon;

   @Before
   public void setUp() {
      when(wallet.hasEnoughMoney(customer, 10)).thenReturn(true);
      amazon = new Amazon(warehouse, wallet, notifier);
   }

   @Test
   public void shouldDispatchItemWhenBought() {
      amazon.buy(customer, item);

      verify(warehouse).dispatch(customer, item);
   }

   @Test
   public void shouldPayForItemWithWallet() {
      amazon.buy(customer, item);

      verify(wallet).bill(customer, 10);
   }

   @Test
   public void shouldNotifyWhenItemBought() {
      amazon.buy(customer, item);

      String msg = "You have bought a toaster";
      verify(notifier).notify(msg);
   }

   @Test
   public void shouldThrowExceptionIfNotEnoughMoney() {
      when(wallet.hasEnoughMoney(customer, 10)).thenReturn(false);

      try {
         amazon.buy(customer, item);
         fail(); // manually fails
      } catch (NotEnoughMoneyException e) {
         verify(wallet, times(0)).bill(customer, 10);
         verifyZeroInteractions(warehouse, notifier);
      }
   }
}