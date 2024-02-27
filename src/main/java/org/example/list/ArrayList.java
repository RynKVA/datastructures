package org.example.list;

import java.util.Arrays;

// move magic numbers to constants +
// replace by system.arraycopy where it's possible +
// move toArray method to test +
// overriding method toString +
//Generics
public class ArrayList extends AbstractList implements List {
    private final int STANDARDCAPACITY = 10;
    private final double STANDARDEXTENSION = 1.5;
    private int[] array;


    public ArrayList() {
        array = new int[STANDARDCAPACITY];
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
        if (size == 0) {
            array[index] = value;
            size++;
        } else {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        }
    }

    @Override
    public boolean contains(int value) {
        return indexOf(value) != -1;
    }

    @Override
    public boolean remove(int value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                System.arraycopy(array, indexOf(value) + 1, array, indexOf(value), size - (indexOf(value) + 1));
                for (int j = i + 1; j < size; j++) {
                    array[j - 1] = array[j];
                }
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
            int[] targetArray = new int[(int) (array.length * STANDARDEXTENSION + 1)];
            System.arraycopy(array, 0, targetArray, 0, array.length);
            array = targetArray;
        }
    }

    private void trimToSize() {
        int[] targetArray = new int[size];
        System.arraycopy(array, 0, targetArray, 0, size);
        array = targetArray;
    }

    public String toString (){
        trimToSize();
        return Arrays.toString(array);
    }
}
