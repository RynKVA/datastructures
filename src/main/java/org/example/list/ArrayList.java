package org.example.list;

;

//Generics
public class ArrayList implements List {
    private final int STANDARDCAPACITY = 10;
    private final double STANDARDEXTENSION = 1.5;

    // move magic numbers to constants
    // replace by system.arraycopy where it's possible
    // move toArray method to test
    private int[] array;
    private int size = 0;

    public ArrayList() {
        array = new int[STANDARDCAPACITY];
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
    public void add(int value){
        add(value, size);
    }

    @Override
    public void add(int value, int index){
        validateIndex(index);
        expandingArray();
        if (size == 0) {
            array[index] = value;
            size++;
        } else {
            System.arraycopy(array, index, array, index + 1, size - index);
            array[index] = value;
            size++;
        }
    }

    @Override
    public boolean contains(int value) {
        return indexOf(value) != -1;
        /*if (size == 0) { // size
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (value == array[i]) {
                return true;
            }
        }
        return false;*/
    }

    @Override
    public boolean remove(int value){
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                // System.arraycopy
                System.arraycopy(array,indexOf(value)+1,array,indexOf(value),size-(indexOf(value)+1)) ;// [1, 2, 3, 4] -> remove 2 index 1 -> [1, 3, 4]
                for (int j = i + 1; j < size; j++) {
                    array[j - 1] = array[j];
                }
                size--;
                return true ;
            }
        }
        return false;
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
    public int get(int index) {
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
        if (size == array.length) {
            int[] targetArray = new int[(int) (array.length * STANDARDEXTENSION + 1)];
            System.arraycopy(array, 0, targetArray, 0, array.length);
            array = targetArray;
        }
    }

    private void trimToSize() {
        int[] targetArray = new int[size];
        System.arraycopy(array, 0, targetArray, 0, size);
        array = targetArray;
    }

    private void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }

    }
}
