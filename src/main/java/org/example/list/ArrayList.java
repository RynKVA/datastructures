package org.example.list;

import java.util.StringJoiner;
// Generics +
public class ArrayList <T> extends AbstractList <T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_EXTENSION = 1.5;
    private T[] array;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        array = (T[]) new Object[capacity];
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
    public T remove(int index) {
        validateIndex(index);
        T removedElement=get(index);
        if (index==size) {
            size--;
            return removedElement;
        }
        System.arraycopy(array, index + 1, array, index, size - (index + 1));
        size--;
        return removedElement;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return array[index];
    }


    @SuppressWarnings("unchecked")
    private void expandingArray() {
        if (size == array.length) {
            T[] targetArray =  (T[]) new Object[(int) (array.length * DEFAULT_EXTENSION + 1)];
            System.arraycopy(array, 0, targetArray, 0, array.length);
            array = targetArray;
        }
    }


    @SuppressWarnings("unchecked")
    public void trimToSize() {
        T[] targetArray = (T[]) new Object[size];
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
