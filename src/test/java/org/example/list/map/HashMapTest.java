package org.example.list.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {
    private final HashMap<Character, Integer> emptyHashMap = new HashMap<>();
    private final HashMap<Character, Integer> hashMapWithTwoEntries = new HashMap<>();
    private final HashMap<String, Integer> stringKeyHashMap = new HashMap<>();
    @BeforeEach
    void before(){
        hashMapWithTwoEntries.put('A', 1);
        hashMapWithTwoEntries.put('B', 3);

    }

    @Test
    void testPutAndGet() {
        emptyHashMap.put('A', 1);
        emptyHashMap.put('B', 3);


        assertEquals(2, emptyHashMap.size());

        assertEquals(1, emptyHashMap.get('A'));
        assertEquals(3, emptyHashMap.get('B'));
    }

    @Test
    void whenDoingFirstPutThenReturnNull() {
        assertNull(emptyHashMap.put('A', 1));
    }

    @Test
    void testPutPairSameKeyAndNewValueReplaceOldValueReturnOldValue(){
        assertEquals(3, hashMapWithTwoEntries.put('B', 5));

        assertEquals(5, hashMapWithTwoEntries.get('B'));
    }
    @Test
    void testPutIFKeyAndValueIsNullStandInBucketIndexZero() {
        emptyHashMap.put(null, null);

        assertEquals(1, emptyHashMap.size());

        assertNull(emptyHashMap.get(null));
    }
    @Test
    void whenPutKeyNullInMapWitchAlreadyExistPairWithKeyNullThenReplaceOnNewValueAndReturnOldValue() {
        emptyHashMap.put(null, null);
        assertNull(emptyHashMap.put(null, 8));

        assertEquals(1, emptyHashMap.size());

        assertEquals(8, emptyHashMap.get(null));
    }

    @Test
    void whenPutByTheKeyWithNegativeHashCodeThenItsWorkCorrectly() {
        assertEquals(-515212422,"sn,fdds23842342".hashCode());
        stringKeyHashMap.put("sn,fdds23842342", 3);

    }

    @Test
    void whenGetByTheKeyThenReturnValueWhichIsPairWithThisKey() {
        assertEquals(1, hashMapWithTwoEntries.get('A'));
        assertEquals(3, hashMapWithTwoEntries.get('B'));
    }

    @Test
    void whenGetByNotUsedKeyThenReturnNull() {
        assertNull(hashMapWithTwoEntries.get('H'));
    }

    @Test
    void whenRemoveByTheExistKeyThenDeletePairThisKeyAndValueAndReturnDeletedValue() {
        assertEquals(2, hashMapWithTwoEntries.size());

        assertEquals(1, hashMapWithTwoEntries.remove('A'));
        assertEquals(3, hashMapWithTwoEntries.remove('B'));

        assertEquals(0, hashMapWithTwoEntries.size());
    }

    @Test
    void whenRemoveByNotExistKeyThenReturnNull() {
        assertNull(hashMapWithTwoEntries.remove('H'));
    }

    @Test
    void whenRemoveByExistKeyWhichIsNullThenDeleteThisPairKeyAndValueAndReturnDeletedValue() {
        emptyHashMap.put(null,5);
        assertEquals(1, emptyHashMap.size());

        assertEquals(5, emptyHashMap.remove(null));
        assertEquals(0, emptyHashMap.size());
        
    }

    @Test
    void whenMapHasTwoPairKayValueThenReturnSizeTwo() {
        emptyHashMap.put('A', 1);
        emptyHashMap.put('B', 3);

        assertEquals(2, emptyHashMap.size());
    }

    @Test
    void whenSizeIsTwoAndUsedPutByAlreadyExistKeyThenSizeIsNoteChange() {
        emptyHashMap.put('A', 1);
        emptyHashMap.put('A', 3);

        assertEquals(1, emptyHashMap.size());

    }

    @Test
    void whenKeyIsContainedThenReturnTrue() {
        assertTrue(hashMapWithTwoEntries.containsKey('A'));
    }
    @Test
    void whenKeyIsNotContainedThenReturnFalse() {
        assertFalse(hashMapWithTwoEntries.containsKey('D'));
    }

    @Test
    void testIsEmptyOnEmptyMapReturnTrue(){
        assertTrue(emptyHashMap.isEmpty());
    }
    @Test
    void testIsEmptyOnNotEmptyMapReturnFalse(){
        assertFalse(hashMapWithTwoEntries.isEmpty());
    }

    @Test
    void whenUsedHasNextIfNextElementExistThenReturnTrue() {
        Iterator<Map.Entry<Character, Integer>> iterator = hashMapWithTwoEntries.iterator();

        assertTrue(iterator.hasNext());

        iterator.next();
        assertTrue(iterator.hasNext());

    }

    @Test
    void whenUsedHasNextIfNextElementNotExistThenReturnFalse() {
        Iterator<Map.Entry<Character, Integer>> iterator = hashMapWithTwoEntries.iterator();

        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();

        assertFalse(iterator.hasNext());

    }

    @Test
    void whenUsedHasNextInEmptyHashMapThenReturnFalse() {
        Iterator<Map.Entry<Character, Integer>> iterator = emptyHashMap.iterator();

        assertFalse(iterator.hasNext());
    }

    @Test
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
    void whenUsedNextIfNextElementNotExistThenExpectNoSuchElementException() {
        Iterator<Map.Entry<Character, Integer>> iterator = hashMapWithTwoEntries.iterator();

        iterator.next();
        iterator.next();

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                iterator::next);
        assertEquals("No next element.", exception.getMessage());
    }

    @Test
    void whenUsedNextOnEmptyHashMapThenExpectNoSuchElementException() {
        Iterator<Map.Entry<Character, Integer>> iterator = emptyHashMap.iterator();

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                iterator::next);
        assertEquals("No next element.", exception.getMessage());

    }

    @Test
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
    void whenUsingRemoveBeforeUsedNextThenExpectIllegalStateException() {
        Iterator<Map.Entry<Character, Integer>> iterator = hashMapWithTwoEntries.iterator();

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                iterator::remove);
        assertEquals("Method next() not used.", exception.getMessage());
    }

    @Test
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
    void testRemoveOnMapWithCollisionEntry() {
        HashMap<Integer, Integer> collisionHashMap = new HashMap<>();
        collisionHashMap.put(1,1);
        collisionHashMap.put(17,17);
        assertEquals(2, collisionHashMap.size() );

        Iterator<Map.Entry<Integer, Integer>> iterator = collisionHashMap.iterator();
        iterator.next();
        iterator.remove();
        assertEquals( 1, collisionHashMap.size() );


        iterator.next();
        iterator.remove();
        assertEquals(0, collisionHashMap.size() );


    }

    @Test
    void test() {
        emptyHashMap.put(null, null);
        char key = 'A';
        int value = 0;
        for (int i = 0; i < 25; i++) {
            emptyHashMap.put(key, value);
            key++;
            value++;
        }
        assertEquals(26, emptyHashMap.size());
        System.out.println(emptyHashMap);
    }
}