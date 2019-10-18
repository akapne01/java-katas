package kata.lru;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class LRUCacheTest {

   @Test
   public void testStoreAndRetrieveItemsInCache() {
      int size = 2;
      LRUCache<String, String> cache = new LRUCache<>(size);
      String key = "This is a key";
      String expectedValue = "Value returned";
      cache.insert(key, expectedValue);
      String actualValue = cache.get(key);
      assertThat(actualValue,  is(expectedValue));
   }



   @Test
   public void shouldNotStoreAnyNewValuesWhenCacheIsFull() {
      int size = 1;
      LRUCache<String, String> cache = new LRUCache<>(size);
      String key = "This is a key";
      String value = "Value returned";
      String keyNotSaved = "This key should not be saved as cache is full";
      String notSavedValue = "This value should not be saved as cache is full";
      cache.insert(key, value);
      cache.insert(keyNotSaved, notSavedValue);
      String actualValueReturned = cache.get(key);
      String actualValueNotReturned = cache.get(keyNotSaved);
      assertThat(actualValueReturned, is(value));
      assertNull(actualValueNotReturned);

   }

   @Test
   public void shouldUpdateTheValueOfKeyWhenCacheIsFullAndKeyExists() {
      int size = 1;
      LRUCache<String, String> cache = new LRUCache<>(size);
      String key = "This is a key";
      String value = "Value returned";
      String updatedValueForTheKey = "This is updated value that is returned";
      cache.insert(key, value);
      cache.insert(key, updatedValueForTheKey);
      String actualValueReturned = cache.get(key);
      assertThat(actualValueReturned, is(updatedValueForTheKey));
   }

   @Test
   public void xx() {
      int size = 2;
      LRUCache<String, String> cache = new LRUCache<>(size);
      String key = "This is a key";
      String value = "Value returned";
      String updatedValueForTheKey = "This is updated value that is returned";
      cache.insert(key, value);
      cache.insert(key, updatedValueForTheKey);
      cache.insert("qq", "ww");
      String actualValueReturned = cache.get(key);
      assertThat(actualValueReturned, is(updatedValueForTheKey));
      assertThat(cache.get("qq"), is("ww"));
   }

   @Test
   public void lll() {
      int size = 2;
      LRUCache<String, String> cache = new LRUCache<>(size);
      String keyAddedNotRetrievable = "A Key that should return null";
      String value = "Value Returned";
      String keyAdded2 = "Key2";
      String value2 = "Value2";
      String keyAdded3 = "Key3 that replaces key1";
      String value3 = "Value3";

      cache.insert(keyAddedNotRetrievable, value);
      cache.insert(keyAdded2, value2);
      cache.insert(keyAdded3, value3);

      String actualValueForKey1 = cache.get(keyAddedNotRetrievable);
      String actualValueForKey2 = cache.get(keyAdded2);
      String actualValueForKey3 = cache.get(keyAdded3);

      System.out.println(actualValueForKey1);
      assertNull(actualValueForKey1);
      assertThat(actualValueForKey2, is(value2));
      assertThat(actualValueForKey3, is(value3));


   }
}