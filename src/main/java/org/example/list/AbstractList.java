package org.example.list;

public abstract class AbstractList<T> implements List<T> {
    protected int size;


    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
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
}
