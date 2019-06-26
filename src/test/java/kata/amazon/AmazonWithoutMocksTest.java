package kata.amazon;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class AmazonWithoutMocksTest {

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
      RecordingWarehouse warehouse = new RecordingWarehouse();
      Amazon amazon = new Amazon(warehouse, new AlwaysEnoughMoneyWallet(), new Notifier());
      amazon.buy(customer, item);

      warehouse.verifyDispatched(customer, item);
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