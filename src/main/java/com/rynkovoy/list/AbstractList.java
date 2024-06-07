package com.rynkovoy.list;

import java.util.Objects;
import java.util.StringJoiner;

public abstract class AbstractList<E> implements List<E> {
    protected int size;


    @Override
    public void add(E value) {
        add(value, size);
    }

    @Override
    public boolean remove(E value) {
        int index = indexOf(value);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
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
        int index = 0;
        for (E findValue : this) {
            if (Objects.equals(value, findValue)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E value) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(value, get(i))) {
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
            throw new IndexOutOfBoundsException("Index: " + index + " out of bounds [0, " + size + "]");
        }
    }

    protected void validateIndex(int index) {
        if (index < 0 || index >= size) {
            if (size == 0) {
                throw new IndexOutOfBoundsException("List is empty");
            }
            throw new IndexOutOfBoundsException("Index: " + index + " out of bounds [0, " + size + "]");
        }
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (E value : this) {
            joiner.add(String.valueOf(value));
        }
        return joiner.toString();
    }
}
