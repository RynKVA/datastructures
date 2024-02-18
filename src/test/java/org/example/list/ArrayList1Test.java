package org.example.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayList1Test {
    private ArrayList list = new ArrayList();
    //

    @BeforeEach
    void before() {
        list.add(5);
        list.add(7);
        list.add(9);
        list.add(0);
        list.add(1);
    }

    @Test
    void add() {
        ArrayList list = new ArrayList();
        list.add(5);
        list.add(7);
        list.add(9);
        list.add(0);
        list.add(1);

        int[] array={1};
        int[] array1={5,7,9,0,1};
        int[] array2={};
//        assertArrayEquals(list.getArray(), array);
        assertArrayEquals(list.getArray(), array1);
    }

    @Test
    void testAdd() {
        ArrayList list = new ArrayList();
        list.add(3,0);

        int[] array={3};
        int[] array1={5,7,9,3,0,1};
        int[] array2={};
//        assertArrayEquals(list.getArray(), array);
        assertArrayEquals(list.getArray(), array);
    }

    @Test
    void test() {
        ArrayList list = new ArrayList();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
           list.add(11, 1);
        });
    }

    @Test
    void contains() {
        ArrayList list = new ArrayList();
        list.add(5);
        list.add(7);
        list.add(9);
        list.add(0);
        list.add(1);
        boolean b= true;
        assertEquals(b,list.contains(1));
    }

    @Test
    void remove() {
        ArrayList list = new ArrayList();
        list.add(5);
        list.add(7);
        list.add(9);
        list.add(0);
        list.add(1);
        list.remove(0);
        int[] array1={5,7,9,1};
        assertArrayEquals(array1,list.getArray());
        assertEquals(true,list.remove(5));
    }

    @Test
    void size() {
        ArrayList list = new ArrayList();
//        list.add(5);
//        list.add(7);
//        list.add(9);
//        list.add(0);
//        list.add(1);
//        list.remove(7);
        int size=0;
        assertEquals(size,list.size());
    }

    @Test
    void isEmpty() {
    }

    // private boolean, check each element
}