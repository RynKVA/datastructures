package org.example.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractMapTest {
    private Map<String, Integer> emptyMap;
    private Map<String, Integer> MapWithTwoEntries;

    abstract Map<String, Integer> getMap();

    abstract Map<Integer, Integer> getIntegerMap();

    abstract Map<Integer, Integer> getIntegerMap(int capacity);

    @BeforeEach
    void before() {
        emptyMap = getMap();
        MapWithTwoEntries = getMap();

        MapWithTwoEntries.put("A", 1);
        MapWithTwoEntries.put("B", 3);
    }

    @Test
    @DisplayName("When put in empty map two pair then check get by key which return value")
    void testPutAndGet() {
        assertNull(emptyMap.put("A", 1));
        assertNull(emptyMap.put("B", 3));

        assertEquals(2, emptyMap.size());

        assertEquals(1, emptyMap.get("A"));
        assertEquals(3, emptyMap.get("B"));
    }

    @Test
    @DisplayName("When put first pair in empty map then return null")
    void whenDoingFirstPutThenReturnNull() {
        assertNull(emptyMap.put("A", 1));
    }

    @Test
    @DisplayName("When put pair with already exist key then replace value")
    void testPutPairBySameKeyAndNewValueReplaceOldValueReturnOldValue() {
        assertEquals(3, MapWithTwoEntries.put("B", 5));

        assertEquals(5, MapWithTwoEntries.get("B"));
    }

    @Test
    @DisplayName("When put pair with key and value null then stand in first index bucket")
    void testPutIfKeyAndValueIsNullStandInBucketIndexZero() {
        emptyMap.put(null, null);

        assertEquals(1, emptyMap.size());

        assertNull(emptyMap.get(null));
    }

    @Test
    @DisplayName("When put pair with already exist key null in map then value replace and return old value")
    void whenPutKeyNullInMapWhichAlreadyExistPairWithKeyNullThenReplaceOnNewValueAndReturnOldValue() {
        emptyMap.put(null, null);
        assertNull(emptyMap.put(null, 8));

        assertEquals(1, emptyMap.size());

        assertEquals(8, emptyMap.get(null));
    }

    @Test
    @DisplayName("When put by the key with negative hashCode then it put in bucket which index is remainder of the division module of hash on buckets length")
    void whenPutByTheKeyWithNegativeHashCodeThenItsWorkCorrectly() {
        assertEquals(-515212422, "sn,fdds23842342".hashCode());
        emptyMap.put("sn,fdds23842342", 3);
        assertEquals(3, emptyMap.get("sn,fdds23842342"));
    }

    @Test
    @DisplayName("When create hashMap with capacity zero and put one pair then size and capacity stand one")
    void whenBucketsCapacityIsZeroAndPutOnePairThenSizeStandOne() {
        Map<Integer, Integer> map = getIntegerMap(0);

        assertNull(map.put(0, 1));
        assertEquals(1, map.size());
    }

    @Test
    @DisplayName("When get by key then return value which is in pair with this key")
    void whenGetByTheKeyThenReturnValueWhichIsInPairWithThisKey() {
        assertEquals(1, MapWithTwoEntries.get("A"));
        assertEquals(3, MapWithTwoEntries.get("B"));
    }

    @Test
    @DisplayName("When get by not used key then return null")
    void whenGetByNotUsedKeyThenReturnNull() {
        assertNull(MapWithTwoEntries.get("H"));
    }

    @Test
    @DisplayName("When get by the key entries which in same bucket then returns corresponding value")
    void whenGetByTheKeyEntriesWhichInSameBucketThenReturnCorrespondingValue() {
        Map<Integer, Integer> integerMap = getIntegerMap();
        integerMap.put(1, 1);
        integerMap.put(17, 17);

        assertEquals(1, integerMap.get(1));
        assertEquals(17, integerMap.get(17));
    }

    @Test
    @DisplayName("When get by the not exist key in bucket then returns null")
    void whenGetByTheNotExistKeyInBucketThenReturnNull() {
        Map<Integer, Integer> integerMap = getIntegerMap();
        integerMap.put(1, 1);
        integerMap.put(17, 17);

        assertNull(integerMap.get(33));
    }

    @Test
    @DisplayName("When remove by key then delete pair (key, value) and return deleted value")
    void whenRemoveByTheExistKeyThenDeletePairThisKeyAndValueAndReturnDeletedValue() {
        assertEquals(2, MapWithTwoEntries.size());

        assertEquals(1, MapWithTwoEntries.remove("A"));
        assertEquals(3, MapWithTwoEntries.remove("B"));

        assertEquals(0, MapWithTwoEntries.size());
    }

    @Test
    @DisplayName("When remove by not exist key then return null")
    void whenRemoveByNotExistKeyThenReturnNull() {
        assertNull(MapWithTwoEntries.remove("H"));
    }

    @Test
    @DisplayName("When remove by key which is null then delete pair (key, value) and return deleted value")
    void whenRemoveByExistKeyWhichIsNullThenDeleteThisPairKeyAndValueAndReturnDeletedValue() {
        emptyMap.put(null, 5);
        assertEquals(1, emptyMap.size());

        assertEquals(5, emptyMap.remove(null));
        assertEquals(0, emptyMap.size());
    }

    @Test
    @DisplayName("When remove second entry in same bucket then second pair is removed and size decreased")
    void whenRemoveSecondEntryThenItsWorkCorrectlyAndSizeDecreased() {
        Map<Integer, Integer> integerMap = getIntegerMap();
        integerMap.put(1, 1); //same bucket
        integerMap.put(17, 17); //same bucket
        assertEquals(2, integerMap.size());

        assertEquals(17, integerMap.remove(17));
        assertEquals(1, integerMap.size());
    }

    @Test
    @DisplayName("When try remove by not exist key in one bucket then return null and size not changes")
    void removeByNotExistKeyReturnNull() {
        Map<Integer, Integer> integerMap = getIntegerMap();
        integerMap.put(1, 1); //same bucket
        integerMap.put(17, 17); //same bucket
        assertEquals(2, integerMap.size());

        assertNull(integerMap.remove(33));
        assertEquals(2, integerMap.size());
    }

    @Test
    @DisplayName("When map has two pair then size is two")
    void whenMapHasTwoPairKayValueThenReturnSizeTwo() {
        emptyMap.put("A", 1);
        emptyMap.put("B", 3);

        assertEquals(2, emptyMap.size());
    }

    @Test
    @DisplayName("When map has two pair and put pair with already exist key then replace value and size not increased")
    void whenSizeIsTwoAndUsedPutByAlreadyExistKeyThenSizeIsNoteChange() {
        emptyMap.put("A", 1);
        emptyMap.put("A", 3);

        assertEquals(3, emptyMap.get("A"));
        assertEquals(1, emptyMap.size());
    }

    @Test
    @DisplayName("When key is contained in map then return true")
    void whenKeyIsContainedThenReturnTrue() {
        assertTrue(MapWithTwoEntries.containsKey("A"));
    }

    @Test
    @DisplayName("When key is not contained in map then return false")
    void whenKeyIsNotContainedThenReturnFalse() {
        assertFalse(MapWithTwoEntries.containsKey("D"));
    }

    @Test
    @DisplayName("When method isEmpty used on empty map then return true")
    void testIsEmptyOnEmptyMapReturnTrue() {
        assertTrue(emptyMap.isEmpty());
    }

    @Test
    @DisplayName("When method isEmpty used on not empty map then return false")
    void testIsEmptyOnNotEmptyMapReturnFalse() {
        assertFalse(MapWithTwoEntries.isEmpty());
    }

    @Test
    @DisplayName("When used method hasNext if next element exist then return true")
    void whenUsedHasNextIfNextElementExistThenReturnTrue() {
        Iterator<Map.Entry<String, Integer>> iterator = MapWithTwoEntries.iterator();

        assertTrue(iterator.hasNext());

        iterator.next();
        assertTrue(iterator.hasNext());
    }

    @Test
    @DisplayName("When used method hasNext if next element not exist then return false")
    void whenUsedHasNextIfNextElementNotExistThenReturnFalse() {
        Iterator<Map.Entry<String, Integer>> iterator = MapWithTwoEntries.iterator();

        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();

        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("When used method hasNext on empty map then return false")
    void whenUsedHasNextInEmptyHashMapThenReturnFalse() {
        Iterator<Map.Entry<String, Integer>> iterator = emptyMap.iterator();

        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("When used method next if next entry exist then return target entry with his pair(key, value)")
    void whenUsedNextIfNextElementExistThenReturnTargetEntryWithPairKeyAndValue() {
        Iterator<Map.Entry<String, Integer>> iterator = MapWithTwoEntries.iterator();

        Map.Entry<String, Integer> firstEntry = iterator.next();
        assertEquals("A", firstEntry.getKey());
        assertEquals(1, firstEntry.getValue());

        Map.Entry<String, Integer> secondEntry = iterator.next();
        assertEquals("B", secondEntry.getKey());
        assertEquals(3, secondEntry.getValue());
    }

    @Test
    @DisplayName("When used method next if next entry not exist then expect NoSuchElementException")
    void whenUsedNextIfNextElementNotExistThenExpectNoSuchElementException() {
        Iterator<Map.Entry<String, Integer>> iterator = MapWithTwoEntries.iterator();

        iterator.next();
        iterator.next();

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                iterator::next);
        assertEquals("No next element.", exception.getMessage());
    }

    @Test
    @DisplayName("When used method next on empty map then expect NoSuchElementException")
    void whenUsedNextOnEmptyHashMapThenExpectNoSuchElementException() {
        Iterator<Map.Entry<String, Integer>> iterator = emptyMap.iterator();

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                iterator::next);
        assertEquals("No next element.", exception.getMessage());
    }

    @Test
    @DisplayName("When using iterator method remove after used method next then remove target entry and decreased size")
    void whenUsingRemoveAfterUsedNextThenRemoveTargetEntryAndDecreasedSize() {
        Iterator<Map.Entry<String, Integer>> iterator = MapWithTwoEntries.iterator();

        assertEquals(2, MapWithTwoEntries.size());

        iterator.next();
        iterator.remove();
        assertEquals(1, MapWithTwoEntries.size());

        iterator.next();
        iterator.remove();
        assertEquals(0, MapWithTwoEntries.size());
    }

    @Test
    @DisplayName("When using iterator method remove before using method next then expect IllegalStateException")
    void whenUsingRemoveBeforeUsedNextThenExpectIllegalStateException() {
        Iterator<Map.Entry<String, Integer>> iterator = MapWithTwoEntries.iterator();

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                iterator::remove);
        assertEquals("Method next() not used.", exception.getMessage());
    }

    @Test
    @DisplayName("When using iterator method remove after already using iterator method remove then expect IllegalStateException")
    void whenUsingRemoveAfterAlreadyUsedRemoveThenExpectIllegalStateException() {
        Iterator<Map.Entry<String, Integer>> iterator = MapWithTwoEntries.iterator();

        assertEquals(2, MapWithTwoEntries.size());

        iterator.next();
        iterator.remove();
        assertEquals(1, MapWithTwoEntries.size());

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                iterator::remove);
        assertEquals("Method next() not used.", exception.getMessage());
    }

    @Test
    @DisplayName("When using iterator method remove on map with three entries which in same bucket then its remove one by one")
    void testRemoveOnMapWithThreeEntriesInSameBucket() {
        Map<Integer, Integer> integerMap = getIntegerMap();
        integerMap.put(1, 1);
        integerMap.put(17, 17);
        integerMap.put(33, 33);
        assertEquals(3, integerMap.size());

        Iterator<Map.Entry<Integer, Integer>> iterator = integerMap.iterator();
        iterator.next();
        iterator.remove();
        assertEquals(2, integerMap.size());

        iterator.next();
        iterator.remove();
        assertEquals(1, integerMap.size());

        iterator.next();
        iterator.remove();
        assertEquals(0, integerMap.size());
    }

    @Test
    @DisplayName("Test on correction HashMap method toString")
    void toStringHashMap() {
        assertEquals("{A=1, B=3}", MapWithTwoEntries.toString());
    }

    @Test
    @DisplayName("When default capacity (16) is full on seventy five percent (size = 12) then buckets capacity growing multiply double and plus one (33)")
    void testMethodGrowBucketsWhenTheDefaultCapacityIsFullOnSeventyFivePercent() {
        Map<Integer, Integer> integerMap = getIntegerMap();
        int key = 0;
        int value = 1;

        for (int i = 0; i < 12; i++) {
            integerMap.put(key, value);
            key++;
            value++;
        }
        assertEquals(12, integerMap.size());

        for (int i = 12; i < 33; i++) {
            integerMap.put(key, value);
            key++;
            value++;
        }
        assertEquals(33, integerMap.size());
    }

    @Test
    @DisplayName("When grow buckets capacity then method refillBuckets distributes entries in same bucket")
    void checkMethodRefillBucketsWhenItsDoingRefill() {
        Map<Integer, Integer> integerMap = getIntegerMap(3);
        integerMap.put(1, 1); // after refill in 1 index bucket
        integerMap.put(8, 8); // after refill in same bucket (1 index)
        integerMap.put(15, 15); // after refill in same bucket (1 index)
        assertEquals(3, integerMap.size());

        integerMap.put(2, 2);
        integerMap.put(3, 3);
        assertEquals(5, integerMap.size());
    }
}
