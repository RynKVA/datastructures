package org.example.list.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest {
    private final HashMap<Character, Integer> emptyHashMap = new HashMap<>();
    private final HashMap<Character, Integer> hashMapWithThreeEntries = new HashMap<>();
    private final HashMap<String, Integer> stringKeyHashMap = new HashMap<>();

    @BeforeEach
    void before(){
        hashMapWithThreeEntries.put('A', 1);
        hashMapWithThreeEntries.put('B', 3);

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
        assertEquals(3, hashMapWithThreeEntries.put('B', 5));

        assertEquals(5, hashMapWithThreeEntries.get('B'));
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
        assertEquals(1, hashMapWithThreeEntries.get('A'));
        assertEquals(3, hashMapWithThreeEntries.get('B'));
    }

    @Test
    void whenGetByNotUsedKeyThenReturnNull() {
        assertNull(hashMapWithThreeEntries.get('H'));
    }

    @Test
    void whenRemoveByTheExistKeyThenDeletePairThisKeyAndValueAndReturnDeletedValue() {
        assertEquals(2, hashMapWithThreeEntries.size());

        assertEquals(1, hashMapWithThreeEntries.remove('A'));
        assertEquals(3, hashMapWithThreeEntries.remove('B'));

        assertEquals(0, hashMapWithThreeEntries.size());
    }

    @Test
    void whenRemoveByNotExistKeyThenReturnNull() {
        assertNull(hashMapWithThreeEntries.remove('H'));
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
        assertTrue(hashMapWithThreeEntries.containsKey('A'));
    }
    @Test
    void whenKeyIsNotContainedThenReturnFalse() {
        assertFalse(hashMapWithThreeEntries.containsKey('D'));
    }

    @Test
    void testIsEmptyOnEmptyMapReturnTrue(){
        assertTrue(emptyHashMap.isEmpty());
    }
    @Test
    void testIsEmptyOnNotEmptyMapReturnFalse(){
        assertFalse(hashMapWithThreeEntries.isEmpty());
    }

    @Test
    void test() {
        char key = 'A';
        int value = 0;
        for (int i = 0; i < 25; i++) {
            emptyHashMap.put(key, value);
            key++;
            value++;
        }
        assertEquals(25, emptyHashMap.size());
        System.out.println(emptyHashMap);
    }

}