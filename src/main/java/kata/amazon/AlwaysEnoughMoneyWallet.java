package kata.amazon;

public class AlwaysEnoughMoneyWallet extends Wallet {

   @Override
   public boolean hasEnoughMoney(Customer customer, double amount) {
      return true;
   }
}
