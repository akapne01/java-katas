package kata.amazon;

public class Amazon {

   private final Warehouse warehouse;
   private final Wallet wallet;
   private final Notifier notifier;
   private final String notificationMessage = "You have bought ";

   public Amazon(Warehouse warehouse, Wallet wallet, Notifier notifier) {
      this.warehouse = warehouse;
      this.wallet = wallet;
      this.notifier = notifier;
   }

   public void buy(Customer customer, Item item) {

      if (!wallet.hasEnoughMoney(customer, item.getPrice())) {
         throw new NotEnoughMoneyException();
      }

      warehouse.dispatch(customer, item);
      wallet.bill(customer, item.getPrice());
      notifier.notify(notificationMessage + item.getDescription());
   }
}
