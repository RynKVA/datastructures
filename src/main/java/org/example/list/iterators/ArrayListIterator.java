package org.example.list.iterators;

import org.example.list.AbstractList;
import org.example.list.ArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayListIterator<E> implements Iterator<E> {
    private final ArrayList<E> arrayList;
    private int position;
    private int useFlag;
    private int removeUsed = 0;

    public ArrayListIterator(ArrayList<E> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public boolean hasNext() {
        return position != arrayList.size();
    }

    @Override
    public E next() {
        if (position == arrayList.size()) {
            throw new NoSuchElementException("No next element.");
        }
        E element = arrayList.get(position);
        position++;
        useFlag = 1;
        return element;
    }

    @Override
    public void remove() {
        if (useNext() && removeUsed == 0) {
            arrayList.remove(position - 1);
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
