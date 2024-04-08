package org.example.list.queue;

import java.util.Objects;
import java.util.StringJoiner;

public class ArrayQueue implements NewQueue {
    private int size;
    private final Object[] array;

    public ArrayQueue() {
        array = new Object[10];
    }

    @Override
    public void enqueue(Object value) {
        array[size] = value;
        size++;
    }

    @Override
    public Object dequeue() {
        if (isEmpty()){
            throw new IllegalStateException("Queue is empty!");
        }
        Object returnValue = array[0];
        System.arraycopy(array, 1, array, 0, size-1);
        size--;
        return returnValue;
    }

    @Override
    public Object peek() {
        if(isEmpty()){
            throw new IllegalStateException("Queue is empty!");
        }
        return array[0];
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
    public boolean contains(Object value) {
        for (int i = 0; i < size; i++) {
            if(Objects.equals(array[i], value)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        size = 0;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[","]");
        for (int i = 0; i < size; i++) {
            joiner.add(String.valueOf(array[i]));
        }
        return joiner.toString();
    }
}
