package com.rynkovoy.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<E> extends AbstractList<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_EXTENSION = 1.5;
    private E[] array;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayList(int capacity) {
        array = (E[]) new Object[capacity];
    }

    @Override
    public void add(E value, int index) {
        validateIndexOnAdd(index);
        growIfNeeded();
        if (size != 0) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }
        array[index] = value;
        size++;
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
        E removedElement = get(index);
        if (index != size - 1) {
            System.arraycopy(array, index + 1, array, index, size - (index + 1));
        }
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
        E previousValue = get(index);
        array[index] = value;
        return previousValue;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArraylistIterator();
    }

    @SuppressWarnings("unchecked")
    private void growIfNeeded() {
        if (size == array.length) {
            E[] targetArray = (E[]) new Object[(int) (array.length * DEFAULT_EXTENSION + 1)];
            System.arraycopy(array, 0, targetArray, 0, array.length);
            array = targetArray;
        }
    }

    int capacityArrayLength() {
        return array.length;
    }

    public void trimToSize() {
        array = Arrays.copyOf(array, size);
    }

    private class ArraylistIterator implements Iterator<E> {
        private int position;
        private boolean isNextUsed;

        @Override
        public boolean hasNext() {
            return position != size();
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next element.");
            }
            E element = (E) get(position);
            position++;
            isNextUsed = true;
            return element;
        }

        @Override
        public void remove() {
            if (!isNextUsed) {
                throw new IllegalStateException("Method next() not used.");
            }
            ArrayList.this.remove(position - 1);
            position--;
            isNextUsed = false;
        }
    }
}
