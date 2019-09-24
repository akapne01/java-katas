package kata.calendar;

public class CalendarOperationResult {
   private final boolean successful;
   private final String failingReason;

   public CalendarOperationResult(boolean successful) {
      this(successful, "Successful");
   }

   public CalendarOperationResult(boolean successful, String failingReason) {
       this.successful = successful;
       this.failingReason = failingReason;
   }

   public boolean isSuccessful() {
      return successful;
   }

    public String getFailingReason() {
       return failingReason;
    }
}
