package kata.lrucachepuzzle;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

   private final int size;
   private final Map<Object, CacheEntry> cache = new HashMap<>();

   private CacheEntry first = null;
   private CacheEntry last = null;

   public LRUCache(int size) {
      this.size = size;
   }

   public void put(Object key, Object value) {
      CacheEntry newEntry = CacheEntry.newEntry(key, value);

      if (cache.isEmpty()) {
         first = newEntry;
         last = newEntry;
      }
      //if cache is empty
      //make first indicator point to newEntry
      //make last indicator point to newEntry

      else if (cache.containsKey(key)) {
         CacheEntry oldEntry = cache.remove(key);
         removeFromList(oldEntry);
         insertNewEntryAsFirst(newEntry);
      }
      //else if cache contains an item for key
      //don't need to evict oldest entry (just FYI, no code needed for this line)
      //remove existing entry from cache
      //remove existing entry from list
      //insert newEntry as first item in the list - template A

      else if (cache.size() == size) {
         cache.remove(last.getKey()); // oldest
         removeFromList(last);
         insertNewEntryAsFirst(newEntry);
      }
      //else if cache is full
      //remove oldest entry from cache
      //remove oldest entry from list
      //insert newEntry as first item in the list - template A

      else {
         insertNewEntryAsFirst(newEntry);
      }

      //else
      //insert newEntry as first item in the list - template A

      cache.put(key, newEntry);
      //insert newEntry into cache

   }

   private void removeFromList(CacheEntry cacheEntry) {
      //if cacheEntry is first
      //remove first entry - template B
      if (cacheEntry == first) {
         CacheEntry second = cacheEntry.getNext();
         second.setPrevious(null);
         first = second;
         cacheEntry.setNext(null);
      } else if (cacheEntry == last) {
         CacheEntry entryBefore = cacheEntry.getPrevious();
         entryBefore.setNext(null);
         last = entryBefore;
         cacheEntry.setPrevious(null);
      } else {
         CacheEntry before = cacheEntry.getPrevious();
         CacheEntry next = cacheEntry.getNext();
         before.setNext(next);
         next.setPrevious(before);
         cacheEntry.setPrevious(null);
         cacheEntry.setNext(null);
      }

      //else if cacheEntry is last
      //remove last entry - template C

      //else
      //remove cacheEntry from the middle - template D
   }

   private void insertNewEntryAsFirst(CacheEntry newEntry) {
      first.setPrevious(newEntry);
      newEntry.setNext(first);
      first = newEntry;
   }

   public Object get(Object key) {
      CacheEntry cacheEntry = cache.get(key);
      if (cacheEntry == null) {
         return null;
      }

      moveToTheFront(cacheEntry);
      return cacheEntry.getEntry();
   }

   private void moveToTheFront(CacheEntry cacheEntry) {
      if (cacheEntry == first) {
         return;
      }
      //if cache entry is first
      //don't need to do anything, cacheEntry is already the LRU item!
      if (cacheEntry == last) {
         // Template E
         CacheEntry previous = cacheEntry.getPrevious();
         previous.setNext(null);
         last = previous;

         first.setPrevious(cacheEntry);
         cacheEntry.setPrevious(null);
         cacheEntry.setNext(first);
         first = cacheEntry;
      }

      //if cache entry is last
      //move cacheEntry to the front from the last position - template E

      else {
         CacheEntry previous = cacheEntry.getPrevious();
         CacheEntry next = cacheEntry.getNext();
         previous.setNext(next);
         next.setPrevious(previous);
         cacheEntry.setPrevious(null);
         cacheEntry.setNext(first);
         first.setPrevious(cacheEntry);
         first = cacheEntry;
      }
      //else
      //move cacheEntry to the front from the middle - template F
   }
}
