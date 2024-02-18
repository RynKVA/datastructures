package org.example.list;

import java.util.*;

public class ArrayList implements List {
    private int[] array;

//    public ArrayList() { array.length = 10
//    }

    public ArrayList() { // ArrayList(int capacity) array.length = capacity
        array = new int[]{};
    }

    public int[] getArray() {
        // add(11)
        // add(12)
        // list.getArray()[0] = 15
        // list.get(0) -> 11
        return array;
    }

    private int size = 0;

    @Override
    public void add(int value) {
        // add(value, size);
        if (array.length < size + 1) {
            expandingArray();
            array[size] = value;
            size++;
        }
    }

    @Override
    public void add(int value, int index) {
        // validateIndex() -1 or size = 5, get(6)
        // create custom exception
        // [0, 0, 0] -> add(1) -> [1, 0, 0] -> get(1) ?
//        try {
            if (index == 0 && array.length == 0) {
                expandingArray();
                array[index] = value;
            } else if (index >= array.length) {
                throw new ArrayIndexOutOfBoundsException();
            } else if (!(index >= array.length)) {
                expandingArray();
                for (int i = array.length - 2; i >= index; i--) {
                    array[i + 1] = array[i];
                }
                array[index] = value;
            }
//        } catch (ArrayIndexOutOfBoundsException e) {
//            System.out.println("This index out of array.");
//        }




    }

    @Override
    public boolean contains(int value) {
        if (array.length == 0) { // size
            return false;
        }

        for (int element : array) {
            if (value == element) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean remove(int value) { // return removed value
        if (array. == 0) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                for (int j = i + 1; j < array.length; j++) {
                    // array.length = 1_000_000
                    // remove() index - 1 -> shift 999_998
                    array[i] = array[j];
                }
                narrowingArray();// no need
                size--;
                return true;
            }

        }
        return false;
    }

    @Override
    public int size() {
        return array.length; // [0, 0, 0]
        // add(5) -> [5, 0, 0]
        // size() -> 3
        //
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
//        if(array.length==0) return true;
//        return false;
    }

    private void expandingArray() {
//        System.arraycopy()
        int[] targetArray = new int[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            targetArray[i] = array[i];
        }
        array = targetArray;
    }

    private void narrowingArray() { // trimToSize() array.length = 151, size = 105
        int[] array1 = new int[array.length - 1];
        for (int i = 0; i < array.length - 1; i++) {
            array1[i] = array[i];
        }
        array = array1;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}
