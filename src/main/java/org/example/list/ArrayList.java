package org.example.list;

import java.util.StringJoiner;

// move magic numbers to constants +
// replace by system.arraycopy where it's possible +
// move toArray method to test +
// overriding method toString +
// Generics
public class ArrayList <T> extends AbstractList <T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_EXTENSION = 1.5;
    private Object[] array;


    public ArrayList() {
        array = new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        array = new Object[capacity];
    }

    @Override
    public void add(T value, int index) {
        validateIndexOnAdd(index);
        expandingArray();
        if (size != 0) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = value;
        size++;
    }

    @Override
    public boolean remove(T value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                System.arraycopy(array, indexOf(value) + 1, array, indexOf(value), size - (indexOf(value) + 1));
                size--;
                return true;
            }
        }
        return false;
    }
    @Override
    public Object remove(int index) {
        validateIndex(index);
        if (index==size) {
            size--;
            return array[index];
        }
        System.arraycopy(array, index + 1, array, index, size - (index + 1));
        size--;
        return array[index];
    }

    @Override
    public Object get(int index) {
        validateIndex(index);
        return array[index];
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    private void expandingArray() {
        if (size == array.length) {
            Object[] targetArray =  new Object[(int) (array.length * DEFAULT_EXTENSION + 1)];
            System.arraycopy(array, 0, targetArray, 0, array.length);
            array = targetArray;
        }
    }

    public void trimToSize() {
        Object[] targetArray = new Object[size];
        System.arraycopy(array, 0, targetArray, 0, size);
        array = targetArray;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            joiner.add(String.valueOf(array[i]));
        }
        return joiner.toString();
    }
}
