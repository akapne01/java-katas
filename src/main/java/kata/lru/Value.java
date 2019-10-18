package kata.lru;

import java.time.LocalDateTime;

public class Value<V> {
   private final V value;
   private final LocalDateTime dateTime;

   public Value(V value) {
      this.value = value;
      this.dateTime = LocalDateTime.now();
   }

   public V getValue() {
      return value;
   }

   public LocalDateTime getDateTime() {
      return dateTime;
   }
}
