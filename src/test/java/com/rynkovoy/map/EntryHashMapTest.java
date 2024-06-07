package com.rynkovoy.map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntryHashMapTest extends AbstractMapTest {
    @Override
    Map<String, Integer> getMap() {
        return new EntryHashMap<>();
    }

    @Override
    Map<Integer, Integer> getIntegerMap() {
        return new EntryHashMap<>();
    }

    @Override
    Map<Integer, Integer> getIntegerMap(int capacity) {
        return new EntryHashMap<>(capacity);
    }

    @Test
    @DisplayName("When key hashCode is maximum minimum Integer value then bucketIndex will be remainder of the division Integer maximum on bucket length")
    void whenKeyHashCodeIsMaximumMinimumIntegerValue() {
        EntryHashMap<Integer, Integer> map = new EntryHashMap<>();
        Integer key = Integer.MIN_VALUE;
        assertEquals(15, map.getBucketIndex(key, 16));
    }

    @Test
    @DisplayName("When used setValue in Entry then value changes on new in this Entry")
    void whenUsedSetValueInEntryThenChangeValueInThisEntry() {
        EntryHashMap <Integer,Integer> mapWithTwoEntries = new EntryHashMap<>();
        mapWithTwoEntries.put(0, 0);
        mapWithTwoEntries.put(2, 2);

        assertEquals(2, mapWithTwoEntries.get(2));
        mapWithTwoEntries.getEntry(2).setValue(6);

        assertEquals(6, mapWithTwoEntries.get(2));
    }
}