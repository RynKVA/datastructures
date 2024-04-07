package org.example;

import org.example.sort.Sort;

import static org.junit.jupiter.api.Assertions.*;

class SortTest {

    @org.junit.jupiter.api.Test
    void sort() {
       int[] array= {5,10,20,15,-1};
       int[] array1={1,5,10,15,20};
        assertArrayEquals(array1, Sort.sort(array));
    }
}