package DataStructure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import testsupport.Console;

class LRUCacheTest {

    @Test
    void returnsMinusOneForMissingKey() {
        LRUCache cache = new LRUCache(2);

        assertEquals(-1, cache.get(1));
    }

    @Test
    void returnsStoredValue() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 10);
        cache.put(2, 20);

        assertEquals(10, cache.get(1));
        assertEquals(20, cache.get(2));
    }

    @Test
    void evictsLeastRecentlyInsertedKeyWhenFull() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 10);
        cache.put(2, 20);

        cache.put(3, 30);

        assertEquals(-1, cache.get(1));
        assertEquals(20, cache.get(2));
        assertEquals(30, cache.get(3));
    }

    @Test
    void getMakesKeyMostRecentlyUsed() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 10);
        cache.put(2, 20);
        cache.get(1);

        cache.put(3, 30);

        assertEquals(10, cache.get(1));
        assertEquals(-1, cache.get(2));
    }

    @Test
    void getOfMissingKeyDoesNotChangeEvictionOrder() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 10);
        cache.put(2, 20);
        cache.get(99);

        cache.put(3, 30);

        assertEquals(-1, cache.get(1));
        assertEquals(20, cache.get(2));
    }

    @Test
    void putOnExistingKeyReplacesValueWithoutEvicting() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 10);
        cache.put(2, 20);

        cache.put(1, 11);

        assertEquals(11, cache.get(1));
        assertEquals(20, cache.get(2));
    }

    @Test
    void putOnExistingKeyMakesItMostRecentlyUsed() {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 10);
        cache.put(2, 20);
        cache.put(1, 11);

        cache.put(3, 30);

        assertEquals(11, cache.get(1));
        assertEquals(-1, cache.get(2));
        assertEquals(30, cache.get(3));
    }

    @Test
    void capacityOneKeepsOnlyTheLatestKey() {
        LRUCache cache = new LRUCache(1);
        cache.put(1, 10);

        cache.put(2, 20);

        assertEquals(-1, cache.get(1));
        assertEquals(20, cache.get(2));
    }

    @Test
    void evictsInOrderAcrossManyInsertions() {
        LRUCache cache = new LRUCache(3);
        for (int key = 1; key <= 10; key++) {
            cache.put(key, key * 10);
        }

        for (int key = 1; key <= 7; key++) {
            assertEquals(-1, cache.get(key), "key " + key + " should have been evicted");
        }
        assertEquals(80, cache.get(8));
        assertEquals(90, cache.get(9));
        assertEquals(100, cache.get(10));
    }

    @Test
    void storesNegativeKeysAndValues() {
        LRUCache cache = new LRUCache(2);

        cache.put(-1, -100);
        cache.put(0, 0);

        assertEquals(-100, cache.get(-1));
        assertEquals(0, cache.get(0));
    }

    @Test
    void evictedKeyCanBeInsertedAgain() {
        LRUCache cache = new LRUCache(1);
        cache.put(1, 10);
        cache.put(2, 20);

        cache.put(1, 12);

        assertEquals(12, cache.get(1));
        assertEquals(-1, cache.get(2));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, Integer.MIN_VALUE})
    void rejectsCapacityBelowOne(int capacity) {
        assertThrows(IllegalArgumentException.class, () -> new LRUCache(capacity));
    }

    @Test
    void mainPrintsHitThenMiss() throws Exception {
        assertEquals("20\n-1", Console.capture(() -> LRUCache.main()));
    }
}
