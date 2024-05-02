package org.example.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {
    private final HashMap<Character, Integer> emptyHashMap = new HashMap<>();
    private final HashMap<Character, Integer> hashMapWithTwoEntries = new HashMap<>();
    private final HashMap<String, Integer> stringKeyHashMap = new HashMap<>();

    @BeforeEach
    void before() {
        hashMapWithTwoEntries.put('A', 1);
        hashMapWithTwoEntries.put('B', 3);

    }

    @Test
    @DisplayName("When put in empty map two pair then check get by key witch return value")
    void testPutAndGet() {
        assertNull(emptyHashMap.put('A', 1));
        assertNull(emptyHashMap.put('B', 3));

        assertEquals(2, emptyHashMap.size());

        assertEquals(1, emptyHashMap.get('A'));
        assertEquals(3, emptyHashMap.get('B'));
    }

    @Test
    @DisplayName("When put first pair in empty map then return null")
    void whenDoingFirstPutThenReturnNull() {
        assertNull(emptyHashMap.put('A', 1));
    }

    @Test
    @DisplayName("When put pair with already put key then replace value")
    void testPutPairBySameKeyAndNewValueReplaceOldValueReturnOldValue() {
        assertEquals(3, hashMapWithTwoEntries.put('B', 5));

        assertEquals(5, hashMapWithTwoEntries.get('B'));
    }

    @Test
    @DisplayName("When put pair with key and value null then stand in first index bucket")
    void testPutIfKeyAndValueIsNullStandInBucketIndexZero() {
        emptyHashMap.put(null, null);

        assertEquals(1, emptyHashMap.size());

        assertNull(emptyHashMap.get(null));
    }

    @Test
    @DisplayName("When put pair with already exist key null in map then value replace and return old value")
    void whenPutKeyNullInMapWitchAlreadyExistPairWithKeyNullThenReplaceOnNewValueAndReturnOldValue() {
        emptyHashMap.put(null, null);
        assertNull(emptyHashMap.put(null, 8));

        assertEquals(1, emptyHashMap.size());

        assertEquals(8, emptyHashMap.get(null));
    }

    @Test
    @DisplayName("When put by the key with negative hashCode then its work correctly")
    void whenPutByTheKeyWithNegativeHashCodeThenItsWorkCorrectly() {
        assertEquals(-515212422, "sn,fdds23842342".hashCode());
        stringKeyHashMap.put("sn,fdds23842342", 3);

    }

    @Test
    @DisplayName("When key hashCode is maximum minimum Integer value then bucketIndex will be remainder of the division Integer maximum on bucket length")
    void whenKeyHashCodeIsMaximumMinimumIntegerValue() {
        HashMap<Integer, Integer> map = new HashMap<>();
        Integer key = Integer.MIN_VALUE;
        assertEquals(15, map.getBucketIndex(key, 16));
    }

    @Test
    @DisplayName("When get by key then return value which is in pair with this key")
    void whenGetByTheKeyThenReturnValueWhichIsInPairWithThisKey() {
        assertEquals(1, hashMapWithTwoEntries.get('A'));
        assertEquals(3, hashMapWithTwoEntries.get('B'));
    }

    @Test
    @DisplayName("When get by not used key then return null")
    void whenGetByNotUsedKeyThenReturnNull() {
        assertNull(hashMapWithTwoEntries.get('H'));
    }

    @Test
    @DisplayName("When remove by key then delete pair (key, value) and return deleted value")
    void whenRemoveByTheExistKeyThenDeletePairThisKeyAndValueAndReturnDeletedValue() {
        assertEquals(2, hashMapWithTwoEntries.size());

        assertEquals(1, hashMapWithTwoEntries.remove('A'));
        assertEquals(3, hashMapWithTwoEntries.remove('B'));

        assertEquals(0, hashMapWithTwoEntries.size());
    }

    @Test
    @DisplayName("When remove by not exist key then return null")
    void whenRemoveByNotExistKeyThenReturnNull() {
        assertNull(hashMapWithTwoEntries.remove('H'));
    }

    @Test
    @DisplayName("When remove by key which is null then delete pair (key, value) and return deleted value")
    void whenRemoveByExistKeyWhichIsNullThenDeleteThisPairKeyAndValueAndReturnDeletedValue() {
        emptyHashMap.put(null, 5);
        assertEquals(1, emptyHashMap.size());

        assertEquals(5, emptyHashMap.remove(null));
        assertEquals(0, emptyHashMap.size());

    }

    @Test
    @DisplayName("When map has two pair then size is two")
    void whenMapHasTwoPairKayValueThenReturnSizeTwo() {
        emptyHashMap.put('A', 1);
        emptyHashMap.put('B', 3);

        assertEquals(2, emptyHashMap.size());
    }

    @Test
    @DisplayName("When map has two pair and put pair with already exist key then replace value and size not increased")
    void whenSizeIsTwoAndUsedPutByAlreadyExistKeyThenSizeIsNoteChange() {
        emptyHashMap.put('A', 1);
        emptyHashMap.put('A', 3);

        assertEquals(3, emptyHashMap.get('A'));
        assertEquals(1, emptyHashMap.size());

    }

    @Test
    @DisplayName("When key is contained in map then return true")
    void whenKeyIsContainedThenReturnTrue() {
        assertTrue(hashMapWithTwoEntries.containsKey('A'));
    }

    @Test
    @DisplayName("When key is not contained in map then return false")
    void whenKeyIsNotContainedThenReturnFalse() {
        assertFalse(hashMapWithTwoEntries.containsKey('D'));
    }

    @Test
    @DisplayName("When method isEmpty used on empty map then return true")
    void testIsEmptyOnEmptyMapReturnTrue() {
        assertTrue(emptyHashMap.isEmpty());
    }

    @Test
    @DisplayName("When method isEmpty used on not empty map then return false")
    void testIsEmptyOnNotEmptyMapReturnFalse() {
        assertFalse(hashMapWithTwoEntries.isEmpty());
    }

    @Test
    @DisplayName("When used method hasNext if next element exist then return true")
    void whenUsedHasNextIfNextElementExistThenReturnTrue() {
        Iterator<Map.Entry<Character, Integer>> iterator = hashMapWithTwoEntries.iterator();

        assertTrue(iterator.hasNext());

        iterator.next();
        assertTrue(iterator.hasNext());

    }

    @Test
    @DisplayName("When used method hasNext if next note element exist then return false")
    void whenUsedHasNextIfNextElementNotExistThenReturnFalse() {
        Iterator<Map.Entry<Character, Integer>> iterator = hashMapWithTwoEntries.iterator();

        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();

        assertFalse(iterator.hasNext());

    }

    @Test
    @DisplayName("When used method hasNext on empty map then return false")
    void whenUsedHasNextInEmptyHashMapThenReturnFalse() {
        Iterator<Map.Entry<Character, Integer>> iterator = emptyHashMap.iterator();

        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("When used method next if next entry exist then return target entry with his pair(key, value)")
    void whenUsedNextIfNextElementExistThenReturnTargetEntryWithPairKeyAndValue() {
        Iterator<Map.Entry<Character, Integer>> iterator = hashMapWithTwoEntries.iterator();

        Map.Entry<Character, Integer> firstEntry = iterator.next();
        assertEquals('A', firstEntry.getKey());
        assertEquals(1, firstEntry.getValue());

        Map.Entry<Character, Integer> secondEntry = iterator.next();
        assertEquals('B', secondEntry.getKey());
        assertEquals(3, secondEntry.getValue());
    }

    @Test
    @DisplayName("When used method next if next entry not exist then expect NoSuchElementException")
    void whenUsedNextIfNextElementNotExistThenExpectNoSuchElementException() {
        Iterator<Map.Entry<Character, Integer>> iterator = hashMapWithTwoEntries.iterator();

        iterator.next();
        iterator.next();

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                iterator::next);
        assertEquals("No next element.", exception.getMessage());
    }

    @Test
    @DisplayName("When used method next on empty map then expect NoSuchElementException")
    void whenUsedNextOnEmptyHashMapThenExpectNoSuchElementException() {
        Iterator<Map.Entry<Character, Integer>> iterator = emptyHashMap.iterator();

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                iterator::next);
        assertEquals("No next element.", exception.getMessage());

    }

    @Test
    @DisplayName("When using iterator method remove after used method next then remove target entry and decreased size")
    void whenUsingRemoveAfterUsedNextThenRemoveTargetEntryAndDecreasedSize() {
        Iterator<Map.Entry<Character, Integer>> iterator = hashMapWithTwoEntries.iterator();

        assertEquals(2, hashMapWithTwoEntries.size());

        iterator.next();
        iterator.remove();
        assertEquals(1, hashMapWithTwoEntries.size());

        iterator.next();
        iterator.remove();
        assertEquals(0, hashMapWithTwoEntries.size());
    }

    @Test
    @DisplayName("When using iterator method remove before using method next then expect IllegalStateException")
    void whenUsingRemoveBeforeUsedNextThenExpectIllegalStateException() {
        Iterator<Map.Entry<Character, Integer>> iterator = hashMapWithTwoEntries.iterator();

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                iterator::remove);
        assertEquals("Method next() not used.", exception.getMessage());
    }

    @Test
    @DisplayName("When using iterator method remove after already using iterator method remove then expect IllegalStateException")
    void whenUsingRemoveAfterAlreadyUsedRemoveThenExpectIllegalStateException() {
        Iterator<Map.Entry<Character, Integer>> iterator = hashMapWithTwoEntries.iterator();

        assertEquals(2, hashMapWithTwoEntries.size());

        iterator.next();
        iterator.remove();
        assertEquals(1, hashMapWithTwoEntries.size());

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                iterator::remove);
        assertEquals("Method next() not used.", exception.getMessage());
    }

    @Test
    @DisplayName("When using iterator method remove on map with collision of two entries then its works correctly")
    void testRemoveOnMapWithCollisionEntry() {
        HashMap<Integer, Integer> collisionHashMap = new HashMap<>();
        collisionHashMap.put(1, 1);
        collisionHashMap.put(17, 17);
        assertEquals(2, collisionHashMap.size());

        Iterator<Map.Entry<Integer, Integer>> iterator = collisionHashMap.iterator();
        iterator.next();
        iterator.remove();
        assertEquals(1, collisionHashMap.size());


        iterator.next();
        iterator.remove();
        assertEquals(0, collisionHashMap.size());


    }

    @Test
    @DisplayName("When default capacity (16) is full on seventy five percent (size = 12) then buckets capacity growing multiply double plus one")
    void testMethodGrowBucketsWhenTheDefaultCapacityIsFullOnSeventyFivePercent() {
        HashMap<Integer, Integer> map = new HashMap<>();
        int key = 0;
        int value = 1;

        for (int i = 0; i < 12; i++) {
            map.put(key, value);
            key++;
            value++;
        }
        assertEquals(12, map.size());

        for (int i = 12; i < 33; i++) {
            map.put(key, value);
            key++;
            value++;
        }
        assertEquals(33, map.size());

    }

    @Test
    @DisplayName("Test on correction HashMap method toString")
    void toStringHashMap() {
        assertEquals("{A=1, B=3}", hashMapWithTwoEntries.toString());
    }

    @Test
    void whenBucketsCapacityIsZeroAndPutOnePairThenSizeStandOne() {
        HashMap<Integer, Character> map = new HashMap<>(0);

        assertNull(map.put(0, 'A'));
        assertEquals(1, map.size());
    }
}