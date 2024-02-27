package org.example.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArrayList1Test {
    private final ArrayList list = new ArrayList();
    //

    @BeforeEach
    void before(){
        list.add(5);
        list.add(7);
        list.add(9);
        list.add(0);
        list.add(1);
    }

 /*   @Test
    void add1(){
        int[] array = {5, 7, 9, 0, 1};
        assertArrayEquals(toArray(list), array);
    }*/

    @Test
    @DisplayName("Add three element ..")
    void whenAddedThreeElementsThenSizeGrowsOnThree(){
//    void add2() throws IndexOutOfListException {
        ArrayList list = new ArrayList();
        list.add(0);
        list.add(0);
        list.add(0);
        int[] array = new int[]{0, 0, 0};
        assertArrayEquals(toArray(list), array);
    }

    @Test
    void ad3(){
        ArrayList list = new ArrayList();
        int[] array = new int[]{5};
        list.add(5);
        assertArrayEquals(toArray(list), array);
    }


    @Test
    void AddByIndex(){
        ArrayList list = new ArrayList();
        list.add(3, 0);
        int[] array = {3};
        assertArrayEquals(toArray(list), array);
    }

    @Test
    void AddByIndex1(){
        list.add(3, 3);
        int[] array = {5, 7, 9, 3, 0, 1};
        assertArrayEquals(toArray(list), array);

    }

    @Test
    void AddByIndex2() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.add(3, 11);
        });
    }


    @Test
    void contains() {
        assertTrue(list.contains(1));
        assertFalse(list.contains(6));
    }

    @Test
    void remove() {
        list.remove(0);
        int[] array1 = {5, 7, 9, 1};
        assertArrayEquals(array1, toArray(list));


    }

    @Test
    void remove1() {
        assertFalse(list.remove(11));
    }


    @Test
    void size() {
        assertEquals(5, list.size());
    }

    @Test
    void isEmpty() {
        ArrayList list = new ArrayList();
        assertTrue(list.isEmpty());
    }

    @Test
    void get(){
        assertEquals(5, list.get(0));
        assertEquals(1, list.get(4));

    }

    @Test
    void get1() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(10);
        });

    }

    @Test
    void get2() {
        ArrayList list = new ArrayList() {};
        assertThrows(IndexOutOfBoundsException.class, () -> {
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

     private int[] toArray(ArrayList list){
         int[] arrayOfElements = new int[list.size()];
         for (int i = 0; i < list.size(); i++) {
             arrayOfElements[i] = list.get(i);
         }
         return arrayOfElements;

     }
}