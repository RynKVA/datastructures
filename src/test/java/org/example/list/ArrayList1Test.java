package org.example.list;

import org.example.list.exceptions.IndexOutOfListExceptin;
import org.example.list.exceptions.ListIsEmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ArrayList1Test {
    private final ArrayList list = new ArrayList();
    //

    @BeforeEach
    void before() throws IndexOutOfListExceptin {
        list.add(5);
        list.add(7);
        list.add(9);
        list.add(0);
        list.add(1);
    }

    @Test
    void add1() throws IndexOutOfListExceptin {
        int[] array = {5, 7, 9, 0, 1};
        assertArrayEquals(list.getArray(), array);
    }

    @Test
    @DisplayName("Add three element ..")
    void whenAddedThreeElementsThenSizeGrowsOnThree() throws IndexOutOfListExceptin {
//    void add2() throws IndexOutOfListExceptin {
        ArrayList list = new ArrayList();
        list.add(0);
        list.add(0);
        list.add(0);
        int[] array = new int[]{0, 0, 0};
        assertArrayEquals(list.getArray(), array);
    }

    @Test
    void ad3() throws IndexOutOfListExceptin {
        ArrayList list = new ArrayList();
        int[] array = new int[]{5};
        list.add(5);
        assertArrayEquals(list.getArray(), array);
    }


    @Test
    void AddByIndex() throws IndexOutOfListExceptin {
        ArrayList list = new ArrayList();
        list.add(3, 0);
        int[] array = {3};
        assertArrayEquals(list.getArray(), array);
    }

    @Test
    void AddByIndex1() throws IndexOutOfListExceptin {
        list.add(3, 3);
        int[] array = {5, 7, 9, 3, 0, 1};
        assertArrayEquals(list.getArray(), array);

    }

    @Test
    void AddByIndex2() {
        assertThrows(IndexOutOfListExceptin.class, () -> {
            list.add(3, 11);
        });
    }


    @Test
    void contains() {
        assertTrue(list.contains(1));
        assertFalse(list.contains(6));
    }

    @Test
    void remove() throws ListIsEmptyException {
        int[] array1 = {5, 7, 9, 1};
        assertEquals(0, list.remove(0));
        assertArrayEquals(array1, list.getArray());


    }

    @Test
    void remove1() throws ListIsEmptyException {
        ArrayList list = new ArrayList();
        assertThrows(ListIsEmptyException.class, () -> {
            list.remove(7);
        });
    }


    @Test
    void size() {
        System.out.println(Arrays.toString(list.getArray()));
        assertEquals(5, list.size());
    }

    @Test
    void isEmpty() {
        ArrayList list = new ArrayList();
        assertTrue(list.isEmpty());
    }

    @Test
    void get() throws IndexOutOfListExceptin, ListIsEmptyException {
        assertEquals(5, list.get(0));
        assertEquals(1, list.get(4));

    }

    @Test
    void get1() throws IndexOutOfListExceptin {
        assertThrows(IndexOutOfListExceptin.class, () -> {
            list.get(10);
        });

    }

    @Test
    void get2() throws IndexOutOfListExceptin {
        ArrayList list = new ArrayList() {
        };
        assertThrows(ListIsEmptyException.class, () -> {
            list.get(0);
        });
    }


    @Test
    void indexOf() {
        assertEquals(0, list.indexOf(5));
        assertEquals(4, list.indexOf(1));
        assertEquals(-1, list.indexOf(6));
    }

    // private boolean, check each element

     private int[] toArray(ArrayList list) throws ListIsEmptyException, IndexOutOfListExceptin {
         int[] arrayOfElements = new int[list.size()];
         for (int i = 0; i < list.size(); i++) {
             arrayOfElements[i] = list.get(i);
         }
         return arrayOfElements;

     }
}