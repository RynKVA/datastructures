package org.example.stack;

import java.util.StringJoiner;

public class StackClass<E> implements Stack<E> {
    private E[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private static final double DEFAULT_EXTENSION = 1.5;

    @SuppressWarnings("unchecked")
    StackClass() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E peek() {
        return array[size - 1];
    }

    @Override
    public E pop() {
        stackIsEmpty();
        E targetItem = array[size - 1];
        size--;
        return targetItem;
    }

    @Override
    public E push(E item) {
        expandingArray();
        if (size == 0) {
            array[0] = item;
            size++;
            return item;
        }
        array[size] = item;
        size++;
        return item;
    }


    @Override
    public int search(Object item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(array[i])){
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            joiner.add(String.valueOf(array[i]));
        }
        return joiner.toString();
    }

    @SuppressWarnings("unchecked")
    private void expandingArray() {
        if (size == array.length) {
            E[] targetArray = (E[]) new Object[(int) (array.length * DEFAULT_EXTENSION + 1)];
            System.arraycopy(array, 0, targetArray, 0, array.length);
            array = targetArray;
        }
    }

    private void stackIsEmpty() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("Stack is empty, push item.");
        }
    }
}
