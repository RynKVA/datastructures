package org.example.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class AbstractList<E> implements List<E>, Iterable<E> {
    protected int size;


    @Override
    public void add(E value) {
        add(value, size);
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value) != -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (value.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public int lastIndexOf(E value) {
        for (int i = size - 1; i >= 0; i--) {
            if (value.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    protected void validateIndexOnAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of List.");
        }
    }

    protected void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of List.");
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new AbstractIterator<>();
    }

    protected class AbstractIterator <E> implements Iterator<E> {
        private int position;
        private int useFlag;
        private int removeUsed = 0;


        @Override
        public boolean hasNext() {
            return position != size();
        }

        @Override
        public E next() {
            if (position == size()) {
                throw new NoSuchElementException("No next element.");
            }
            E element = (E) get(position);
            position++;
            useFlag = 1;
            return element;
        }

        @Override
        public void remove() {
            if (useNext() && removeUsed == 0) {
                AbstractList.this.remove(position - 1);
                removeUsed++;
                position--;
                useFlag=0;
            } else {
                throw new IllegalStateException("Method next() not used.");
            }
            removeUsed--;
        }

        private boolean useNext() {
            return useFlag != 0;
        }
    }
}
