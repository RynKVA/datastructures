package org.example.list;

import org.example.list.iterators.ArrayListIterator;

import java.util.Iterator;
import java.util.StringJoiner;

// Generics +
public class ArrayList<E> extends AbstractList<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_EXTENSION = 1.5;
    private E[] array;

    @SuppressWarnings("unchecked")
    public ArrayList() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        array = (E[]) new Object[capacity];
    }

    @Override
    public void add(E value, int index) {
        validateIndexOnAdd(index);
        expandingArray();
        if (size != 0) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = value;
        size++;
    }

    @Override
    public boolean remove(E value) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(value)) {
                System.arraycopy(array, indexOf(value) + 1, array, indexOf(value), size - (indexOf(value) + 1));
                array[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public E remove(int index) {
        validateIndex(index);
        E removedElement = get(index);
        if (index == size - 1) {
            array[size - 1] = null;
            size--;
            return removedElement;
        }
        System.arraycopy(array, index + 1, array, index, size - (index + 1));
        array[size - 1] = null;
        size--;
        return removedElement;
    }

    @Override
    public E get(int index) {
        validateIndex(index);
        return array[index];
    }

    @Override
    public E set(E value, int index) {
        validateIndex(index);
        E previousValue = array[index];
        array[index] = value;
        return previousValue;
    }


    @SuppressWarnings("unchecked")
    private void expandingArray() {
        if (size == array.length) {
            E[] targetArray = (E[]) new Object[(int) (array.length * DEFAULT_EXTENSION + 1)];
            System.arraycopy(array, 0, targetArray, 0, array.length);
            array = targetArray;
        }
    }


    @SuppressWarnings("unchecked")
    public void trimToSize() {
        E[] targetArray = (E[]) new Object[size];
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

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator<>(this);
    }
}
