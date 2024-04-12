package org.example.list;

import java.util.Iterator;

public interface List <E> {

    E get(int index);
    E set(E value, int index);

    int indexOf(E value);
    int lastIndexOf(E value);
    void clear ();

    void add(E value);

    void add(E value, int index);

    boolean contains(E value);

    boolean remove(E value);

    E remove(int index);

    int size();

    boolean isEmpty();

    String toString();

    Iterator<E> iterator();


}
