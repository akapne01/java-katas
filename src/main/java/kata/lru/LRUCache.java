package kata.lru;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> {

   private final Map<K, Value<V>> cache = new HashMap<>();
   private int size;
   private int counter;

   public LRUCache(int size) {
      this.size = size;
      this.counter = 0;
   }

   public void insert(K key, V value) {

      Value<V> valueToAdd = new Value<>(value);
      if (cache.containsKey(key)) {
         cache.put(key, valueToAdd);
      } else if (counter < size) {
         cache.put(key, valueToAdd);
         counter++;
      } else {
         K keyToRemove = null;
         for (K k : cache.keySet()) {
            if (keyToRemove == null) {
               keyToRemove = k;
            }
            if (cache.get(k).getDateTime().isBefore(cache.get(keyToRemove).getDateTime())) {
               keyToRemove = k;
            }
         }
         cache.remove(keyToRemove);
         cache.put(key, valueToAdd);
      }
   }

   public V get(K key) {
      if (cache.containsKey(key)) {
         return cache.get(key).getValue();
      }
      return null;
   }
}
