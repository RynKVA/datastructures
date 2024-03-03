package org.example.list;

import java.util.Arrays;
import java.util.StringJoiner;

// move magic numbers to constants +
// replace by system.arraycopy where it's possible +
// move toArray method to test +
// overriding method toString +
//Generics
public class ArrayList extends AbstractList implements List {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_EXTENSION = 1.5;
    private int[] array;


    public ArrayList() {
        array = new int[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        array = new int[capacity];
    }


    @Override
    public void add(int value) {
        add(value, size);
    }

    @Override
    public void add(int value, int index) {
        validateIndex(index);
        expandingArray();
        if (size != 0) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = value;
        size++;
    }

    @Override
    public boolean contains(int value) {
        return indexOf(value) != -1;
    }

    @Override
    public boolean remove(int value) {
        listISEmpty();
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                System.arraycopy(array, indexOf(value) + 1, array, indexOf(value), size - (indexOf(value) + 1));
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int get(int index) {
        listISEmpty();
        validateIndex(index);
        return array[index];
    }

    @Override
    public int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    private void expandingArray() {
        if (size == array.length) {
            int[] targetArray = new int[(int) (array.length * DEFAULT_EXTENSION + 1)];
            System.arraycopy(array, 0, targetArray, 0, array.length);
            array = targetArray;
        }
    }

    private void trimToSize() {
        int[] targetArray = new int[size];
        System.arraycopy(array, 0, targetArray, 0, size);
        array = targetArray;
    }

    @Override
    public String toString (){
        trimToSize();
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        return joiner.toString(); // [1, 4, 7, 9]
    }
    // array.length = 100, list.size = 51
    // toString
    // list.add() -> expanding
}
