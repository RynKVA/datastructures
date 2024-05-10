package org.example.map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class HashMapTest extends AbstractMapTest {
    @Override
    Map<String, Integer> getMap() {
        return new HashMap<>();
    }

    @Override
    Map<Integer, Integer> getIntegerMap() {
        return new HashMap<>();
    }

    @Override
    Map<Integer, Integer> getIntegerMap(int capacity) {
        return new HashMap<>(capacity);
    }

    @Test
    @DisplayName("When key hashCode is maximum minimum Integer value then bucketIndex will be remainder of the division Integer maximum on bucket length")
    void whenKeyHashCodeIsMaximumMinimumIntegerValue() {
        HashMap<Integer, Integer> map = new HashMap<>();
        Integer key = Integer.MIN_VALUE;
        assertEquals(15, map.getBucketIndex(key, 16));
    }
}
