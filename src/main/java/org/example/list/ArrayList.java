package org.example.list;

import org.example.list.exceptions.IndexOutOfListExceptin;
import org.example.list.exceptions.ListIsEmptyException;

public class ArrayList implements List {
    private int[] array;
    private int size = 0;

    public ArrayList() {
        array = new int[10];
    }

    public ArrayList(int capacity) {
        array = new int[capacity];
    }

    public int[] getArray() {
        // add(11)
        // add(12)
        // list.getArray()[0] = 15
        // list.get(0) -> 11
        trimToSize();
        return array;
    }


    @Override
    public void add(int value) throws IndexOutOfListExceptin {
        add(value, size);
    }

    @Override
    public void add(int value, int index) throws IndexOutOfListExceptin {
        // validateIndex() -1 or size = 5, get(6)
        // create custom exception
        // [0, 0, 0] -> add(1) -> [1, 0, 0] -> get(1) ?
        validateIndex(index);
        expandingArray();
        if (size == 0) {
            array[index] = value;
            size++;
        } else {
            for (int i = size - 1; i >= index; i--) {
                array[i + 1] = array[i];
            }
            array[index] = value;
            size++;
        }
    }

    @Override
    public boolean contains(int value) {
        if (size == 0) { // size
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (value == array[i]) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int remove(int value) throws ListIsEmptyException { // return removed value
        arrayListIsEmpty();
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                for (int j = i + 1; j < size; j++) {
                    // array.length = 1_000_000
                    // remove() index - 1 -> shift 999_998
                    array[j - 1] = array[j];
                }
                size--;
                return value;
            }
        }
        return value;
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
    public int get(int index) throws IndexOutOfListExceptin {
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
        if (size== array.length) {
            int[] targetArray = new int[(int) (array.length * 1.5 + 1)];
            System.arraycopy(array, 0, targetArray, 0, array.length);
            array = targetArray;
        }
    }

    //    private void narrowingArray() { // trimToSize() array.length = 151, size = 105
//        int[] array1 = new int[array.length - 1];
//        for (int i = 0; i < array.length - 1; i++) {
//            array1[i] = array[i];
//        }
//        array = array1;
//    }
    private void trimToSize() {
        int[] targetArray = new int[size];
        System.arraycopy(array, 0, targetArray, 0, size);
        array = targetArray;
    }

    private void validateIndex(int index) throws IndexOutOfListExceptin {
        if (index < 0 || index > size) {
            throw new IndexOutOfListExceptin();
        }

    }

    private void arrayListIsEmpty() throws ListIsEmptyException {
        if (isEmpty()) {
            throw new ListIsEmptyException();
        }
    }

}
